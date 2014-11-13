/*
* Copyright (C) 2007-2007 the GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
*
* This file is part of GSAN, an integrated service management system for Sanitation
*
* GSAN is free software; you can redistribute it and/or modify
* it under the terms of the GNU General Public License as published by
* the Free Software Foundation; either version 2 of the License.
*
* GSAN is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU General Public License for more details.
*
* You should have received a copy of the GNU General Public License
* along with this program; if not, write to the Free Software
* Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA 02111-1307, USA
*/

/*
* GSAN - Sistema Integrado de Gest�o de Servi�os de Saneamento
* Copyright (C) <2007> 
* Adriano Britto Siqueira
* Alexandre Santos Cabral
* Ana Carolina Alves Breda
* Ana Maria Andrade Cavalcante
* Aryed Lins de Ara�jo
* Bruno Leonardo Rodrigues Barros
* Carlos Elmano Rodrigues Ferreira
* Cl�udio de Andrade Lira
* Denys Guimar�es Guenes Tavares
* Eduardo Breckenfeld da Rosa Borges
* Fab�ola Gomes de Ara�jo
* Fl�vio Leonardo Cavalcanti Cordeiro
* Francisco do Nascimento J�nior
* Homero Sampaio Cavalcanti
* Ivan S�rgio da Silva J�nior
* Jos� Edmar de Siqueira
* Jos� Thiago Ten�rio Lopes
* K�ssia Regina Silvestre de Albuquerque
* Leonardo Luiz Vieira da Silva
* M�rcio Roberto Batista da Silva
* Maria de F�tima Sampaio Leite
* Micaela Maria Coelho de Ara�jo
* Nelson Mendon�a de Carvalho
* Newton Morais e Silva
* Pedro Alexandre Santos da Silva Filho
* Rafael Corr�a Lima e Silva
* Rafael Francisco Pinto
* Rafael Koury Monteiro
* Rafael Palermo de Ara�jo
* Raphael Veras Rossiter
* Roberto Sobreira Barbalho
* Rodrigo Avellar Silveira
* Rosana Carvalho Barbosa
* S�vio Luiz de Andrade Cavalcante
* Tai Mu Shih
* Thiago Augusto Souza do Nascimento
* Tiago Moreno Rodrigues
* Vivianne Barbosa Sousa
*
* Este programa � software livre; voc� pode redistribu�-lo e/ou
* modific�-lo sob os termos de Licen�a P�blica Geral GNU, conforme
* publicada pela Free Software Foundation; vers�o 2 da
* Licen�a.
* Este programa � distribu�do na expectativa de ser �til, mas SEM
* QUALQUER GARANTIA; sem mesmo a garantia impl�cita de
* COMERCIALIZA��O ou de ADEQUA��O A QUALQUER PROP�SITO EM
* PARTICULAR. Consulte a Licen�a P�blica Geral GNU para obter mais
* detalhes.
* Voc� deve ter recebido uma c�pia da Licen�a P�blica Geral GNU
* junto com este programa; se n�o, escreva para Free Software
* Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA
* 02111-1307, USA.
*/  
package gcom.gui.util.tabelaauxiliar.abreviada;

import gcom.arrecadacao.banco.Banco;
import gcom.atendimentopublico.LigacaoOrigem;
import gcom.atendimentopublico.ordemservico.EquipamentosEspeciais;
import gcom.cadastro.cliente.OrgaoExpedidorRg;
import gcom.cadastro.endereco.LogradouroTipo;
import gcom.cadastro.endereco.LogradouroTitulo;
import gcom.cadastro.geografico.UnidadeFederacao;
import gcom.cadastro.imovel.Despejo;
import gcom.cadastro.imovel.PavimentoRua;
import gcom.cadastro.localidade.Zeis;
import gcom.gui.Funcionalidade;
import gcom.gui.util.tabelaauxiliar.DadosTelaTabelaAuxiliar;
import gcom.micromedicao.hidrometro.HidrometroLocalArmazenagem;
import gcom.operacional.FonteCaptacao;
import gcom.operacional.SistemaAbastecimento;
import gcom.seguranca.transacao.Tabela;
import gcom.util.HibernateUtil;
import gcom.util.SistemaException;
import gcom.util.tabelaauxiliar.TabelaAuxiliar;
import gcom.util.tabelaauxiliar.abreviada.TabelaAuxiliarAbreviada;

import java.util.HashMap;

/**
 * Title: GCOM
 * Description: Sistema de Gest�o Comercial
 * Copyright: Copyright (c) 2004
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * @version 1.0
 */

public class DadosTelaTabelaAuxiliarAbreviada extends DadosTelaTabelaAuxiliar {
	
	private static HashMap telas = new HashMap();
	private static HashMap configuracaoParametrosTelas = new HashMap();

	/**
	 * Este m�todo busca um map de funcionalidades cadastradas e cria uma
	 * inst�ncia da funcionalidade para ser usada na Tabela Auxiliar
	 * 
	 * @param nome
	 * @return
	 */
	public static DadosTelaTabelaAuxiliarAbreviada obterDadosTelaTabelaAuxiliar(String nome) {
		
		System.out.println("-----------Adicionando itens");
		// Verifica se a funcionalidade desejada j� foi instanciada e j� est� no
		// cache
		if (!telas.containsKey(nome)) {
			String[] configuracaoTela = (String[]) configuracaoParametrosTelas.get(nome);

			try {
				// Cria a inst�ncia do objeto DadosTelaTabelaAuxiliar
				DadosTelaTabelaAuxiliarAbreviada dadosTela = 
					new DadosTelaTabelaAuxiliarAbreviada(configuracaoTela[1], 
						(TabelaAuxiliarAbreviada) Class.forName(configuracaoTela[0]).newInstance(),
						configuracaoTela[2], 
						nome);

				// Coloca a inst�ncia criada no map que representa o cache com
				// as inst�ncia j� criadas
				telas.put(nome, dadosTela);

				return dadosTela;
			} catch (ClassNotFoundException ex) {
				throw new SistemaException();
			} catch (IllegalAccessException ex) {
				throw new SistemaException();
			} catch (InstantiationException ex) {
				throw new SistemaException();
			}
		} else {
			// Se o a funcionalidade j� estiver no cache, ent�o ela � retornada
			// sem a necessidade de passar pelo m�todo
			return (DadosTelaTabelaAuxiliarAbreviada) telas.get(nome);
		}
	}
	

	static {
			
				
 		configuracaoParametrosTelas.put("hidrometroLocalArmazenagem",
			new String[] { 
 				HidrometroLocalArmazenagem.class.getName(),
				"Local de Armazenagem do Hidr�metro",
				Funcionalidade.TELA_HIDROMETRO_LOCAL_ARMAZENAGEM });

		configuracaoParametrosTelas.put("tipoPavimentoRua", 
			new String[] {
				PavimentoRua.class.getName(), 
				"Tipo Pavimento Rua",
				Funcionalidade.TELA_TIPO_PAVIMENTO_RUA });

		configuracaoParametrosTelas.put("zeis",
			new String[] { 
				Zeis.class.getName(),
				"Zeis",
				Funcionalidade.TELA_ZEIS });
		
		configuracaoParametrosTelas.put("sistemaAbastecimento",
			new String[] { 
				SistemaAbastecimento.class.getName(),
				"Sistema de Abastecimento",
				Funcionalidade.TELA_SISTEMA_ABASTECIMENTO });
		
		configuracaoParametrosTelas.put("despejo",
			new String[] { 
				Despejo.class.getName(),
				"Despejo",
				Funcionalidade.TELA_DESPEJO });
		
		configuracaoParametrosTelas.put("orgaoExpeditorRg",
			new String[] { 
				OrgaoExpedidorRg.class.getName(),
				"�rgao Expeditor do RG",
				Funcionalidade.TELA_ORGAO_EXPEDITOR_RG });
		
		configuracaoParametrosTelas.put("unidadeFederacao",
			new String[] { 
				UnidadeFederacao.class.getName(),
				"Unidade da Federa��o",
				Funcionalidade.TELA_UNIDADE_FEDERACAO });
		
		configuracaoParametrosTelas.put("equipamentosEspeciais",
			new String[] { 
				EquipamentosEspeciais.class.getName(),
				"Equipamento Especial",
				Funcionalidade.TELA_EQUIPAMENTOS_ESPECIAIS});
		
		configuracaoParametrosTelas.put("banco",
			new String[] { 
				Banco.class.getName(),
				"Banco",
				Funcionalidade.TELA_BANCO});

		configuracaoParametrosTelas.put("tabela", 
			new String[] { 
				Tabela.class.getName(), 
				"Tabela",
				Funcionalidade.TELA_TABELA });				
	
		configuracaoParametrosTelas.put("sistemaAbastecimento", 
			new String[] { 
				SistemaAbastecimento.class.getName(), 
				"Sistema de Abastecimento",
				Funcionalidade.TELA_SISTEMA_ABASTECIMENTO });	
	
		configuracaoParametrosTelas.put("ligacaoOrigem", 
			new String[] { 
				LigacaoOrigem.class.getName(), 
				"Origem da Liga��o",
				Funcionalidade.TELA_LIGACAO_ORIGEM });

		configuracaoParametrosTelas.put("logradouroTipo", 
			new String[] {
				LogradouroTipo.class.getName(), 
				"Tipo do Logradouro",
				Funcionalidade.TELA_LOGRADOURO_TIPO });

		configuracaoParametrosTelas.put("tituloLogradouro", 
			new String[] {
				LogradouroTitulo.class.getName(), 
				"Titulo do Logradouro",
				Funcionalidade.TELA_LOGRADOURO_TITULO });
		
		configuracaoParametrosTelas.put("fonteCaptacao", 
			new String[] {
				FonteCaptacao.class.getName(), 
				"Fonte de Capta��o",
				Funcionalidade.TELA_FONTE_CAPTACAO });
		
		
	}

	/**
	 * Construtor da classe DadosTelaTabelaAuxiliarAbreviada
	 * 
	 * @param titulo
	 *            Descri��o do par�metro
	 * @param tabela
	 *            Descri��o do par�metro
	 * @param funcionalidadeTabelaAux
	 *            Descri��o do par�metro
	 */
	protected DadosTelaTabelaAuxiliarAbreviada(String titulo,
			TabelaAuxiliar tabela, String funcionalidadeTabelaAux,
			String nomeParametroFuncionalidade) {
		super(titulo, tabela, funcionalidadeTabelaAux,
				nomeParametroFuncionalidade);
	}
	
	
	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param dados
	 *            Descri��o do par�metro
	 */
	public static void adicionarDadosTela(DadosTelaTabelaAuxiliar dados) {
		DadosTelaTabelaAuxiliar.adicionarDadosTela(dados);
	}

	/**
	 * Retorna o valor de dadosTela
	 * 
	 * @param nome
	 *            Descri��o do par�metro
	 * @return O valor de dadosTela
	 */
	public static DadosTelaTabelaAuxiliar getDadosTela(String nome) {
		return DadosTelaTabelaAuxiliar.getDadosTela(nome);
	}

	/**
	 * M�todo sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxManter() {
		return super.getFuncionalidadeTabelaAuxManter().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_MANTER,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_MANTER);
	}
	
	/**
	 * M�todo sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxFiltrar() {
		return super.getFuncionalidadeTabelaAuxFiltrar().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_FILTRAR,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_FILTRAR);
	}

	/**
	 * M�todo sobrescrito que retorna o valor de funcionalidadeTabelaAuxManter
	 * 
	 * @return O valor de funcionalidadeTabelaAuxManter
	 */
	public String getFuncionalidadeTabelaAuxInserir() {
		return super.getFuncionalidadeTabelaAuxInserir().replaceAll(
				Funcionalidade.TABELA_AUXILIAR_INSERIR,
				Funcionalidade.TABELA_AUXILIAR_ABREVIADA_INSERIR);
	}

	/**
	 * Retorna o valor de tamanhoMaximoCampo
	 * 
	 * @return O valor de tamanhoMaximoCampo
	 */
	public int getTamanhoMaximoDescricaoAbreviada() {
		return HibernateUtil.getColumnSize(this.getTabelaAuxiliar().getClass(),
				"descricaoAbreviada");
	}
	
	/**
	 * Retorna o valor de tabelaAuxiliar
	 * 
	 * @return O valor de tabelaAuxiliar
	 */
	public TabelaAuxiliar getTabela() {
		return  super.getTabelaAuxiliar();
	}
}