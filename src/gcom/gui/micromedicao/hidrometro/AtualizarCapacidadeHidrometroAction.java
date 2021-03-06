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
package gcom.gui.micromedicao.hidrometro;

import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.hidrometro.FiltroHidrometroCapacidade;
import gcom.micromedicao.hidrometro.HidrometroCapacidade;
import gcom.util.filtro.ParametroSimples;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class AtualizarCapacidadeHidrometroAction extends GcomAction {

	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o retorno
		ActionForward retorno = actionMapping.findForward("telaSucesso");

		// Obt�m a inst�ncia da fachada
		Fachada fachada = Fachada.getInstancia();

		// Mudar isso quando tiver esquema de seguran�a
		HttpSession sessao = httpServletRequest.getSession(false);

		AtualizarCapacidadeHidrometroActionForm atualizarCapacidadeHidrometroActionForm = (AtualizarCapacidadeHidrometroActionForm) actionForm;

		HidrometroCapacidade hidrometroCapacidade = (HidrometroCapacidade) sessao
				.getAttribute("hidrometroCapacidadeAtualizar");

		hidrometroCapacidade.setId(new Integer(
				atualizarCapacidadeHidrometroActionForm.getIdentificador()));
		hidrometroCapacidade
				.setDescricao(atualizarCapacidadeHidrometroActionForm
						.getDescricao());
		hidrometroCapacidade
				.setDescricaoAbreviada(atualizarCapacidadeHidrometroActionForm
						.getAbreviatura());
		hidrometroCapacidade.setLeituraMinimo(new Short(
				atualizarCapacidadeHidrometroActionForm.getNumMinimo()));
		hidrometroCapacidade.setLeituraMaximo((new Short(
				atualizarCapacidadeHidrometroActionForm.getNumMaximo())));
		hidrometroCapacidade
				.setIndicadorUso(atualizarCapacidadeHidrometroActionForm
						.getIndicadoruso());
		hidrometroCapacidade.setNumeroOrdem(new Short(
				atualizarCapacidadeHidrometroActionForm.getNumOrdem()));
		hidrometroCapacidade
				.setCodigoHidrometroCapacidade(atualizarCapacidadeHidrometroActionForm
						.getCodigo());

		String numMinimo = atualizarCapacidadeHidrometroActionForm
				.getNumMinimo();
		String numMaximo = atualizarCapacidadeHidrometroActionForm
				.getNumMaximo();
		String codigo = atualizarCapacidadeHidrometroActionForm.getCodigo();
		
		String numOrdem = atualizarCapacidadeHidrometroActionForm.getNumOrdem();

		Collection colecaoPesquisa = null;

		// O numero maximo de digitos de leitura do hidr�metro � obrigat�rio.
		if (numMaximo != null && !numMaximo.equalsIgnoreCase("")) {
			if (new Integer(numMaximo).intValue() < new Integer(numMinimo)
					.intValue()) {
				throw new ActionServletException(
						"atencao.numero_minimo_nao_pode_ser_maior_que_numero_maximo",
						null,
						"Numero maximo de digitos de leitura do hidr�metro");
			}
			hidrometroCapacidade.setLeituraMaximo(new Short(numMaximo));
		}

		// Verificar exist�ncia do c�digo da Capacidade do Hidrometro que n�o seja ele mesmo
		FiltroHidrometroCapacidade filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();

		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.CODIGO_HIDROMETRO_CAPACIDADE,
				hidrometroCapacidade.getCodigoHidrometroCapacidade()));

		// Verificar exist�ncia da Capacidade do Hidrometro
		colecaoPesquisa = fachada.pesquisar(filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			HidrometroCapacidade hidrometroCapacidadeBase = (HidrometroCapacidade) colecaoPesquisa
					.iterator().next();

			if ((hidrometroCapacidadeBase.getId().intValue() != hidrometroCapacidade
					.getId().intValue())
					&& (hidrometroCapacidadeBase
							.getCodigoHidrometroCapacidade()
							.equalsIgnoreCase(hidrometroCapacidade
									.getCodigoHidrometroCapacidade()))) {

				// Capacidade de hidrometro j� existe
				throw new ActionServletException(
						"atencao.pesquisa_capacidade_do_hidrometro_ja_cadastrada",
						null, codigo);
			}

		}
		
		//Verifica a Existencia de um Numero de Ordem J� existente na base que n�o seja ele mesmo
		
		filtroHidrometroCapacidade = new FiltroHidrometroCapacidade();
		
		filtroHidrometroCapacidade.adicionarParametro(new ParametroSimples(
				FiltroHidrometroCapacidade.NUMERO_ORDEM,
				hidrometroCapacidade.getNumeroOrdem()));

		// Verificar exist�ncia do numero de ordem da Capacidade do Hidrometro
		colecaoPesquisa = fachada.pesquisar(filtroHidrometroCapacidade,
				HidrometroCapacidade.class.getName());

		if (colecaoPesquisa != null && !colecaoPesquisa.isEmpty()) {

			HidrometroCapacidade hidrometroCapacidadeBase = (HidrometroCapacidade) colecaoPesquisa
					.iterator().next();

			if ((hidrometroCapacidadeBase.getId().intValue() != hidrometroCapacidade
					.getId().intValue())
					&& (hidrometroCapacidadeBase
							.getNumeroOrdem().toString()
							.equalsIgnoreCase(hidrometroCapacidade
									.getNumeroOrdem().toString()))) {

				// Numero de Ordem j� existe
				throw new ActionServletException(
						"atencao.pesquisa_numero_de_ordem_da_capacidade_do_hidrometro_ja_cadastrada",
						null, numOrdem);
			}

		}

		fachada.atualizarCapacidadeHidrometro(hidrometroCapacidade);

		montarPaginaSucesso(httpServletRequest,
				"Capacidade de Hidr�metro de c�digo "
						+ hidrometroCapacidade.getId().toString()
						+ " atualizado com sucesso.",
				"Realizar outra Manuten��o de Capacidade de Hid�metro",
				"exibirFiltrarCapacidadeHidrometroAction.do?menu=sim");
		return retorno;
	}
}
