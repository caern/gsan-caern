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
package gcom.faturamento.bean;

import java.math.BigDecimal;


/**
 * D�bitos de um im�vel ou de um cliente
 * @author Fernanda Paiva
 * @since 04/01/2006
 */
public class GerarRelacaoAcompanhamentoFaturamentoHelper {
	
	private String codigoImovel;
	
	private String inscricao;
	
	private String idLocalidade;
	
	private String nomeLocalidade;
	
	private String idGerenciaRegional;
	
	private String nomeGerenciaRegional;
	
	private String categoriaPrincipal;
	
	private String nomeClienteUsuario;
	
	private String enderecoImovel;
	
	private String codigoSetorComercial;
	
	private String numeroQuadra;
	
	private String lote;
	
	private String subLote;
	
	private String quantidadeEconomias;
	
	private String situacaoAgua;
	
	private String situacaoEsgoto;
	
	private String percentualEsgoto;
	
	private String dataCorte;
	
	private BigDecimal valorFatura;
	
	private String endereco;
	
	private String nomeAbreviadoGerencia;
	
	private String valorDebito;
	
	private String esgotoFixado;
	
	private String consumoMedio;
	
	private String consumoMes;
	
	private String consumoAnormalidade;
	
	private String fatura;
	
	private String mesUm;
	
	private String mesDois;
	
	private String mesTres;
	
	private String mesQuatro;
	
	private String mesCinco;
	
	private String mesSeis;
	
	private String anormalidade;
	
	private String mesAnoFaturamento;
	
	private String dataInstalacaoHidrometro;
	
	private String variacao;
	
	/**
	 * @return Retorna o campo anormalidade.
	 */
	public String getAnormalidade() {
		return anormalidade;
	}

	/**
	 * @param anormalidade O anormalidade a ser setado.
	 */
	public void setAnormalidade(String anormalidade) {
		this.anormalidade = anormalidade;
	}

	/**
	 * @return Retorna o campo esgotoFixado.
	 */
	public String getEsgotoFixado() {
		return esgotoFixado;
	}

	/**
	 * @param esgotoFixado O esgotoFixado a ser setado.
	 */
	public void setEsgotoFixado(String esgotoFixado) {
		this.esgotoFixado = esgotoFixado;
	}

	/**
	 * @return Retorna o campo categoriaPrincipal.
	 */
	public String getCategoriaPrincipal() {
		return categoriaPrincipal;
	}

	/**
	 * @param categoriaPrincipal O categoriaPrincipal a ser setado.
	 */
	public void setCategoriaPrincipal(String categoriaPrincipal) {
		this.categoriaPrincipal = categoriaPrincipal;
	}

	/**
	 * @return Retorna o campo codigoImovel.
	 */
	public String getCodigoImovel() {
		return codigoImovel;
	}

	/**
	 * @param codigoImovel O codigoImovel a ser setado.
	 */
	public void setCodigoImovel(String codigoImovel) {
		this.codigoImovel = codigoImovel;
	}

	/**
	 * @return Retorna o campo enderecoImovel.
	 */
	public String getEnderecoImovel() {
		return enderecoImovel;
	}

	/**
	 * @param enderecoImovel O enderecoImovel a ser setado.
	 */
	public void setEnderecoImovel(String enderecoImovel) {
		this.enderecoImovel = enderecoImovel;
	}

	/**
	 * @return Retorna o campo idGerenciaRegional.
	 */
	public String getIdGerenciaRegional() {
		return idGerenciaRegional;
	}

	/**
	 * @param idGerenciaRegional O idGerenciaRegional a ser setado.
	 */
	public void setIdGerenciaRegional(String idGerenciaRegional) {
		this.idGerenciaRegional = idGerenciaRegional;
	}

	/**
	 * @return Retorna o campo idLocalidade.
	 */
	public String getIdLocalidade() {
		return idLocalidade;
	}

	/**
	 * @param idLocalidade O idLocalidade a ser setado.
	 */
	public void setIdLocalidade(String idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	/**
	 * @return Retorna o campo inscricao.
	 */
	public String getInscricao() {
		return inscricao;
	}

	/**
	 * @param inscricao O inscricao a ser setado.
	 */
	public void setInscricao(String inscricao) {
		this.inscricao = inscricao;
	}

	/**
	 * @return Retorna o campo nomeClienteUsuario.
	 */
	public String getNomeClienteUsuario() {
		return nomeClienteUsuario;
	}

	/**
	 * @param nomeClienteUsuario O nomeClienteUsuario a ser setado.
	 */
	public void setNomeClienteUsuario(String nomeClienteUsuario) {
		this.nomeClienteUsuario = nomeClienteUsuario;
	}

	/**
	 * @return Retorna o campo nomeGerenciaRegional.
	 */
	public String getNomeGerenciaRegional() {
		return nomeGerenciaRegional;
	}

	/**
	 * @param nomeGerenciaRegional O nomeGerenciaRegional a ser setado.
	 */
	public void setNomeGerenciaRegional(String nomeGerenciaRegional) {
		this.nomeGerenciaRegional = nomeGerenciaRegional;
	}

	/**
	 * @return Retorna o campo nomeLocalidade.
	 */
	public String getNomeLocalidade() {
		return nomeLocalidade;
	}

	/**
	 * @param nomeLocalidade O nomeLocalidade a ser setado.
	 */
	public void setNomeLocalidade(String nomeLocalidade) {
		this.nomeLocalidade = nomeLocalidade;
	}

	/**
	 * @return Retorna o campo codigoSetorComercial.
	 */
	public String getCodigoSetorComercial() {
		return codigoSetorComercial;
	}

	/**
	 * @param codigoSetorComercial O codigoSetorComercial a ser setado.
	 */
	public void setCodigoSetorComercial(String codigoSetorComercial) {
		this.codigoSetorComercial = codigoSetorComercial;
	}

	/**
	 * @return Retorna o campo lote.
	 */
	public String getLote() {
		return lote;
	}

	/**
	 * @param lote O lote a ser setado.
	 */
	public void setLote(String lote) {
		this.lote = lote;
	}

	/**
	 * @return Retorna o campo numeroQuadra.
	 */
	public String getNumeroQuadra() {
		return numeroQuadra;
	}

	/**
	 * @param numeroQuadra O numeroQuadra a ser setado.
	 */
	public void setNumeroQuadra(String numeroQuadra) {
		this.numeroQuadra = numeroQuadra;
	}

	/**
	 * @return Retorna o campo subLote.
	 */
	public String getSubLote() {
		return subLote;
	}

	/**
	 * @param subLote O subLote a ser setado.
	 */
	public void setSubLote(String subLote) {
		this.subLote = subLote;
	}

	/**
	 * @return Retorna o campo dataCorte.
	 */
	public String getDataCorte() {
		return dataCorte;
	}

	/**
	 * @param dataCorte O dataCorte a ser setado.
	 */
	public void setDataCorte(String dataCorte) {
		this.dataCorte = dataCorte;
	}

	/**
	 * @return Retorna o campo percentualEsgoto.
	 */
	public String getPercentualEsgoto() {
		return percentualEsgoto;
	}

	/**
	 * @param percentualEsgoto O percentualEsgoto a ser setado.
	 */
	public void setPercentualEsgoto(String percentualEsgoto) {
		this.percentualEsgoto = percentualEsgoto;
	}

	/**
	 * @return Retorna o campo quantidadeEconomias.
	 */
	public String getQuantidadeEconomias() {
		return quantidadeEconomias;
	}

	/**
	 * @param quantidadeEconomias O quantidadeEconomias a ser setado.
	 */
	public void setQuantidadeEconomias(String quantidadeEconomias) {
		this.quantidadeEconomias = quantidadeEconomias;
	}

	/**
	 * @return Retorna o campo situacaoAgua.
	 */
	public String getSituacaoAgua() {
		return situacaoAgua;
	}

	/**
	 * @param situacaoAgua O situacaoAgua a ser setado.
	 */
	public void setSituacaoAgua(String situacaoAgua) {
		this.situacaoAgua = situacaoAgua;
	}

	/**
	 * @return Retorna o campo situacaoEsgoto.
	 */
	public String getSituacaoEsgoto() {
		return situacaoEsgoto;
	}

	/**
	 * @param situacaoEsgoto O situacaoEsgoto a ser setado.
	 */
	public void setSituacaoEsgoto(String situacaoEsgoto) {
		this.situacaoEsgoto = situacaoEsgoto;
	}

	/**
	 * @return Retorna o campo fatura.
	 */
	public BigDecimal getValorFatura() {
		return valorFatura;
	}

	/**
	 * @param fatura O fatura a ser setado.
	 */
	public void setValorFatura(BigDecimal valorFatura) {
		this.valorFatura = valorFatura;
	}

	/**
	 * @return Retorna o campo endereco.
	 */
	public String getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco O endereco a ser setado.
	 */
	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return Retorna o campo nomeAbreviadoGerencia.
	 */
	public String getNomeAbreviadoGerencia() {
		return nomeAbreviadoGerencia;
	}

	/**
	 * @param nomeAbreviadoGerencia O nomeAbreviadoGerencia a ser setado.
	 */
	public void setNomeAbreviadoGerencia(String nomeAbreviadoGerencia) {
		this.nomeAbreviadoGerencia = nomeAbreviadoGerencia;
	}

	/**
	 * @return Retorna o campo valorDebito.
	 */
	public String getValorDebito() {
		return valorDebito;
	}

	/**
	 * @param valorDebito O valorDebito a ser setado.
	 */
	public void setValorDebito(String valorDebito) {
		this.valorDebito = valorDebito;
	}

	/**
	 * @return Retorna o campo consumoMedio.
	 */
	public String getConsumoMedio() {
		return consumoMedio;
	}

	/**
	 * @param consumoMedio O consumoMedio a ser setado.
	 */
	public void setConsumoMedio(String consumoMedio) {
		this.consumoMedio = consumoMedio;
	}

	/**
	 * @return Retorna o campo consumoMes.
	 */
	public String getConsumoMes() {
		return consumoMes;
	}

	/**
	 * @param consumoMes O consumoMes a ser setado.
	 */
	public void setConsumoMes(String consumoMes) {
		this.consumoMes = consumoMes;
	}

	/**
	 * @return Retorna o campo consumoAnormalidade.
	 */
	public String getConsumoAnormalidade() {
		return consumoAnormalidade;
	}

	/**
	 * @param consumoAnormalidade O consumoAnormalidade a ser setado.
	 */
	public void setConsumoAnormalidade(String consumoAnormalidade) {
		this.consumoAnormalidade = consumoAnormalidade;
	}

	/**
	 * @return Retorna o campo fatura.
	 */
	public String getFatura() {
		return fatura;
	}

	/**
	 * @param fatura O fatura a ser setado.
	 */
	public void setFatura(String fatura) {
		this.fatura = fatura;
	}

	/**
	 * @return Retorna o campo mesCinco.
	 */
	public String getMesCinco() {
		return mesCinco;
	}

	/**
	 * @param mesCinco O mesCinco a ser setado.
	 */
	public void setMesCinco(String mesCinco) {
		this.mesCinco = mesCinco;
	}

	/**
	 * @return Retorna o campo mesDois.
	 */
	public String getMesDois() {
		return mesDois;
	}

	/**
	 * @param mesDois O mesDois a ser setado.
	 */
	public void setMesDois(String mesDois) {
		this.mesDois = mesDois;
	}

	/**
	 * @return Retorna o campo mesQuatro.
	 */
	public String getMesQuatro() {
		return mesQuatro;
	}

	/**
	 * @param mesQuatro O mesQuatro a ser setado.
	 */
	public void setMesQuatro(String mesQuatro) {
		this.mesQuatro = mesQuatro;
	}

	/**
	 * @return Retorna o campo mesSeis.
	 */
	public String getMesSeis() {
		return mesSeis;
	}

	/**
	 * @param mesSeis O mesSeis a ser setado.
	 */
	public void setMesSeis(String mesSeis) {
		this.mesSeis = mesSeis;
	}

	/**
	 * @return Retorna o campo mesTres.
	 */
	public String getMesTres() {
		return mesTres;
	}

	/**
	 * @param mesTres O mesTres a ser setado.
	 */
	public void setMesTres(String mesTres) {
		this.mesTres = mesTres;
	}

	/**
	 * @return Retorna o campo mesUm.
	 */
	public String getMesUm() {
		return mesUm;
	}

	/**
	 * @param mesUm O mesUm a ser setado.
	 */
	public void setMesUm(String mesUm) {
		this.mesUm = mesUm;
	}

	/**
	 * @return Retorna o campo mesAnoFaturamento.
	 */
	public String getMesAnoFaturamento() {
		return mesAnoFaturamento;
	}

	/**
	 * @param mesAnoFaturamento O mesAnoFaturamento a ser setado.
	 */
	public void setMesAnoFaturamento(String mesAnoFaturamento) {
		this.mesAnoFaturamento = mesAnoFaturamento;
	}

	/**
	 * @return Retorna o campo dataInstalacaoHidrometro.
	 */
	public String getDataInstalacaoHidrometro() {
		return dataInstalacaoHidrometro;
	}

	/**
	 * @param dataInstalacaoHidrometro O dataInstalacaoHidrometro a ser setado.
	 */
	public void setDataInstalacaoHidrometro(String dataInstalacaoHidrometro) {
		this.dataInstalacaoHidrometro = dataInstalacaoHidrometro;
	}

	/**
	 * @return Retorna o campo variacao.
	 */
	public String getVariacao() {
		return variacao;
	}

	/**
	 * @param variacao O variacao a ser setado.
	 */
	public void setVariacao(String variacao) {
		this.variacao = variacao;
	}
	
}
