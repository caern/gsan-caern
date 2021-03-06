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
package gcom.gui.relatorio.faturamento;

import gcom.gerencial.bean.InformarDadosGeracaoRelatorioConsultaHelper;
import gcom.relatorio.ExibidorProcessamentoTarefaRelatorio;
import gcom.relatorio.RelatorioVazioException;
import gcom.relatorio.faturamento.RelatorioAnaliseFaturamento;
import gcom.seguranca.acesso.usuario.Usuario;
import gcom.tarefa.TarefaRelatorio;
import gcom.util.Util;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * <p>
 * Title: GCOM
 * </p>
 * <p>
 * Description: Sistema de Gest�o Comercial
 * </p>
 * <p>
 * Copyright: Copyright (c) 2004
 * </p>
 * <p>
 * Company: COMPESA - Companhia Pernambucana de Saneamento
 * </p>
 * 
 * @author Fernanda Paiva
 * @version 1.0
 */

public class GerarRelatorioAnaliseFaturamentoAction extends
		ExibidorProcessamentoTarefaRelatorio {

	/**
	 * < <Descri��o do m�todo>>
	 * 
	 * @param actionMapping
	 *            Descri��o do par�metro
	 * @param actionForm
	 *            Descri��o do par�metro
	 * @param httpServletRequest
	 *            Descri��o do par�metro
	 * @param httpServletResponse
	 *            Descri��o do par�metro
	 * @return Descri��o do retorno
	 */

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// cria a vari�vel de retorno
		ActionForward retorno = null;

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		Collection colecaoAnaliseFaturamento = (Collection) sessao
				.getAttribute("colecaoAnaliseFaturamento");

		InformarDadosGeracaoRelatorioConsultaHelper informarDadosGeracaoRelatorioConsultaHelper = (InformarDadosGeracaoRelatorioConsultaHelper) sessao
				.getAttribute("informarDadosGeracaoRelatorioConsultaHelper");

		// Inicio da parte que vai mandar os parametros para o relat�rio

		String mesAnoFaturamento = (String) informarDadosGeracaoRelatorioConsultaHelper.getAnoMesReferencia().toString();
		String descricao = (String) informarDadosGeracaoRelatorioConsultaHelper	.getDescricaoOpcaoTotalizacao();
		Integer tipoAnalise = (Integer) informarDadosGeracaoRelatorioConsultaHelper.getTipoAnaliseFaturamento();
		

		// cria uma inst�ncia da classe do relat�rio
		RelatorioAnaliseFaturamento relatorioAnaliseFaturamento = new RelatorioAnaliseFaturamento(
				(Usuario) (httpServletRequest.getSession(false))
						.getAttribute("usuarioLogado"));
		
		relatorioAnaliseFaturamento.addParametro("mesAnoFaturamento", Util.formatarAnoMesParaMesAno(mesAnoFaturamento));
		relatorioAnaliseFaturamento.addParametro("descricao", descricao);		
		relatorioAnaliseFaturamento.addParametro("tipoAnalise", tipoAnalise);		
		relatorioAnaliseFaturamento.addParametro("tipoQuebra", descricao);
		relatorioAnaliseFaturamento.addParametro("colecaoAnaliseFaturamento",colecaoAnaliseFaturamento);
		relatorioAnaliseFaturamento.addParametro("informarDadosGeracaoRelatorioConsultaHelper",informarDadosGeracaoRelatorioConsultaHelper);
		
		
		
		//----------------------------------------------------------------------------------------------------------------
        // Alterado por : Yara T. Souza.
		// Data : 26/08/2008
		//----------------------------------------------------------------------------------------------------------------
	     
		
		String localidade = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getLocalidade()!= null){
			 localidade =  (Integer) informarDadosGeracaoRelatorioConsultaHelper.getLocalidade().getId() + " - " + (String) informarDadosGeracaoRelatorioConsultaHelper.getLocalidade().getDescricao();
		}
			
		String gerenciaRegional = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getGerenciaRegional()!= null){
			gerenciaRegional = (String) informarDadosGeracaoRelatorioConsultaHelper.getGerenciaRegional().getNome();
		}
		String unidadeNegocio = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getUnidadeNegocio() != null){
			unidadeNegocio = (String) informarDadosGeracaoRelatorioConsultaHelper.getUnidadeNegocio().getNome();
		}
		String setorComercial = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial()!= null){
			setorComercial = informarDadosGeracaoRelatorioConsultaHelper.getSetorComercial().getDescricao();
		}
		
		String quadra = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getQuadra()!= null){
			quadra = informarDadosGeracaoRelatorioConsultaHelper.getQuadra().getDescricao();
		}
		
		String grupoFaturamento = "";
		if ( informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo() != null ) {
			grupoFaturamento = informarDadosGeracaoRelatorioConsultaHelper.getFaturamentoGrupo().getDescricao();
		}
		
		String rota = "";
		if(informarDadosGeracaoRelatorioConsultaHelper.getRota()!= null){
			rota = informarDadosGeracaoRelatorioConsultaHelper.getRota().getCodigo().intValue()+"";
		}
		
		Collection colecaoPerfilImovel = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoImovelPerfil();
		Collection colecaoLigacaoAgua = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoAguaSituacao();
		Collection colecaoLigacaoEsgoto = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoLigacaoEsgotoSituacao();
		Collection colecaoCategoria = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoCategoria();
		Collection colecaoEsferaPoder = (Collection) informarDadosGeracaoRelatorioConsultaHelper.getColecaoEsferaPoder();
    			
		relatorioAnaliseFaturamento.addParametro("localidade", localidade);
		relatorioAnaliseFaturamento.addParametro("gerenciaRegional", gerenciaRegional);
		relatorioAnaliseFaturamento.addParametro("unidadeNegocio", unidadeNegocio);
		relatorioAnaliseFaturamento.addParametro("setorComercial", setorComercial);
		relatorioAnaliseFaturamento.addParametro("quadra", quadra);
		relatorioAnaliseFaturamento.addParametro("grupoFaturamento", grupoFaturamento);
		relatorioAnaliseFaturamento.addParametro("rota", rota);

		relatorioAnaliseFaturamento.addParametro("colecaoPerfilImovel", colecaoPerfilImovel);
		relatorioAnaliseFaturamento.addParametro("colecaoLigacaoAgua", colecaoLigacaoAgua);
		relatorioAnaliseFaturamento.addParametro("colecaoLigacaoEsgoto", colecaoLigacaoEsgoto);
		relatorioAnaliseFaturamento.addParametro("colecaoCategoria",colecaoCategoria);
		relatorioAnaliseFaturamento.addParametro("colecaoEsferaPoder",colecaoEsferaPoder);
		
		//----------------------------------------------------------------------------------------------------------------
		

		String tipoRelatorio = httpServletRequest.getParameter("tipoRelatorio");
		if (tipoRelatorio == null) {
			tipoRelatorio = TarefaRelatorio.TIPO_PDF + "";
		}

		relatorioAnaliseFaturamento.addParametro("tipoFormatoRelatorio",
				Integer.parseInt(tipoRelatorio));
		try {
			retorno = processarExibicaoRelatorio(relatorioAnaliseFaturamento,
					tipoRelatorio, httpServletRequest, httpServletResponse,
					actionMapping);

		} catch (RelatorioVazioException ex) {
			// manda o erro para a p�gina no request atual
			reportarErros(httpServletRequest, "atencao.relatorio.vazio");

			// seta o mapeamento de retorno para a tela de aten��o de popup
			retorno = actionMapping.findForward("telaAtencaoPopup");

		}

		return retorno;
	}

}