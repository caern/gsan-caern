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
import gcom.faturamento.FaturamentoAtividadeCronograma;
import gcom.faturamento.FaturamentoGrupoCronogramaMensal;
import gcom.faturamento.FiltroFaturamentoAtividadeCronograma;
import gcom.gui.GcomAction;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class FiltrarComandoAtividadeFaturamentoAction extends GcomAction {

    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        //Seta o mapeamento de retorno
        ActionForward retorno = actionMapping
                .findForward("filtrarComandoAtividadeFaturamento");

        //Carrega a instancia da fachada
        Fachada fachada = Fachada.getInstancia();

        //Carrega o objeto sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        
        //Limpando todos os objetos colocados na sess�o
        //--------------------------------------------------------------------
        sessao.removeAttribute("dataCorrente");
        sessao.removeAttribute("exibirCampoVencimentoGrupo");
        sessao.removeAttribute("faturamentoAtividadeCronograma");
        sessao.removeAttribute("colecaoFaturamentoAtividadeCronogramaRota");
        sessao.removeAttribute("colecaoRotasSelecionadas");
        sessao.removeAttribute("colecaoRotasSelecionadasUsuario");
        sessao
                .removeAttribute("colecaoFaturamentoAtividadeCronogramaRotaUniao");
        sessao.removeAttribute("PesquisarActionForm");
        sessao.removeAttribute("InserirComandoAtividadeFaturamentoActionForm");
        sessao.removeAttribute("statusWizard");
        //--------------------------------------------------------------------
        
        
        FiltroFaturamentoAtividadeCronograma filtroFaturamentoAtividadeCronograma = new FiltroFaturamentoAtividadeCronograma();
        filtroFaturamentoAtividadeCronograma.setConsultaSemLimites(true);
        
        //filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo.anoMesReferencia");
        filtroFaturamentoAtividadeCronograma.adicionarCaminhoParaCarregamentoEntidade("faturamentoGrupoCronogramaMensal.faturamentoGrupo");
        
        // Retorna uma lista de atividades de faturamento comandadas e ainda n�o
        // realizadas
        /*Collection colecaoAtividadesAtualizacao = fachada
                .buscarAtividadeComandadaNaoRealizada(filtroFaturamentoAtividadeCronograma);*/
        
		// 1� Passo - Pegar o total de registros atrav�s de um count da consulta que aparecer� na tela
		Integer totalRegistros = fachada
				.buscarAtividadeComandadaNaoRealizadaCount();

		// 2� Passo - Chamar a fun��o de Pagina��o passando o total de registros
		retorno = this.controlarPaginacao(httpServletRequest, retorno,
				totalRegistros);

		// 3� Passo - Obter a cole��o da consulta que aparecer� na tela passando o numero de paginas
		// da pesquisa que est� no request
		Collection colecaoAtividadesAtualizacao = fachada
			.buscarAtividadeComandadaNaoRealizada((Integer) httpServletRequest
					.getAttribute("numeroPaginasPesquisa"));

        
        Iterator colecaoAtividadesAtualizacaoIt = colecaoAtividadesAtualizacao.iterator();
        Collection colecaoFaturamentoAtividadeCronograma = new Vector();
        Object[] arrayConteudoAtividade = new Object[6];
        FaturamentoAtividadeCronograma faturamentoAtividadeCronograma;
        FaturamentoAtividade faturamentoAtividade;
        FaturamentoGrupoCronogramaMensal faturamentoGrupoCronogramaMensal;
        Date comando, dataPrevista;
        
        while (colecaoAtividadesAtualizacaoIt.hasNext()){
        	arrayConteudoAtividade = (Object[]) colecaoAtividadesAtualizacaoIt.next();
        	
        	faturamentoAtividade = (FaturamentoAtividade) arrayConteudoAtividade[1];
        	faturamentoGrupoCronogramaMensal = (FaturamentoGrupoCronogramaMensal) arrayConteudoAtividade[4];
        	comando = (Date) arrayConteudoAtividade[2];
        	dataPrevista = (Date) arrayConteudoAtividade[3];
        	
        	faturamentoAtividadeCronograma = new  FaturamentoAtividadeCronograma();
        	faturamentoAtividadeCronograma.setId(new Integer(String.valueOf(arrayConteudoAtividade[0])));
        	faturamentoAtividadeCronograma.setFaturamentoAtividade(faturamentoAtividade);
        	faturamentoAtividadeCronograma.setComando(comando);
        	faturamentoAtividadeCronograma.setDataPrevista(dataPrevista);
        	faturamentoAtividadeCronograma.setFaturamentoGrupoCronogramaMensal(faturamentoGrupoCronogramaMensal);
        	colecaoFaturamentoAtividadeCronograma.add(faturamentoAtividadeCronograma);
        }

        sessao.setAttribute("colecaoAtividadesAtualizacao",
        		colecaoFaturamentoAtividadeCronograma);
        
        
        return retorno;
    }

}
