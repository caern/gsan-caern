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
package gcom.gui.faturamento;

import gcom.fachada.Fachada;
import gcom.faturamento.FaturamentoAtividade;
import gcom.faturamento.FaturamentoGrupo;
import gcom.faturamento.FiltroFaturamentoAtividade;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class InserirComandoAtividadeFaturamentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping.findForward("telaSucesso");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);

        //Inst�ncia do formul�rio que est� sendo utilizado
        InserirComandoAtividadeFaturamentoActionForm inserirComandoAtividadeFaturamentoActionForm = 
        	(InserirComandoAtividadeFaturamentoActionForm) actionForm;

        // Grupo selecionado
        String grupoFaturamentoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getGrupoFaturamentoID();
        // Atividade selecionado
        String atividadeFaturamentoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getAtividadeFaturamentoID();

        if (grupoFaturamentoJSP == null
                || grupoFaturamentoJSP.equalsIgnoreCase(String
                        .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
            throw new ActionServletException(
                    "atencao.faturamento_grupo_nao_informado");
        }

        if (atividadeFaturamentoJSP == null
                || atividadeFaturamentoJSP.equalsIgnoreCase(String
                        .valueOf(ConstantesSistema.NUMERO_NAO_INFORMADO))) {
            throw new ActionServletException(
                    "atencao.faturamento_atividade_nao_informado");
        }

        // Carrega Grupo Selecionado
        // Cole��o que foi utilizada para carregar o listBos de exibi��o
        Collection colecaoFaturamentoGrupo = (Collection) sessao
                .getAttribute("colecaoGrupoFaturamento");

        FaturamentoGrupo faturamentoGrupo = obterFaturamentoGrupoSelecionado(
                grupoFaturamentoJSP, colecaoFaturamentoGrupo);

        // [FS0003] - Verificar exist�ncia do cronograma para o grupo
        fachada.verificarExistenciaCronogramaGrupo(faturamentoGrupo);

        // Carrega Atividade Selecionada
        FiltroFaturamentoAtividade filtroFaturamentoAtividade = new FiltroFaturamentoAtividade();

        filtroFaturamentoAtividade.adicionarParametro(new ParametroSimples(
                FiltroFaturamentoAtividade.ID, atividadeFaturamentoJSP));

        filtroFaturamentoAtividade.adicionarParametro(new ParametroSimples(
                FiltroFaturamentoAtividade.INDICADOR_USO,
                ConstantesSistema.INDICADOR_USO_ATIVO));

        Collection colecaoFaturamentoAtividade = fachada.pesquisar(
                filtroFaturamentoAtividade, FaturamentoAtividade.class
                        .getName());

        FaturamentoAtividade faturamentoAtividade = (FaturamentoAtividade) Util
                .retonarObjetoDeColecao(colecaoFaturamentoAtividade);

        // [FS0008] - Verificar exist�ncia da atividade no cronograma do grupo do m�s corrente
        //	fachada.verificarExistenciaCronogramaAtividadeGrupo(
        //        faturamentoAtividade, faturamentoGrupo);

        // Rotas selecionadas
        // [FS0006] - Verificar exist�ncia de rotas para o grupo
        Collection colecaoRotasGrupo = fachada
                .verificarExistenciaRotaGrupo(faturamentoGrupo);

        // [SB0002] - Verificar Situa��o da Atividade para a Rota
        // true = Rotas habilitadas
        Collection colecaoRotasSituacao = fachada
                .verificarSituacaoAtividadeRota(colecaoRotasGrupo,
                        faturamentoAtividade, faturamentoGrupo, true);

        //[FS0007] - Verificar sele��o de pelo menos uma rota habilitada
        if (colecaoRotasSituacao == null || colecaoRotasSituacao.isEmpty()) {
            throw new ActionServletException(
                    "atencao.pesquisa.nenhuma.rota_habilitada_grupo");
        }

        // Para auxiliar na formata��o de uma data
        SimpleDateFormat formatoData = new SimpleDateFormat("dd/MM/yyyy");

        // Data corrente para compara��o
        String dataCorrente = null;
        Date dataCorrenteGrupo = null;

        if ((faturamentoAtividade.getId().equals(FaturamentoAtividade.FATURAR_GRUPO) ||
        	faturamentoAtividade.getId().equals(FaturamentoAtividade.GERAR_ARQUIVO_LEITURA))
            && sessao.getAttribute("dataCorrente") != null) {

            dataCorrente = (String) sessao.getAttribute("dataCorrente");
            if (dataCorrente != null && !dataCorrente.equalsIgnoreCase("")) {

                try {
                    dataCorrenteGrupo = formatoData.parse(dataCorrente);
                } catch (ParseException ex) {
                    dataCorrenteGrupo = null;
                }
            }
        }
        
        
        //Data de vencimento do grupo na base para compara��o
        String dataVencimentoGrupoBase = null;
        //Date dataVencimentoGrupoBaseObjeto = null;
        
     // Data de vecimento do grupo
        String dataVencimentoGrupoJSP = inserirComandoAtividadeFaturamentoActionForm
                .getVencimentoGrupo();

        if ((faturamentoAtividade.getId().equals(FaturamentoAtividade.FATURAR_GRUPO) ||
            faturamentoAtividade.getId().equals(FaturamentoAtividade.GERAR_ARQUIVO_LEITURA))
            && sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {

        	dataVencimentoGrupoBase = (String) sessao.getAttribute("exibirCampoVencimentoGrupo");
            if (dataVencimentoGrupoBase != null && !dataVencimentoGrupoBase.equalsIgnoreCase("")) {

                /*try {
                	dataVencimentoGrupoBaseObjeto = formatoData.parse(dataVencimentoGrupoBase);
                } catch (ParseException ex) {
                	dataVencimentoGrupoBaseObjeto = null;
                }*/
            }
           
            if (dataVencimentoGrupoJSP == null || dataVencimentoGrupoJSP.equals("")){
            	throw new ActionServletException(
                        "atencao.data_vencimento_obrigatoria");            
        }

        
        
        
        }

        String faturamentoGrupoAnoMes = ""+faturamentoGrupo.getAnoMesReferencia();
        faturamentoGrupoAnoMes = Util.formatarAnoMesParaMesAno(faturamentoGrupoAnoMes);
       
        Date dataVencimentoGrupo = null;

        if ((faturamentoAtividade.getId().equals(FaturamentoAtividade.FATURAR_GRUPO) ||
             faturamentoAtividade.getId().equals(FaturamentoAtividade.GERAR_ARQUIVO_LEITURA))
             && sessao.getAttribute("exibirCampoVencimentoGrupo") != null) {

            if (dataVencimentoGrupoJSP != null
                    && !dataVencimentoGrupoJSP.equalsIgnoreCase("")) {
            	
            	 String mesAnoVencimentoGrupo = dataVencimentoGrupoJSP.substring(3,10);
                 Integer diferencaMeses = Util.getDiferencaMeses(faturamentoGrupoAnoMes, mesAnoVencimentoGrupo);
                 
                 if (diferencaMeses > 3){
                 	
                 	 throw new ActionServletException(
                              "atencao.data_vencimento_superior_permitido");
                 }

                try {
                    dataVencimentoGrupo = formatoData
                            .parse(dataVencimentoGrupoJSP);
                    if (dataCorrenteGrupo.after(dataVencimentoGrupo)) {
                        throw new ActionServletException(
                                "atencao.faturamento_data_vencimento_grupo_menor",
                                null, dataCorrente);
                    } 
                } catch (ParseException ex) {
                    dataVencimentoGrupo = null;
                }
            }
        }

        // O sistema inclui o comando
        Integer faturamentoAtividadeCronogramaId = fachada.inserirComandoAtividadeFaturamento(faturamentoGrupo,
        		faturamentoAtividade, colecaoRotasSituacao,
                	dataVencimentoGrupo, this.getUsuarioLogado(httpServletRequest));
        
        montarPaginaSucesso(httpServletRequest,
                "Comando da Atividade de Faturamento " + faturamentoAtividade.getDescricao() + 
                " do " + faturamentoGrupo.getDescricaoAbreviada() + 
                " refer�ncia " + Util.formatarAnoMesParaMesAno(faturamentoGrupo.getAnoMesReferencia()) + 
                " inserido com sucesso.",
                "Inserir outro Comando de Atividade de Faturamento",
                "exibirInserirComandoAtividadeFaturamentoAction.do",
                "exibirAtualizarComandoAtividadeFaturamentoAction.do?faturamentoAtividadeCronogramaID="
                + faturamentoAtividadeCronogramaId, "Atualizar Comando de Atividade de Faturamento Inserido");
        
        
        //Removendo os objetos da sess�o
        sessao.removeAttribute("dataCorrente");
        sessao.removeAttribute("exibirCampoVencimentoGrupo");
        sessao.removeAttribute("colecaoGrupoFaturamento");
        sessao.removeAttribute("colecaoGrupoFaturamento");

        return retorno;
    }

    /**
     * Retorna o objeto FaturamentoGrupo selecionado
     * 
     * @param id
     * @param colecao
     * @return
     */
    private FaturamentoGrupo obterFaturamentoGrupoSelecionado(String id,
            Collection colecao) {
        FaturamentoGrupo retorno = null;
        Iterator colecaoIterator = colecao.iterator();

        while (colecaoIterator.hasNext()) {
            retorno = (FaturamentoGrupo) colecaoIterator.next();

            if (retorno.getId().equals(new Integer(id))) {
                break;
            }
        }

        return retorno;
    }
}