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
import gcom.micromedicao.hidrometro.HidrometroMarca;
import gcom.util.ConstantesSistema;

import java.util.Date;

import org.apache.struts.validator.ValidatorActionForm;


/**
 * [UC0524]	INSERIR SISTEMA ESGOTO
 * 
 * @author K�ssia Albuquerque
 * @date 08/03/2007
 */


public class InserirHidrometroMarcaActionForm extends ValidatorActionForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String descricaoMarcaHidrometro;
	
	private String descricaoAbreviada;
	
	private String validadeRevisao;
	
	private String marcaHidrometro;
	
	private String indicadorTipoHidrometro;

	public String getDescricaoAbreviada() {
		return descricaoAbreviada;
	}



	public void setDescricaoAbreviada(String descricaoAbreviada) {
		this.descricaoAbreviada = descricaoAbreviada;
	}

	public HidrometroMarca setFormValues(HidrometroMarca hidrometroMarca) {
		
		
		// Metodo usado para setar todos os valores do Form na base de dados

		hidrometroMarca.setDescricao(getDescricaoMarcaHidrometro());		
		hidrometroMarca.setDescricaoAbreviada(getDescricaoAbreviada());
		
		if ( getValidadeRevisao() != null && !getValidadeRevisao().equals( "" ) ){		
		  hidrometroMarca.setIntervaloDiasRevisao( Short.parseShort( getValidadeRevisao() ) );
		}
		
		hidrometroMarca.setCodigoHidrometroMarca( getMarcaHidrometro() );
		
		hidrometroMarca.setUltimaAlteracao(new Date());
		hidrometroMarca.setIndicadorUso(ConstantesSistema.INDICADOR_USO_ATIVO);
		
		if(getIndicadorTipoHidrometro()!= null && getIndicadorTipoHidrometro().equals("2")){
			hidrometroMarca.setIndicadorMicro(Short.parseShort("2"));
			hidrometroMarca.setIndicadorMacro(Short.parseShort("1"));
		}else{
			hidrometroMarca.setIndicadorMicro(Short.parseShort("1"));
			hidrometroMarca.setIndicadorMacro(Short.parseShort("2"));
		}
		return hidrometroMarca;
	}



	public String getDescricaoMarcaHidrometro() {
		return descricaoMarcaHidrometro;
	}



	public void setDescricaoMarcaHidrometro(String descricaoMarcaHidrometro) {
		this.descricaoMarcaHidrometro = descricaoMarcaHidrometro;
	}



	public String getMarcaHidrometro() {
		return marcaHidrometro;
	}



	public void setMarcaHidrometro(String marcaHidrometro) {
		this.marcaHidrometro = marcaHidrometro;
	}



	public String getValidadeRevisao() {
		return validadeRevisao;
	}



	public void setValidadeRevisao(String validadeRevisao) {
		this.validadeRevisao = validadeRevisao;
	}
	
	public String getIndicadorTipoHidrometro() {
		return indicadorTipoHidrometro;
	}

	public void setIndicadorTipoHidrometro(String indicadorTipoHidrometro) {
		this.indicadorTipoHidrometro = indicadorTipoHidrometro;
	}

}
