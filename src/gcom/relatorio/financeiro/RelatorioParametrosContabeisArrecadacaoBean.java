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
package gcom.relatorio.financeiro;

import gcom.relatorio.RelatorioBean;
import java.math.BigDecimal;

/**
 * [UC0824] Gerar Relatorio de Parametros Cont�beis
 * 
 * @author Bruno Barros
 * @data 08/07/2008
 */
public class RelatorioParametrosContabeisArrecadacaoBean implements RelatorioBean {

	private String tipoRecebimento;
	private String tipoLancamento;
	private String itemLancamento;
	private String categoria;
	private String itemLancamentoContabil;
	private String contaContabilDebito;
	private String contaContabilCredito;
	private BigDecimal valorContabilizado;
	
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getContaContabilCredito() {
		return contaContabilCredito;
	}
	public void setContaContabilCredito(String contaContabilCredito) {
		this.contaContabilCredito = contaContabilCredito;
	}
	public String getContaContabilDebito() {
		return contaContabilDebito;
	}
	public void setContaContabilDebito(String contaContabilDebito) {
		this.contaContabilDebito = contaContabilDebito;
	}
	public String getItemLancamento() {
		return itemLancamento;
	}
	public void setItemLancamento(String itemLancamento) {
		this.itemLancamento = itemLancamento;
	}
	public String getItemLancamentoContabil() {
		return itemLancamentoContabil;
	}
	public void setItemLancamentoContabil(String itemLancamentoContabil) {
		this.itemLancamentoContabil = itemLancamentoContabil;
	}
	public String getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	public String getTipoRecebimento() {
		return tipoRecebimento;
	}
	public void setTipoRecebimento(String tipoRecebimento) {
		this.tipoRecebimento = tipoRecebimento;
	}
	public BigDecimal getValorContabilizado() {
		return valorContabilizado;
	}
	public void setValorContabilizado(BigDecimal valorContabilizado) {
		this.valorContabilizado = valorContabilizado;
	}
	public RelatorioParametrosContabeisArrecadacaoBean(String tipoRecebimento, String tipoLancamento, String itemLancamento, String categoria, String itemLancamentoContabil, String contaContabilCredito, String contaContabilDebito, BigDecimal valorContabilizado) {
		super();

		this.tipoRecebimento = tipoRecebimento;
		this.tipoLancamento = tipoLancamento;
		this.itemLancamento = itemLancamento;
		this.categoria = categoria;
		this.itemLancamentoContabil = itemLancamentoContabil;
		this.contaContabilDebito = contaContabilDebito;
		this.contaContabilCredito = contaContabilCredito;
		this.valorContabilizado = valorContabilizado;
	}	
}
