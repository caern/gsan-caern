package gcom.gui.micromedicao;

import gcom.atualizacaocadastral.ArquivoTextoAtualizacaoCadastralDM;
import gcom.atualizacaocadastral.FiltroArquivoTextoAtualizacaoCadastralDM;
import gcom.cadastro.SistemaAndroid;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.gui.atualizacaocadastral.AtualizacaoCadastralDMOnlineHelper;
import gcom.micromedicao.SituacaoTransmissaoLeitura;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.google.gson.Gson;

public class ProcessarRequisicaoDispositivoMovelAtualizacaoCadastralAction extends GcomAction {
	/**
	 * Constantes de Classe.
	 */
	private static final char RESPOSTA_ERRO = '#';
	private static final byte RESPOSTA_OK = '*';

	private static final int BAIXAR_ARQUIVO_TEXTO_LIBERADO = 1;
	private static final int ATUALIZAR_MOVIMENTO_DADOS_IMOVEL = 2;
	private static final int FINALIZAR_ROTEIRO = 3;
	private static final int ATUALIZAR_SITUACAO_ARQUIVO = 5;
	private static final int BAIXAR_NOVA_VERSAO_APK = 11;
	private static final int VERIFICAR_VERSAO_ANDROID = 12;
	private static final int PING = 15;
	private static final int CONSULTAR_SITUACAO_ARQUIVO = 16;

	/**
	 * M�todo Execute do Action
	 * 
	 * @param actionMapping
	 * @param actionForm
	 * @param httpServletRequest
	 * @param httpServletResponse
	 * @return
	 */
	public ActionForward execute(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest request,
			HttpServletResponse response) {

		InputStream in = null;
		OutputStream out = null;
		
		try {
			out = response.getOutputStream();
		} catch (IOException e) {
			System.out.println("ERROR:REQUISICAO ATUALIZACAO CADASTRAL: " + e.getMessage());
		}

		try {			
			in = request.getInputStream();
			DataInputStream din = new DataInputStream(in);

			int pacote = din.readByte();

			System.out.println("INICIO:SOLICITACAO ATUALIZACAO CADASTRAL PACOTE: " + pacote);
			switch (pacote) {
			
			case BAIXAR_ARQUIVO_TEXTO_LIBERADO:
				// Baixar Arquivo
				this.baixarArquivoTextoLiberado(din, response, out);
				break;
			case ATUALIZAR_SITUACAO_ARQUIVO:
				/*
				 *  Atualizar Situacao do Arquivo
				 */
				Short situacaoArquivo = din.readShort();
				int idAtualizacaoCadastralArquivoTexto = din.readInt();
				this.atualizarSituacaoArquivo(response, out, 
						idAtualizacaoCadastralArquivoTexto, Integer.valueOf(situacaoArquivo));
				break;
			case PING:
				out.write(RESPOSTA_OK);
				out.flush();
				break;
			case BAIXAR_NOVA_VERSAO_APK:
				this.baixarNovaVersaoAtualizacaoCadastralAndroid(din, response, out);
				break;
			case VERIFICAR_VERSAO_ANDROID:
				this.verificarVersaoAtualizacaoCadastralAndroid(din, response, out);
				break;
			case FINALIZAR_ROTEIRO:
				/*
				 *  Finalizar Leitura
				 */
				this.finalizarRoteiro(din,in, response, out);
				break;
			case ATUALIZAR_MOVIMENTO_DADOS_IMOVEL:
				/*
				 *  Recebe a transmiss�o 
				 *  do im�vel um-a-um
				 */
				this.atualizarImovel(din, response, out);
				break;
			case CONSULTAR_SITUACAO_ARQUIVO:
				long idComando = din.readLong();
				this.consultarArquivoFinalizado(response, out, idComando);
			default:
				break;
			}

			System.out.println("FIM:SOLICITACAO ATUALIZACAO CADASTRAL PACOTE: " + pacote);
		} catch (Exception e) {

			System.out.println("ERROR:REQUISICAO ATUALIZACAO CADASTRAL: " + e.getMessage());
			e.printStackTrace();
			response.setContentLength(1);
			try {
				out.write(RESPOSTA_ERRO);
				out.flush();
			} catch (IOException e1) {
				System.out.println("ERROR:REQUISICAO ATUALIZACAO CADASTRAL: " + e.getMessage());
			}
		} finally {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * M�todo respons�vel por processar
	 * imovel atualiza��o Cadastral
	 * @author Jonathan Marcos
	 * @since 14/10/2014
	 * @param din
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	private void atualizarImovel(DataInputStream din, 
			HttpServletResponse response,OutputStream out) throws IOException {

		System.out.println("INICIO: Recebendo dados do im�vel para atualiza��o ");
		try {
			int tamanhoJsonAntesCompressao = din.readInt();
			int tamanhoJsonDepoisCompressao = din.readInt();
			
			/*
			 * Retorna o array de byte
			 * comprimido
			 */
			byte[] bytesGzipJson = new byte[tamanhoJsonDepoisCompressao];
			int read = 0;
			int numRead = 0;
			while (read < bytesGzipJson.length && (numRead = 
					din.read(bytesGzipJson, read, bytesGzipJson.length - read)) >= 0) {
				read = read + numRead;
			}
			
			/*
			 * Descomprimi o array de
			 * byte
			 */
			byte[] jsonBytes = descomprimirJsonBytes(
					bytesGzipJson,tamanhoJsonAntesCompressao);
			/*
			 * Cria a string json e 
			 * faz o parse para 
			 * objeto helper
			 */
			String json = new String(jsonBytes);
			AtualizacaoCadastralDMOnlineHelper helper = new Gson().
					fromJson(json, AtualizacaoCadastralDMOnlineHelper.class);
			
			/*
			 * Atualiza o imovel
			 */
			getFachada().processarRecebimentoDadosImovelTabletOnline(helper);
			
			out.write(RESPOSTA_OK);
			out.flush();

			System.out.println("FIM: Recebendo dados do im�vel para atualiza��o ");

		} catch (Exception e) {
			System.out.println("ERROR: Recebendo dados do im�vel para atualiza��o");
			e.printStackTrace();
			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * [UC1393] Processar Requisi��es do Dispositivo M�vel Atualiza��o Cadastral.
	 * 
	 * Baixa o arquivo texto na situa��o liberado.
	 * 
	 * @author Diogo Luiz
	 * @date 04/09/2014
	 * 
	 * @param data
	 * @param response
	 * @throws IOException
	 */
	public void baixarArquivoTextoLiberado(DataInputStream data, HttpServletResponse response, OutputStream out) throws IOException {

		System.out.println("INICIO:BAIXAR ARQUIVO ATUALIZACAO CADASTRAL:");

		Fachada fachada = Fachada.getInstancia();
		String login = data.readUTF();
		String senha = data.readUTF();

		System.out.println("LOGIN:BAIXAR ARQUIVO ATUALIZACAO CADASTRAL: " + login);

		try {
			Object[] retorno = fachada.baixarArquivoTextoLiberadoParaLeituristaAtualizacaoCadastral(login, senha);
			byte[] arq = (byte[]) retorno[0];

			if (arq != null) {
				System.out.println("INICIO:TRANSMITIR ARQUIVO ATUALIZACAO CADASTRAL:");

				response.setContentLength(1 + arq.length);
				out.write(RESPOSTA_OK);

				out.write(arq);

				out.flush();

				System.out.println("FIM:TRANSMITIR ARQUIVO ATUALIZACAO CADASTRAL:");
			} else {
				System.out.println("ERROR:BAIXAR ARQUIVO ATUALIZACAO CADASTRAL SEM DADOS:");

				response.setContentLength(1);
				out.write(RESPOSTA_ERRO);
				out.flush();
			}

		} catch (Exception e) {

			System.out.println("ERROR:BAIXAR ARQUIVO ATUALIZACAO CADASTRAL:");
			e.printStackTrace();

			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * 
	 * Atualiza o arquivo texto para situacao "EM CAMPO"
	 * 
	 * @author Diogo Luiz
	 * @date 05/09/2014
	 * 
	 * @param idArquivoTextoVisitaCampo
	 * @param response
	 * @param out
	 * 
	 * @throws IOException
	 */
	private void atualizarSituacaoArquivo(HttpServletResponse response, OutputStream out,
			int idAtualizacaoCadastralArquivoTexto, Integer situacaoArquivo) throws IOException {
		try {
			// Atualiza o arquivo em campo
			System.out.println("INICIO: Atualizando a situacao do arquivo ATUALIZACAO CADASTRAL ID: "
					+ idAtualizacaoCadastralArquivoTexto);
			System.out.println("INICIO: Atualizando a situacao do arquivo ATUALIZACAO CADASTRAL SITUACAO: "
					+ situacaoArquivo);
			
			getFachada().atualizarSituacaoDoArquivo(idAtualizacaoCadastralArquivoTexto, situacaoArquivo);			

			out.write(RESPOSTA_OK);
			out.flush();

			System.out.println("FIM: Atualizando a situacao do arquivo ATUALIZACAO CADASTRAL ID: "
					+ idAtualizacaoCadastralArquivoTexto);

		} catch (Exception e) {
			System.out.println("ERROR: Atualizando a situacao do arquivo ATUALIZACAO CADASTRAL ID: "
					+ idAtualizacaoCadastralArquivoTexto);
			e.printStackTrace();

			response.setContentLength(1);

			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * 
	 * @param din
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	private void baixarNovaVersaoAtualizacaoCadastralAndroid(DataInputStream din, HttpServletResponse response,
			OutputStream out) throws IOException {
		Fachada fachada = Fachada.getInstancia();
		System.out.println("INICIO:SOLICITAR NOVA VERSAO ATUALIZACAO CADASTRAL:");
		try {
			Object[] obj = fachada.baixarNovaVersaoApk(SistemaAndroid.ATUALIZACAO_CADASTRAL);
			if (obj != null) {

				byte[] arq = (byte[]) obj[0];

				System.out.println("INICIO: TRANSMITINDO NOVA VERSAO APK ATUALIZACAO CADASTRAL:");

				response.setContentLength(arq.length);

				out.write(RESPOSTA_OK);

				out.write(arq);
				out.flush();

				System.out.println("FIM: TRANSMITINDO NOVA VERSAO APK ATUALIZACAO CADASTRAL:");
			} else {
				System.out.println("ERROR:SOLICITAR NOVA VERSAO ATUALIZACAO CADASTRAL SEM VERS�O:");
				response.setContentLength(1);

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
		} catch (Exception e) {

			System.out.println("ERROR:SOLICITAR NOVA VERSAO ATUALIZACAO CADASTRAL:");
			e.printStackTrace();

			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}

	private void verificarVersaoAtualizacaoCadastralAndroid(DataInputStream din, HttpServletResponse response,
			OutputStream out) throws IOException {

		Fachada fachada = Fachada.getInstancia();
		System.out.println("INICIO: VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL:");

		try {

			Object[] obj = fachada.baixarNovaVersaoApk(SistemaAndroid.ATUALIZACAO_CADASTRAL);

			if (obj != null) {

				String versao = (String) obj[1].toString();

				System.out.println("INICIO: TRANSMITINDO VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL:");

				out.write(RESPOSTA_OK);

				out.write(versao.getBytes());
				out.flush();

				System.out.println("FIM: TRANSMITINDO VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL:");
			} else {
				System.out.println("ERROR: VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL SEM DADOS:");
				response.setContentLength(1);

				out.write(RESPOSTA_ERRO);
				out.flush();
			}
		} catch (Exception e) {

			System.out.println("ERROR: VERIFICA NOVA VERSAO ATUALIZACAO CADASTRAL:");
			e.printStackTrace();

			response.setContentLength(1);
			out.write(RESPOSTA_ERRO);
			out.flush();

		}
	}

	/**
	 * M�todo respons�vel por recepcionar
	 * a finaliza��o do roteiro online
	 * @author Jonathan Marcos
	 * @since 07/10/2014
	 * @param din
	 * @param inputStream
	 * @param response
	 * @param out
	 * @throws IOException
	 */
	private void finalizarRoteiro(DataInputStream din,InputStream inputStream, 
			HttpServletResponse response,OutputStream out) throws IOException {
		String nomeArquivo = din.readUTF();
		File fileDiretorio = new File(ConstantesSistema.
				DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+"/"+nomeArquivo);
		try {
			System.out.println("INICIO: Finalizando roteiro atualizacao cadastral ");
			
			/*
			 * Cria os diret�rios 
			 * necess�rios e depois
			 * processa o arquivo
			 */
			criarDiretorios(nomeArquivo);
			if(processarArquivo(inputStream, nomeArquivo)){
				out.write(RESPOSTA_OK);
			}else{
				response.setContentLength(1);
				out.write(RESPOSTA_ERRO);
			}
			
			deletarDiretorio(fileDiretorio);
			out.flush();
			
			System.out.println("FIM: Finalizando roteiro atualizacao cadastral ");
		} catch (Exception e) {
			deletarDiretorio(fileDiretorio);
			e.printStackTrace();
			System.out.println("ERROR: Ao finalizar roteiro  atualizacao cadastral");
			response.setContentLength(1);

			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}

	/**
	 * 
	 * Atualiza o arquivo texto para situacao "EM CAMPO"
	 * 
	 * @author Anderson Cabral
	 * @date 12/12/2012
	 * 
	 * @param idArquivoTextoVisitaCampo
	 * @param response
	 * @param out
	 * 
	 * @throws IOException
	 */
	private void consultarArquivoFinalizado(HttpServletResponse response, OutputStream out,
			long idAtualizacaoCadastralArquivoTexto) throws IOException {
		try {
			// Atualiza o arquivo em campo
			System.out.println("INICIO: Consulta a situacao do arquivo ID: " + idAtualizacaoCadastralArquivoTexto);

			FiltroArquivoTextoAtualizacaoCadastralDM filtro = new FiltroArquivoTextoAtualizacaoCadastralDM();
			filtro.adicionarParametro(new ParametroSimples(	FiltroArquivoTextoAtualizacaoCadastralDM.ID_PARAMETRO_TABELA_ATUALIZACAO_CADASTRO,
				idAtualizacaoCadastralArquivoTexto));			

			Collection<ArquivoTextoAtualizacaoCadastralDM> colArquivoTexto = this.getFachada().pesquisar(filtro, 
				ArquivoTextoAtualizacaoCadastralDM.class.getName());

			ArquivoTextoAtualizacaoCadastralDM atualizacaoCadastralArquivoTexto = (ArquivoTextoAtualizacaoCadastralDM) 
					Util.retonarObjetoDeColecao(colArquivoTexto);

			if (atualizacaoCadastralArquivoTexto != null
					&& atualizacaoCadastralArquivoTexto.getSituacaoTransmissao().getId().equals(
						SituacaoTransmissaoLeitura.EM_CAMPO)) {

				System.out.println("FIM: Consulta a situacao do arquivo ID: " + idAtualizacaoCadastralArquivoTexto);
				out.write(RESPOSTA_OK);
				out.flush();
			} else {
				System.out.println("ERROR: Ao Consulta situacao do arquivo em campo ID:"
						+ idAtualizacaoCadastralArquivoTexto);
				response.setContentLength(1);

				out.write(RESPOSTA_ERRO);
				out.flush();
			}

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Ao consultar situacao do arquivo em campo ID:" + idAtualizacaoCadastralArquivoTexto);
			response.setContentLength(1);

			out.write(RESPOSTA_ERRO);
			out.flush();
		}
	}
	
	/**
	 * M�todo respons�vel por
	 * crias os diret�rios necess�rios
	 * no servidor para processamento
	 * dos dados
	 * @author Jonathan Marcos
	 * @since 07/10/2014
	 * @param nomePasta
	 */
	private void criarDiretorios(String nomePasta){
		String[] diretorios = {
				ConstantesSistema.DIRETORIO_ATUALIZACAO_CADASTRAL_BIN,
				ConstantesSistema.DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+"/"+nomePasta
		};
		
		File file = null;
		for(String diretorio : diretorios){
			file = new File(diretorio);
			if(!file.exists()){
				file.mkdirs();
			}
		}
	}
	
	/**
	 * M�todo respons�vel por
	 * deletar a pasta apos 
	 * o processamento
	 * @author Jonathan Marcos
	 * @since 07/10/2014
	 * @param fileDiretorio
	 */
	private boolean deletarDiretorio(File fileDiretorio){
		if(fileDiretorio.isDirectory()){
			String[] children = fileDiretorio.list();
			for (int i=0; i<children.length; i++) { 
	               boolean success = deletarDiretorio(new File(fileDiretorio, children[i]));
	                if (!success) {
	                    return false;
	                }
	            }
		}
		return fileDiretorio.delete();
	}
	
	/**
	 * M�todo respons�vel por
	 * salvar o arquivo zip
	 * @author Jonathan Marcos 
	 * @since 30/09/2014
	 * @param inputStream
	 * @param nomeArquivo
	 * @return boolean
	 */
	private boolean salvarArquivo(InputStream inputStream,String nomeArquivo){
		boolean salvouArquivo = true;
		try {
			File file = new File(ConstantesSistema.DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+"/"
							+nomeArquivo+"/"+nomeArquivo);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			
			byte[] buffer = new byte[inputStream.available()];
            int len;
			
			while ((len = inputStream.read(buffer)) != -1) {
				fileOutputStream.write(buffer,0,len);
    	    }
			
			fileOutputStream.flush();
			fileOutputStream.close();
		} catch (Exception e) {
			salvouArquivo = false;
		}
		return salvouArquivo;
	}
	
	/**
	 * M�todo respons�vel por
	 * descompactar os dados
	 * do arquivo zip
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param nomeArquivo
	 * @return boolean
	 */
	private boolean descompactarArquivo(String nomeArquivo){
		boolean descompactouArquivos = true;
		byte[] buffer = new byte[1024];
		String caminhoPadrao = ConstantesSistema.DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+
				"/"+nomeArquivo+"/";
		try {
			// Cria o input do arquivo ZIP 
			ZipInputStream zinstream = new ZipInputStream(new FileInputStream(
					caminhoPadrao+nomeArquivo)); 
			// Pega a proxima entrada do arquivo 
			ZipEntry zentry = zinstream.getNextEntry(); 
			// Enquanto existir entradas no ZIP 
			while (zentry != null) { 
				// Pega o nome da entrada 
				String entryName = zentry.getName(); 
				// Cria o output do arquivo , Sera extraido onde esta rodando a classe 
				FileOutputStream outstream = new FileOutputStream(caminhoPadrao+entryName); 
				int n; 
				// Escreve no arquivo 
				while ((n = zinstream.read(buffer)) > -1) { 
					outstream.write(buffer, 0, n); 
				} 
				// Fecha arquivo 
				outstream.close(); 
				// Fecha entrada e tenta pegar a proxima 
				zinstream.closeEntry(); 
				zentry = zinstream.getNextEntry(); 
			} 
			// Fecha o zip como um todo 
			zinstream.close();
		} catch (Exception e) {
			descompactouArquivos = false;
		}
		return descompactouArquivos;
	}
	
	/**
	 * M�todo respons�vel por
	 * processar os o arquivo
	 * zip
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param inputStream
	 * @param nomeArquivo
	 */
	private boolean processarArquivo(InputStream inputStream,String nomeArquivo){
		File diretorios = new File(ConstantesSistema.
				DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+"/"+nomeArquivo);
		if(nomeArquivo.toUpperCase().endsWith(".ZIP")){
			if(salvarArquivo(inputStream,nomeArquivo) && 
					descompactarArquivo(nomeArquivo)){
				// Processa os dados
				Fachada.getInstancia().
					processarRecebimentoDadosImoveisTablet(nomeArquivo);
				return true;
			}else{
				deletarDiretorio(diretorios);
				return false;
			}
		}else{
			deletarDiretorio(diretorios);
			return false;
		}
	}
	
	/**
	 * M�todo respons�vel por
	 * descomprimir o array de 
	 * byte
	 * @author Jonathan Marcos
	 * @since 14/10/2014
	 * @param bytesGzip
	 * @param tamanhoJsonAntesCompressao
	 * @return byte[]
	 */
	public static byte[] descomprimirJsonBytes(final byte[] bytesGzip,int tamanhoJsonAntesCompressao) {
	    byte[] ungzipped = new byte[0];
	    try {
	        final GZIPInputStream inputStream = new GZIPInputStream(new ByteArrayInputStream(bytesGzip));
	        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bytesGzip.length);
	        final byte[] buffer = new byte[tamanhoJsonAntesCompressao];
	        int bytesRead = 0;
	        while (bytesRead != -1) {
	            bytesRead = inputStream.read(buffer, 0, tamanhoJsonAntesCompressao);
	            if (bytesRead != -1) {
	                byteArrayOutputStream.write(buffer, 0, bytesRead);
	            }
	        }
	        ungzipped = byteArrayOutputStream.toByteArray();
	        inputStream.close();
	        byteArrayOutputStream.close();
	    } catch (IOException e) {
	    }
	    return ungzipped;
	}
}