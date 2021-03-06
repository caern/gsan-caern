package gcom.gui.portal.caema;

import javax.servlet.ServletRequest;

import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorActionForm;

/**
 * Classe Respons�vel por armazenar o valor da matr�cula e do usu�rio para
 * mostrar os Servi�os do Portal da Compesa
 * 
 * @author Diogo Peixoto
 * @date 13/05/2011
 */
public class ExibirServicosPortalCaemaActionForm extends ValidatorActionForm {

	private static final long serialVersionUID = 1L;

	private String matricula;

	private String nomeUsuario;

	private String cpfCnpjSolicitante;

	@Override
	public void reset(ActionMapping mapping, ServletRequest request) {
		this.cpfCnpjSolicitante = null;
	}

	public String getMatricula() {
		return this.matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}

	public String getCpfCnpjSolicitante() {
		return cpfCnpjSolicitante;
	}

	public void setCpfCnpjSolicitante(String cpfCnpjSolicitante) {
		this.cpfCnpjSolicitante = cpfCnpjSolicitante;
	}
}