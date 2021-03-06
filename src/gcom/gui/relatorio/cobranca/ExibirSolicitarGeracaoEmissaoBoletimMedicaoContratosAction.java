package gcom.gui.relatorio.cobranca;

import java.util.Collection;

import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.empresa.FiltroEmpresa;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.ContratoEmpresaServico;
import gcom.micromedicao.FiltroContratoEmpresaServico;
import gcom.relatorio.cobranca.BoletimMedicaoContratoDadosHelper;
import gcom.relatorio.cobranca.BoletimMedicaoContratoHelper;
import gcom.util.ConstantesSistema;
import gcom.util.Util;
import gcom.util.filtro.ParametroSimples;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * [UC1250] Solicitar Gera��o/Emiss�o Boletim de Medi��o de Contratos
 * 
 * @author Mariana Victor
 * @date 21/11/2011
 * */
public class ExibirSolicitarGeracaoEmissaoBoletimMedicaoContratosAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {

		// Seta o mapeamento de retorno
		ActionForward retorno = actionMapping
			.findForward("exibirSolicitarGeracaoEmissaoBoletimMedicaoContratos");
		
		GerarEmitirBoletimMedicaoContratosForm form = 
			(GerarEmitirBoletimMedicaoContratosForm) actionForm;
		
		HttpSession sessao = httpServletRequest.getSession(false);
	
		Fachada fachada = Fachada.getInstancia();

//		if (form.getMesAnoReferencia() != null
//				&& !form.getMesAnoReferencia().equals("")) {
//			Integer anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
//			
//			if (!Util.validarMesAno(form.getMesAnoReferencia())) {
//				throw new ActionServletException("atencao.mes.ano.informado.invalido");
//			}
//			
//			if (Util.compararAnoMesReferencia(anoMes, sistemaParametro.getAnoMesFaturamento(), ">")){
//				throw new ActionServletException("atencao.mes.ano.informado.maior.faturamento.atual", null, sistemaParametro.getAnoMesFaturamento().toString());
//			}
//		}
		
        if (httpServletRequest.getParameter("menu") != null
        		&& httpServletRequest.getParameter("menu").trim().equalsIgnoreCase("sim")) {
        	// 1.1.	Opera��o (obrigat�rio) (exibir com a op��o "Gerar Boletim" selecionado e permitir 
        	//  que o usu�rio selecione entre "Gerar Boletim" e "Emitir Relat�rio").
        	form.setFormaGeracao("1");
        	form.setTipoOperacao("1");
        	form.setIdEmpresa("-1");
        	form.setIdsBoletim(null);
        	form.setMesAnoReferencia("");
        	
        	// 1.2.	Empresa (obrigat�rio) o sistema dever� popular 
        	//  o "combo-box" com a descri��o das empresas ativas contratadas para cobran�a 
        	FiltroEmpresa filtroEmpresa = new FiltroEmpresa();
        	filtroEmpresa.adicionarParametro(new ParametroSimples(
        		FiltroEmpresa.INDICADORUSO, ConstantesSistema.SIM));
        	filtroEmpresa.adicionarParametro(new ParametroSimples(
        		FiltroEmpresa.INDICADOR_EMPRESA_CONTRATADA_COBRANCA, ConstantesSistema.SIM));
        	Collection<Empresa> colecaoEmpresa = fachada.pesquisar(
        		filtroEmpresa, Empresa.class.getName());
        	
        	if (colecaoEmpresa != null && !colecaoEmpresa.isEmpty()) {
        		sessao.setAttribute("colecaoEmpresas", colecaoEmpresa);
        	} else {
        		sessao.removeAttribute("colecaoEmpresas");
        	}

        }
        
        if (httpServletRequest.getParameter("selecionouEmpresa") != null
        		&& httpServletRequest.getParameter("selecionouEmpresa").trim().equalsIgnoreCase("sim")){
        	
        	if (form.getIdEmpresa() != null
        		&& !form.getIdEmpresa().trim().equals("")) {

        		sessao.setAttribute("informouEmpresa", true);
        		
	        	// 1.3.	Contrato (obrigat�rio) (s� habilitar ap�s a sele��o da Empresa no item acima) o sistema dever� 
	        	//  popular o "combo-box" com os contratos associados � empresa informada 
	        	FiltroContratoEmpresaServico filtroContratoEmpresaServico = new FiltroContratoEmpresaServico();
	        	filtroContratoEmpresaServico.adicionarParametro(new ParametroSimples(
	        		FiltroContratoEmpresaServico.EMPRESA_ID, form.getIdEmpresa()));
	        	Collection<ContratoEmpresaServico> colecaoContratoEmpresaServico = fachada.pesquisar(
	        		filtroContratoEmpresaServico, ContratoEmpresaServico.class.getName());
	        	
	        	if (colecaoContratoEmpresaServico != null && !colecaoContratoEmpresaServico.isEmpty()) {
	        		sessao.setAttribute("colecaoContrato", colecaoContratoEmpresaServico);
	        	} else {
	        		sessao.removeAttribute("colecaoContrato");
	        	}
	        	
        	} else {
        		sessao.removeAttribute("informouEmpresa");
        		sessao.removeAttribute("colecaoContrato");
        	}
        	
        }

		// Tipo da Opera��o igual a Gerar Boletim
		if (form.getTipoOperacao() != null
				&& form.getTipoOperacao().equals("1")) {
			
			  if (httpServletRequest.getParameter("selecionarBoletim") != null
		        		&& httpServletRequest.getParameter("selecionarBoletim").trim().equalsIgnoreCase("sim")){
				  
				  this.validarForm(form);
				  Integer anoMes = null;
				  
				  if (form.getMesAnoReferencia() != null && !form.getMesAnoReferencia().trim().equals("")) {
					anoMes = Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia());
				  }
				  
				  Collection<BoletimMedicaoContratoHelper> colecaoBoletimMedicaoContratoHelper = 
						  fachada.pesquisarBoletimMedicaoContrato(
							  new Integer(form.getIdContrato()),
							  anoMes);
				  
				  if (colecaoBoletimMedicaoContratoHelper != null 
						  && !colecaoBoletimMedicaoContratoHelper.isEmpty()) {
					  sessao.setAttribute("colecaoBoletimMedicaoContratoHelper", colecaoBoletimMedicaoContratoHelper);
				  } else {
					  sessao.removeAttribute("colecaoBoletimMedicaoContratoHelper");
				  }
				  
			  }
		} else if (form.getTipoOperacao() != null
				&& form.getTipoOperacao().equals("2")) {
			// Tipo da Opera��o igual a Emitir Boletim

			  if (httpServletRequest.getParameter("selecionarBoletimContrato") != null
		        		&& httpServletRequest.getParameter("selecionarBoletimContrato").trim().equalsIgnoreCase("sim")){
				  
				  this.validarForm(form);
				  
				  Collection<BoletimMedicaoContratoDadosHelper> colecaoBoletimMedicaoContratoDadosHelper = 
						  fachada.pesquisarBoletimMedicaoContratoEmitir(
							  new Integer(form.getIdContrato()),
							  Util.formatarMesAnoComBarraParaAnoMes(form.getMesAnoReferencia()));
				  
				  if (colecaoBoletimMedicaoContratoDadosHelper != null 
						  && !colecaoBoletimMedicaoContratoDadosHelper.isEmpty()) {
					  sessao.setAttribute("colecaoBoletimMedicaoContratoDadosHelper", colecaoBoletimMedicaoContratoDadosHelper);
				  } else {
					  sessao.removeAttribute("colecaoBoletimMedicaoContratoDadosHelper");
				  }
				  
			  }
		}

		return retorno;
	}
	
	private void validarForm(GerarEmitirBoletimMedicaoContratosForm form) {
		
		if (form.getIdEmpresa() == null
				|| form.getIdEmpresa().trim().equals("")
				|| form.getIdEmpresa().trim().equals("-1")) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Empresa");
		}
		
		if (form.getIdContrato() == null
				|| form.getIdContrato().trim().equals("")
				|| form.getIdContrato().trim().equals("-1")) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "Contrato");
		}
		
		if (form.getTipoOperacao() != null
				&& !form.getTipoOperacao().equals("1")
				&& (form.getMesAnoReferencia() == null
				|| form.getMesAnoReferencia().trim().equals(""))) {

			throw new ActionServletException(
					"atencao.campo_selecionado.obrigatorio", "M�s/Ano do Ciclo de Cobran�a");
		}
		
		// [FS0001] - Validar m�s/ano do boletim.
		if (form.getTipoOperacao() != null
				&& !form.getTipoOperacao().equals("1")
				&& !Util.validarMesAno(form.getMesAnoReferencia())) {
			
			throw new ActionServletException("atencao.mesAno", "M�s/Ano informado");
		}
	}

}
