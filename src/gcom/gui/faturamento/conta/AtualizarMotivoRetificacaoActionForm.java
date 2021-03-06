package gcom.gui.faturamento.conta;

import org.apache.struts.validator.ValidatorActionForm;

public class AtualizarMotivoRetificacaoActionForm extends ValidatorActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private String descricao;
	
	private String numeroOcorrenciasNoAno;
	
	private String indicadorCompetenciaConsumo;
	
	private String indicadorUso;
	
	private String idColuna;
	
	private String descColuna;
	
	private String idColunaPesquisada;
	
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescColuna() {
		return descColuna;
	}
	
	public void setDescColuna(String descColuna) {
		this.descColuna = descColuna;
	}
	
	public String getIdColuna() {
		return idColuna;
	}
	
	public void setIdColuna(String idColuna) {
		this.idColuna = idColuna;
	}
	
	public String getIndicadorCompetenciaConsumo() {
		return indicadorCompetenciaConsumo;
	}
	
	public void setIndicadorCompetenciaConsumo(String indicadorCompetenciaConsumo) {
		this.indicadorCompetenciaConsumo = indicadorCompetenciaConsumo;
	}
	
	public String getIndicadorUso() {
		return indicadorUso;
	}
	
	public void setIndicadorUso(String indicadorUso) {
		this.indicadorUso = indicadorUso;
	}
	
	public String getNumeroOcorrenciasNoAno() {
		return numeroOcorrenciasNoAno;
	}
	
	public void setNumeroOcorrenciasNoAno(String numeroOcorrenciasNoAno) {
		this.numeroOcorrenciasNoAno = numeroOcorrenciasNoAno;
	}

	public String getIdColunaPesquisada() {
		return idColunaPesquisada;
	}

	public void setIdColunaPesquisada(String idColunaPesquisada) {
		this.idColunaPesquisada = idColunaPesquisada;
	}

}
