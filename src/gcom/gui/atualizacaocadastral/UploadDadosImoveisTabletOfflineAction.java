package gcom.gui.atualizacaocadastral;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

/**
 * @author Jonathan Marcos
 * @since 25/09/2014
 */
public class UploadDadosImoveisTabletOfflineAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,ActionForm actionForm, 
			HttpServletRequest httpServletRequest,HttpServletResponse httpServletResponse){
		
		// Retorno
		ActionForward actionForward = 
				actionMapping.findForward("telaSucesso");
		
		RecepcionarDadosImoveisTabletOfflineActionForm recepcionarDadosImoveisTabletOfflineActionForm = 
				(RecepcionarDadosImoveisTabletOfflineActionForm)actionForm;
		
		FormFile formFile = recepcionarDadosImoveisTabletOfflineActionForm.getFormFile();
		criarDiretorios(formFile.getFileName());
		processarArquivo(formFile);
		
		File fileDiretorio = new File(ConstantesSistema.
				DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+"/"+formFile.getFileName());
		deletarDiretorio(fileDiretorio);
		
		montarPaginaSucesso(httpServletRequest, "O arquivo " + formFile.getFileName()
				+ " foi processado com sucesso.", "","");
	
		return actionForward;
	}
	
	/**
	 * Método responsável por
	 * processar os o arquivo
	 * zip
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param formFile
	 */
	private void processarArquivo(FormFile formFile){
		File diretorios = new File(ConstantesSistema.
				DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+"/"+formFile.getFileName());
		if(formFile.getFileName().toUpperCase().endsWith(".ZIP")){
			String nomePasta = formFile.getFileName();
			if(salvarArquivo(nomePasta,formFile) && 
					descompactarArquivo(nomePasta, formFile.getFileName())){
				// Processa os dados
				Fachada.getInstancia().
					processarRecebimentoDadosImoveisTablet(nomePasta);
			}else{
				deletarDiretorio(diretorios);
				throw new ActionServletException("atencao.erro_descompactar_arquivo");
			}
		}else{
			deletarDiretorio(diretorios);
			throw new ActionServletException("atencao.arquivo_zip_invalido");
		}
	}
	
	/**
	 * Método responsável por
	 * crias os diretórios necessários
	 * no servidor para processamento
	 * dos dados
	 * @author Jonathan Marcos
	 * @since 25/09/2014
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
	 * Método responsável por
	 * salvar o arquivo zip
	 * @author Jonathan Marcos 
	 * @since 30/09/2014
	 * @param nomePasta
	 * @param formFile
	 * @return boolean
	 */
	private boolean salvarArquivo(String nomePasta,FormFile formFile){
		boolean salvouArquivo = true;
		try {
			File file = new File(ConstantesSistema.DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+"/"
							+nomePasta+"/"+formFile.getFileName());
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			InputStream inputStream = formFile.getInputStream();
			
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
	 * Método responsável por
	 * descompactar os dados
	 * do arquivo zip
	 * @author Jonathan Marcos
	 * @since 30/09/2014
	 * @param nomePasta
	 * @param nomeArquivo
	 * @return boolean
	 */
	private boolean descompactarArquivo(String nomePasta,String nomeArquivo){
		boolean descompactouArquivos = true;
		byte[] buffer = new byte[1024];
		String caminhoPadrao = ConstantesSistema.DIRETORIO_ATUALIZACAO_CADASTRAL_BIN+
				"/"+nomePasta+"/";
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
	 * Método responsável por
	 * deletar a pasta apos 
	 * o processamento
	 * @author Jonathan Marcos
	 * @since 30/09/2014
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
}