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

import org.apache.struts.action.*;
import javax.servlet.http.*;

/**
 * Form do Manter D�btio a Cobrar do Imovel
 * 
 * @author Rafael Santos
 * @since 29/15/2005 [UC0184] Manter D�bito A Cobrar
 */
public class ManterDebitoACobrarActionForm extends ActionForm {
	private static final long serialVersionUID = 1L;
	private String inscricaoImovel;

	private String nomeCliente;

	private String situacaoAgua;

	private String situacaoEsgoto;

	private String codigoImovel;
	
	private String[] idDebitoACobrar;
	
	//RM 5759
	private String indicadorPermiteCancelarDebito;
	
	private String idRegistroAtendimento;
	
	private String descricaoEspecificacaoRA;
	
	
	private String usuarioPermissaoEspecial;
	
	private String registroAtendimentoCancelaDebito;

	public ActionErrors validate(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		/** @todo: finish this method, this is just the skeleton. */
		return null;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
	}

	/**
	 * @return Returns the codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel
	 *            The codigoImovel to set.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Returns the inscricaoImovel.
	 */
	public String getInscricaoImovel() {
		return inscricaoImovel;
	}

	/**
	 * @param inscricaoImovel
	 *            The inscricaoImovel to set.
	 */
	public void setInscricaoImovel(String inscricaoImovel) {
		this.inscricaoImovel = inscricaoImovel;
	}

	/**
	 * @return Returns the nomeCliente.
	 */
	public String getNomeCliente() {
		return nomeCliente;
	}

	/**
	 * @param nomeCliente
	 *            The nomeCliente to set.
	 */
	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	/**
	 * @return Returns the situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * @param situacaoAgua
	 *            The situacaoAgua to set.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * @return Returns the situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * @param situacaoEsgoto
	 *            The situacaoEsgoto to set.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * @return Returns the idDebitoACobrar.
	 */
	public String[] getIdDebitoACobrar() {
		return idDebitoACobrar;
	}

	/**
	 * @param idDebitoACobrar The idDebitoACobrar to set.
	 */
	public void setIdDebitoACobrar(String[] idDebitoACobrar) {
		this.idDebitoACobrar = idDebitoACobrar;
	}
	
	public String getIndicadorPermiteCancelarDebito() {
		return indicadorPermiteCancelarDebito;
	}

	public void setIndicadorPermiteCancelarDebito(
			String indicadorPermiteCancelarDebito) {
		this.indicadorPermiteCancelarDebito = indicadorPermiteCancelarDebito;
	}

	public String getIdRegistroAtendimento() {
		return idRegistroAtendimento;
	}

	public void setIdRegistroAtendimento(String idRegistroAtendimento) {
		this.idRegistroAtendimento = idRegistroAtendimento;
	}

	public String getDescricaoEspecificacaoRA() {
		return descricaoEspecificacaoRA;
	}

	public void setDescricaoEspecificacaoRA(String descricaoEspecificacaoRA) {
		this.descricaoEspecificacaoRA = descricaoEspecificacaoRA;
	}

	public String getUsuarioPermissaoEspecial() {
		return usuarioPermissaoEspecial;
	}

	public void setUsuarioPermissaoEspecial(String usuarioPermissaoEspecial) {
		this.usuarioPermissaoEspecial = usuarioPermissaoEspecial;
	}

	public String getRegistroAtendimentoCancelaDebito() {
		return registroAtendimentoCancelaDebito;
	}

	public void setRegistroAtendimentoCancelaDebito(
			String registroAtendimentoCancelaDebito) {
		this.registroAtendimentoCancelaDebito = registroAtendimentoCancelaDebito;
	}

		

}
