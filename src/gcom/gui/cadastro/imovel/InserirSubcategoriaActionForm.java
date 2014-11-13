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
package gcom.gui.cadastro.imovel;

import gcom.util.ConstantesSistema;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Description of the Class
 * 
 * @author Fernanda Karla
 * @created 29 de Dezembro de 2005
 */
public class InserirSubcategoriaActionForm extends ValidatorActionForm {
	private static final long serialVersionUID = 1L;
	private String categoria;

    private String descricaoSubcategoria;
    
    private String codigoSubcategoria;

    private String indicadorUso;
    
    private String descricaoAbreviada;
    
    private String codigoTarifaSocial;
    
    private String codigoGrupoSubcategoria;
    
    private String numeroFatorFiscalizacao;
    
    private String indicadorTarifaConsumo;

    private String indicadorSazonalidade;
    
	/**
	 * @return Returns the consumoAlto.
	 */
	public String getCategoria() {
		return categoria;
	}



	/**
	 * @param consumoAlto The consumoAlto to set.
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}


	/**
	 * @return Returns the consumoMinimo.
	 */
	public String getCodigoSubcategoria() {
		return codigoSubcategoria;
	}



	/**
	 * @param consumoMinimo The consumoMinimo to set.
	 */
	public void setCodigoSubcategoria(String codigoSubcategoria) {
		this.codigoSubcategoria = codigoSubcategoria;
	}


	/**
	 * @return Returns the consumoEstouro.
	 */
	public String getDescricaoSubcategoria() {
		return descricaoSubcategoria;
	}



	/**
	 * @param consumoEstouro The consumoEstouro to set.
	 */
	public void setDescricaoSubcategoria(String descricaoSubcategoria) {
		this.descricaoSubcategoria = descricaoSubcategoria;
	}


	/**
	 * @return Returns the indicadorUso.
	 */
	public String getIndicadorUso() {
		return indicadorUso;
	}



	/**
	 * @param indicadorUso The indicadorUso to set.
	 */
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}

		
    /**
     * Description of the Method
     * 
     * @param actionMapping
     *            Description of the Parameter
     * @param httpServletRequest
     *            Description of the Parameter
     */
    public void reset(ActionMapping actionMapping,
            HttpServletRequest httpServletRequest) {
    	
    	codigoSubcategoria = null;
        descricaoSubcategoria = null;
        indicadorUso = ConstantesSistema.INDICADOR_USO_ATIVO.toString();
        
    }

	public String getIndicadorSazonalidade() {
		return indicadorSazonalidade;
	}

	public void setIndicadorSazonalidade(String indicadorSazonalidade) {
		
		this.indicadorSazonalidade = indicadorSazonalidade;
	}



	public String getCodigoGrupoSubcategoria() {
		return codigoGrupoSubcategoria;
	}



	public void setCodigoGrupoSubcategoria(String codigoGrupoSubcategoria) {
		this.codigoGrupoSubcategoria = codigoGrupoSubcategoria;
	}



	public String getCodigoTarifaSocial() {
		return codigoTarifaSocial;
	}



	public void setCodigoTarifaSocial(String codigoTarifaSocial) {
		this.codigoTarifaSocial = codigoTarifaSocial;
	}



	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}



	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}



	public String getIndicadorTarifaConsumo() {
		return indicadorTarifaConsumo;
	}



	public void setIndicadorTarifaConsumo(String indicadorTarifaConsumo) {
		this.indicadorTarifaConsumo = indicadorTarifaConsumo;
	}



	public String getNumeroFatorFiscalizacao() {
		return numeroFatorFiscalizacao;
	}



	public void setNumeroFatorFiscalizacao(String numeroFatorFiscalizacao) {
		this.numeroFatorFiscalizacao = numeroFatorFiscalizacao;
	}
	
	
}