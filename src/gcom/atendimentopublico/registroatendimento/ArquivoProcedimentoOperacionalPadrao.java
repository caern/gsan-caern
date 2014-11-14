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
* Anderson Italo felinto de Lima
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
package gcom.atendimentopublico.registroatendimento;

import gcom.interceptor.ControleAlteracao;
import gcom.interceptor.ObjetoTransacao;
import gcom.util.Util;
import gcom.util.filtro.Filtro;
import gcom.util.filtro.ParametroSimples;
import java.util.Date;

import org.apache.struts.upload.FormFile;

/** @author Hibernate CodeGenerator */
@ControleAlteracao()
public class ArquivoProcedimentoOperacionalPadrao extends ObjetoTransacao {
	
	private static final long serialVersionUID = 1L;
	
	/** identifier field */
	private Integer id;

	/** persistent field */
	private SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao;
	
	/** persistent field */
	private String nomeArquivo;
	
	private String nomeTruncado;
	
	/** persistent field */
	private byte[] bytesArquivo;
	
	/** persistent field */
	private Date ultimaAlteracao;
		
	private String nomeExtensaoArquivo;
	
	private int posicao;
	
	private FormFile arquivo;
	
	public ArquivoProcedimentoOperacionalPadrao() {}

	@Override
	public Date getUltimaAlteracao() {
		return ultimaAlteracao;
	}

	@Override
	public void setUltimaAlteracao(Date ultimaAlteracao) {
		this.ultimaAlteracao = ultimaAlteracao;		
	}

	public Filtro retornaFiltro() {
		FiltroArquivoProcedimentoOperacionalPadrao filtroArquivoProcedimentoOperacionalPadrao = new FiltroArquivoProcedimentoOperacionalPadrao();
		filtroArquivoProcedimentoOperacionalPadrao.adicionarParametro(new ParametroSimples(FiltroSolicitacaoTipoEspecificacao.ID,
				this.getId()));
		return filtroArquivoProcedimentoOperacionalPadrao;
	}

	public String[] retornaCamposChavePrimaria() {
		String[] retorno = { "id" };
		return retorno;
	}
	
	public boolean equals(Object other) {
		if ((this == other)) {
			return true;
		}
		if (!(other instanceof ArquivoProcedimentoOperacionalPadrao)) {
			return false;
		}
		ArquivoProcedimentoOperacionalPadrao castOther = (ArquivoProcedimentoOperacionalPadrao) other;

		return (this.getNomeArquivo().equalsIgnoreCase(castOther.getNomeArquivo()));
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SolicitacaoTipoEspecificacao getSolicitacaoTipoEspecificacao() {
		return solicitacaoTipoEspecificacao;
	}

	public void setSolicitacaoTipoEspecificacao(
			SolicitacaoTipoEspecificacao solicitacaoTipoEspecificacao) {
		this.solicitacaoTipoEspecificacao = solicitacaoTipoEspecificacao;
	}

	public String getNomeArquivo() {
		return nomeArquivo;
	}

	public void setNomeArquivo(String nomeArquivo) {
		this.nomeArquivo = nomeArquivo;
	}

	public int getPosicao() {
		return posicao;
	}

	public void setPosicao(int posicao) {
		this.posicao = posicao;
	}

	public String getNomeExtensaoArquivo() {
		if (this.nomeExtensaoArquivo==null){
			this.nomeExtensaoArquivo=Util.obterExtensaoDoArquivo(nomeArquivo);
		}
		return nomeExtensaoArquivo;
	}

	public void setNomeExtensaoArquivo(String nomeExtensaoArquivo) {
		this.nomeExtensaoArquivo = nomeExtensaoArquivo;
	}

	public byte[] getBytesArquivo() {
		return bytesArquivo;
	}

	public void setBytesArquivo(byte[] bytesArquivo) {
		this.bytesArquivo = bytesArquivo;
	}

	public FormFile getArquivo() {
		return arquivo;
	}

	public void setArquivo(FormFile arquivo) {
		this.arquivo = arquivo;
	}

	public String getNomeTruncado() {
		if (nomeArquivo!=null){
			if (nomeArquivo.length()>50){
				this.nomeTruncado = nomeArquivo.substring(0, 46)+"."+getNomeExtensaoArquivo();
			} else { 
				this.nomeTruncado = nomeArquivo;
			}
		}
		return nomeTruncado;
	}

	public void setNomeTruncado(String nomeTruncado) {
		this.nomeTruncado = nomeTruncado;
	}
	 
}