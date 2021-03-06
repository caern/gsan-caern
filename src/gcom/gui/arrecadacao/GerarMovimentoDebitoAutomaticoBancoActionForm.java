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
package gcom.gui.arrecadacao;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Action form respons�vel pelas propiedades do caso de uso de gerar movimento
 * de d�bito autom�tico para o banco
 * 
 * @author S�vio Luiz
 * @date 17/04/2006
 */
public class GerarMovimentoDebitoAutomaticoBancoActionForm extends
		ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String opcaoMovimentoDebitoAutomatico;

	private String idGrupoFaturamento;
	
	private String idGrupoFaturamentoSelecionados;

	private String mesAnoFaturamento;

	private String opcaoEnvioParaBanco;
	
	private String idArrecadadorMovimento;

	private String codigoBancoMovimento;

	private String codigoRemessaMovimento;

	private String identificacaoServicoMovimento;

	private String numeroSequencialMovimento;
	
	private String[] idsCodigosBancos;
	
	private Date dataAtual;

	public String getCodigoBancoMovimento() {
		return codigoBancoMovimento;
	}

	public void setCodigoBancoMovimento(String codigoBancoMovimento) {
		this.codigoBancoMovimento = codigoBancoMovimento;
	}

	public String getCodigoRemessaMovimento() {
		return codigoRemessaMovimento;
	}

	public void setCodigoRemessaMovimento(String codigoRemessaMovimento) {
		this.codigoRemessaMovimento = codigoRemessaMovimento;
	}

	public String getIdentificacaoServicoMovimento() {
		return identificacaoServicoMovimento;
	}

	public void setIdentificacaoServicoMovimento(
			String identificacaoServicoMovimento) {
		this.identificacaoServicoMovimento = identificacaoServicoMovimento;
	}

	public String getIdGrupoFaturamento() {
		return idGrupoFaturamento;
	}

	public void setIdGrupoFaturamento(String idGrupoFaturamento) {
		this.idGrupoFaturamento = idGrupoFaturamento;
	}

	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	
	public String getNumeroSequencialMovimento() {
		return numeroSequencialMovimento;
	}

	public void setNumeroSequencialMovimento(String numeroSequencialMovimento) {
		this.numeroSequencialMovimento = numeroSequencialMovimento;
	}

	public String getOpcaoEnvioParaBanco() {
		return opcaoEnvioParaBanco;
	}

	public void setOpcaoEnvioParaBanco(String opcaoEnvioParaBanco) {
		this.opcaoEnvioParaBanco = opcaoEnvioParaBanco;
	}

	public String getOpcaoMovimentoDebitoAutomatico() {
		return opcaoMovimentoDebitoAutomatico;
	}

	public void setOpcaoMovimentoDebitoAutomatico(
			String opcaoMovimentoDebitoAutomatico) {
		this.opcaoMovimentoDebitoAutomatico = opcaoMovimentoDebitoAutomatico;
	}
	
	

	public String[] getIdsCodigosBancos() {
		return idsCodigosBancos;
	}

	public void setIdsCodigosBancos(String[] idsCodigosBancos) {
		this.idsCodigosBancos = idsCodigosBancos;
	}

	public void reset(ActionMapping actionMapping,
			HttpServletRequest httpServletRequest) {
		opcaoMovimentoDebitoAutomatico = null;
		idGrupoFaturamento = "";
		mesAnoFaturamento = null;
		opcaoEnvioParaBanco = null;
		codigoBancoMovimento = null;
		codigoRemessaMovimento = null;
		identificacaoServicoMovimento = null;
		numeroSequencialMovimento = null;
		idsCodigosBancos = null;
	}

	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	public String getIdArrecadadorMovimento() {
		return idArrecadadorMovimento;
	}

	public void setIdArrecadadorMovimento(String idArrecadadorMovimento) {
		this.idArrecadadorMovimento = idArrecadadorMovimento;
	}

	public Date getDataAtual() {
		return dataAtual;
	}

	public void setDataAtual(Date dataAtual) {
		this.dataAtual = dataAtual;
	}

	public String getIdGrupoFaturamentoSelecionados() {
		return idGrupoFaturamentoSelecionados;
	}

	public void setIdGrupoFaturamentoSelecionados(
			String idGrupoFaturamentoSelecionados) {
		this.idGrupoFaturamentoSelecionados = idGrupoFaturamentoSelecionados;
	}
	
	

}