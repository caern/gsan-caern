package gcom.gui.atualizacaocadastral;

import gcom.atualizacaocadastral.bean.ComandoAtualizacaoCadastralDMHelper;
import gcom.batch.Processo;
import gcom.cadastro.empresa.Empresa;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.localidade.Localidade;
import gcom.fachada.Fachada;
import gcom.gui.ActionServletException;
import gcom.gui.GcomAction;
import gcom.micromedicao.Leiturista;
import gcom.seguranca.acesso.usuario.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class GerarRoteiroDispositivoMovelAction extends GcomAction {
	
	public ActionForward execute(ActionMapping actionMapping,
			ActionForm actionForm, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse) {
		
		// Prepara o retorno da Ação
		ActionForward retorno = actionMapping.findForward("telaSucesso"); 
		
		//Form
		GerarRoteiroDispositivoMovelActionForm form = (GerarRoteiroDispositivoMovelActionForm) actionForm;
		
		//Sessão
		HttpSession sessao = httpServletRequest.getSession(false);
		
		//Fachada
		Fachada fachada = Fachada.getInstancia();
		
		//Coleção de Imóveis
		ArrayList<String> colecaoImoveis = new ArrayList<String>();	
		
		if((form.getIdsRegistros() == null || form.getIdsRegistros().length < 1)){
			throw new ActionServletException("atencao.nenhum_registro_selecionado");
		}else{
			for(int i = 0; i < form.getIdsRegistros().length; i++){
				colecaoImoveis.add(form.getIdsRegistros()[i]);
			}
		}
		
		//Recuperar o Usuário Logado
		Usuario usuarioLogado = (Usuario) sessao.getAttribute("usuarioLogado");
		
		//Montar o Helper
		ComandoAtualizacaoCadastralDMHelper helper = this.montarHelper(form, usuarioLogado, fachada, colecaoImoveis);			
			
	
		Map parametros = new HashMap();
		parametros.put("helper", helper);
    	parametros.put("colecaoImoveis", colecaoImoveis);
    	
    	
    	fachada.inserirProcessoIniciadoParametrosLivres(parametros, 
         		Processo.GERAR_ROTEIRO_DISPOSITIVO_MOVEL,
         		this.getUsuarioLogado(httpServletRequest));
		
		//Montar tela de sucesso
		this.montarPaginaSucesso(httpServletRequest, 
				"Roteiro para Atualização Cadastral foi encaminhado para batch.", 
				"Gerar Roteiro Dispositivo Móvel", 
				"exibirGerarRoteiroDispositivoMovelAction.do?menu=sim");
		
		
		return retorno;
	}
	
	/**
	 * [SB 0001] - Gerar Comando de Atualização Cadastral
	 * 
	 * @author Diogo Luiz
	 * @date 26/08/2014
	 * 
	 */
	public ComandoAtualizacaoCadastralDMHelper montarHelper(GerarRoteiroDispositivoMovelActionForm form, Usuario usuario, Fachada fachada, 
			ArrayList<String> colecaoImoveis){
		
		ComandoAtualizacaoCadastralDMHelper helper = new ComandoAtualizacaoCadastralDMHelper();
		
		Localidade localidade = new Localidade(Integer.parseInt(form.getIdLocalidade()));
		
		Empresa empresa = new Empresa();
		empresa.setId(Integer.parseInt(form.getIdEmpresa()));
		
		
		Leiturista leiturista = new Leiturista();
		
		if(form.getCadastrador() != null && !form.getCadastrador().equals("-1")){
			leiturista.setId(Integer.parseInt(form.getCadastrador()));
		}else{
			throw new ActionServletException("atencao.campo.informado", null, "Cadastrador");
		}	
		
		Integer codigoSetorComercial = null;
		if(!form.getCodigoSetorComercial().equals("-1")){
			codigoSetorComercial = Integer.parseInt(form.getCodigoSetorComercial());
		}
		
		helper.setCodigoSetorComercial(codigoSetorComercial);
		helper.setEmpresa(empresa);
		helper.setLeiturista(leiturista);
		helper.setLocalidade(localidade);
		helper.setUsuarioLogado(usuario);		
		
		Set<Integer> quadrasSet = new HashSet<Integer>(); 
		
		for(int index = 0; index < colecaoImoveis.size(); index++){
			String  idImovel = colecaoImoveis.get(index);
			idImovel = idImovel.substring(0, idImovel.length() - 2);
			Imovel imovel = fachada.pesquisarImovel(Integer.parseInt(idImovel));
			
			quadrasSet.add(imovel.getQuadra().getNumeroQuadra());
		}
		
		Integer[] quadras = quadrasSet.toArray(new Integer[quadrasSet.size()]);
		
		helper.setQuadrasSelecionadas(quadras);
		
		return helper;
	}
}
	


