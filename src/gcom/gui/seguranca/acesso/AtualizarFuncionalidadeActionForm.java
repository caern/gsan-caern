/**
 * 
 */
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
package gcom.gui.seguranca.acesso;

import org.apache.struts.validator.ValidatorActionForm;

/**
 * Descri��o da classe
 * 
 * @author R�mulo Aur�lio
 * @date 15/05/2006
 */
public class AtualizarFuncionalidadeActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String id;

	private String descricao;

	private String descricaoAbreviada;

	private String caminhoMenu;

	private String caminhoUrl;

	private String indicadorPontoEntrada;

	private String modulo;

	private String idFuncionalidade;

	private String numeroOrdemMenu;

	private String indicadorNovaJanela;

	private String indicadorOlap;

	private String idFuncionalidadeCategoria;

	private String nomeFuncionalidadeCategoria;

	public String getIdFuncionalidadeCategoria() {
		return idFuncionalidadeCategoria;
	}

	public void setIdFuncionalidadeCategoria(String idFuncionalidadeCategoria) {
		this.idFuncionalidadeCategoria = idFuncionalidadeCategoria;
	}

	public String getIndicadorNovaJanela() {
		return indicadorNovaJanela;
	}

	public void setIndicadorNovaJanela(String indicadorNovaJanela) {
		this.indicadorNovaJanela = indicadorNovaJanela;
	}

	public String getIndicadorOlap() {
		return indicadorOlap;
	}

	public void setIndicadorOlap(String indicadorOlap) {
		this.indicadorOlap = indicadorOlap;
	}

	public String getNomeFuncionalidadeCategoria() {
		return nomeFuncionalidadeCategoria;
	}

	public void setNomeFuncionalidadeCategoria(String nomeFuncionalidadeCategoria) {
		this.nomeFuncionalidadeCategoria = nomeFuncionalidadeCategoria;
	}

	public String getIdFuncionalidade() {
		return idFuncionalidade;
	}

	public void setIdFuncionalidade(String idFuncionalidade) {
		this.idFuncionalidade = idFuncionalidade;
	}

	/**
	 * @return Retorna o campo caminhoMenu.
	 */
	public String getCaminhoMenu() {
		return caminhoMenu;
	}

	/**
	 * @param caminhoMenu
	 *            O caminhoMenu a ser setado.
	 */
	public void setCaminhoMenu(String caminhoMenu) {
		this.caminhoMenu = caminhoMenu;
	}

	/**
	 * @return Retorna o campo caminhoUrl.
	 */
	public String getCaminhoUrl() {
		return caminhoUrl;
	}

	/**
	 * @param caminhoUrl
	 *            O caminhoUrl a ser setado.
	 */
	public void setCaminhoUrl(String caminhoUrl) {
		this.caminhoUrl = caminhoUrl;
	}

	/**
	 * @return Retorna o campo descricao.
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            O descricao a ser setado.
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	/**
	 * @return Retorna o campo descricaoAbreviada.
	 */
	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}

	/**
	 * @param descricaoAbreviada
	 *            O descricaoAbreviada a ser setado.
	 */
	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	/**
	 * @return Retorna o campo indicadorPontoEntrada.
	 */
	public String getIndicadorPontoEntrada() {
		return indicadorPontoEntrada;
	}

	/**
	 * @param indicadorPontoEntrada
	 *            O indicadorPontoEntrada a ser setado.
	 */
	public void setIndicadorPontoEntrada(String indicadorPontoEntrada) {
		this.indicadorPontoEntrada = indicadorPontoEntrada;
	}

	/**
	 * @return Retorna o campo modulo.
	 */
	public String getModulo() {
		return modulo;
	}

	/**
	 * @param modulo
	 *            O modulo a ser setado.
	 */
	public void setModulo(String modulo) {
		this.modulo = modulo;
	}

	/**
	 * @return Retorna o campo id.
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            O id a ser setado.
	 */
	public void setId(String id) {
		this.id = id;
	}

	public String getNumeroOrdemMenu() {
		return numeroOrdemMenu;
	}

	public void setNumeroOrdemMenu(String numeroOrdemMenu) {
		this.numeroOrdemMenu = numeroOrdemMenu;
	}

}
