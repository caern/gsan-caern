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
package gcom.gui.atendimentopublico.registroatendimento;

import gcom.atendimentopublico.registroatendimento.Tramite;
import gcom.atendimentopublico.registroatendimento.bean.RAFiltroHelper;
import gcom.gui.GcomAction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC0503]Tramitar Conjunto de Registro de Atendimento
 * 
 * @author Ana Maria
 * @date 15/01/2007
 */
public class LimparRegistroAtendimentoTramitacaoAction extends GcomAction {
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

		// Seta a a��o de retorno
		ActionForward retorno = actionMapping
				.findForward("tramitacaoRegistroAtendimento");

		ConjuntoTramitacaoRaActionForm form = (ConjuntoTramitacaoRaActionForm) actionForm;

		// Obt�m a sess�o
		HttpSession sessao = httpServletRequest.getSession(false);

		String[] ids = (String[]) form.getIdRegistrosTramitacao();

		Collection tramites = null;
		// Recupera a cole��o de tramite da sess�o, caso exista, ou cria uma
		// nova
		if (sessao.getAttribute("tramites") != null
				&& !sessao.getAttribute("tramites").equals("")) {
			tramites = (Collection) sessao.getAttribute("tramites");
		} else {
			tramites = new ArrayList();
		}

		boolean achou = false;

		if (ids != null && ids.length != 0) {

			for (int i = 0; i < ids.length; i++) {

				achou = false;
				Tramite tramiteColecao = null;
				String[] idsTramitacao = ids[i].split(";");
				// Verifica a exist�ncia da cole��o na sess�o.
				if (tramites != null && !tramites.isEmpty()) {
					Iterator iteratorTramite = tramites.iterator();
					while (iteratorTramite.hasNext()) {
						tramiteColecao = (Tramite) iteratorTramite.next();
						// Caso exita na colec�o da sess�o o registro de
						// atendimento selecionado atualiza o tramite existente.
						if (tramiteColecao.getRegistroAtendimento().getId()
								.equals(Integer.parseInt(idsTramitacao[0]))) {
							// Unidade Destino
							tramiteColecao
									.setUnidadeOrganizacionalDestino(null);
							tramiteColecao.setParecerTramite(null);
							achou = true;
							break;
						}
					}
				}
				if (achou) {
					tramites.remove(tramiteColecao);
					// Unidade Destino
					atualizarUnidadeDestinoColecao(Integer
							.parseInt(idsTramitacao[0]), sessao);
				}
			}
		}

		sessao.setAttribute("tramites", tramites);

		httpServletRequest.setAttribute("fecharPopup", "OK");

		sessao.removeAttribute("ConjuntoTramitacaoRaActionForm");

		return retorno;
	}

	/**
	 * Atualiza a unidade destino no filtro da pesquisa
	 * 
	 * @param idRA
	 * @param sessao
	 * @param unidadeDestino
	 */
	private void atualizarUnidadeDestinoColecao(Integer idRA, HttpSession sessao) {
		Collection colecaoRAHelper = (Collection) sessao
				.getAttribute("colecaoRAHelper");
		RAFiltroHelper helper = null;
		Iterator iteratorColecaoRaHelper = colecaoRAHelper.iterator();
		while (iteratorColecaoRaHelper.hasNext()) {
			helper = (RAFiltroHelper) iteratorColecaoRaHelper.next();
			if (helper.getRegistroAtendimento().getId().equals(idRA)) {
				helper.setUnidadeDestino(null);
				break;
			}
		}
	}
}
