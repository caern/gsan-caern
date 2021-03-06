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

import gcom.arrecadacao.Arrecadador;
import gcom.arrecadacao.aviso.AvisoBancario;
import gcom.arrecadacao.pagamento.Pagamento;
import gcom.arrecadacao.pagamento.PagamentoHistorico;
import gcom.cadastro.cliente.Cliente;
import gcom.cadastro.imovel.Imovel;
import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.cobranca.DocumentoTipo;
import gcom.fachada.Fachada;
import gcom.gui.GcomAction;
import gcom.util.ConstantesSistema;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 * 1� Aba do [UC0472] Pagamentos do Imovel
 * 
 * @author Rafael Santos
 * @since 05/09/2006
 */
public class ExibirConsultarImovelPagamentosAction extends GcomAction {

    /**
     * 
     * @param actionMapping
     *            Descri��o do par�metro
     * @param actionForm
     *            Descri��o do par�metro
     * @param httpServletRequest
     *            Descri��o do par�metro
     * @param httpServletResponse
     *            Descri��o do par�metro
     * @return Descri��o do retorno
     */
    public ActionForward execute(ActionMapping actionMapping,
            ActionForm actionForm, HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse) {

        ActionForward retorno = actionMapping
                .findForward("consultarImovelPagamentos");

        //Obtendo uma instancia da sessao
        HttpSession sessao = httpServletRequest.getSession(false);
        
        //Obt�m a inst�ncia da Fachada
        Fachada fachada = Fachada.getInstancia();
        
        ConsultarImovelActionForm consultarImovelActionForm = (ConsultarImovelActionForm) actionForm;

        //id do imovel da aba dados cadastrais
        String idImovelPagamentos = consultarImovelActionForm.getIdImovelPagamentos();
        String limparForm = httpServletRequest.getParameter("limparForm");
        String indicadorNovo = httpServletRequest.getParameter("indicadorNovo");
		String idImovelPrincipalAba = null;
		if(sessao.getAttribute("idImovelPrincipalAba") != null){
			idImovelPrincipalAba = (String)sessao.getAttribute("idImovelPrincipalAba");
		}
		
		// pesquisar os par�metros do sistema
		SistemaParametro sistemaParametro = fachada.pesquisarParametrosDoSistema();
		
		//Indicador Valida CPF/CNPJ
		if(sistemaParametro.getIndicadorValidaCpfCnpj() != null){
			consultarImovelActionForm.setIndicadorValidaCPFCNPJ(String.valueOf(sistemaParametro.getIndicadorValidaCpfCnpj()));
		}
        
        if(limparForm != null && !limparForm.equals("")){
            //limpar os dados 
        	httpServletRequest.setAttribute(
                    "idImovelPagamentosNaoEncontrado", null);
        	
        	sessao.removeAttribute("imovelClientes");

			consultarImovelActionForm.setIdImovelDadosComplementares(null);
			consultarImovelActionForm.setIdImovelDadosCadastrais(null);
			consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
			consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
			consultarImovelActionForm.setIdImovelDebitos(null);
			consultarImovelActionForm.setIdImovelPagamentos(null);
			consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
			consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
			consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
			consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
			consultarImovelActionForm.setImovIdAnt(null);
			consultarImovelActionForm.setIndicadorValidaCPFCNPJ("2");
			consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
        	
        	sessao.removeAttribute("imovelPagamentos");
        	sessao.removeAttribute("colecaoPagamentosImovelConta");
        	sessao.removeAttribute("colecaoPagamentosImovelGuiaPagamento");
        	sessao.removeAttribute("colecaoPagamentosImovelDebitoACobrar");
        	sessao.removeAttribute("colecaoPagamentosHistoricoImovelConta");
        	sessao.removeAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento");
   			sessao.removeAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar");
   			sessao.removeAttribute("colecaoPagamentosImovelContaHistorico");
   			
   			sessao.removeAttribute("qtdePagContas");
   			sessao.removeAttribute("qtdePagGuiaPagamento");
   			sessao.removeAttribute("qtdePagDebitoACobrar");   			
        	
        	sessao.removeAttribute("idImovelPrincipalAba");
        	consultarImovelActionForm.setIdImovelPagamentos(null);
        	consultarImovelActionForm.setMatriculaImovelPagamentos(null);
        	consultarImovelActionForm.setSituacaoAguaPagamentos(null);
        	consultarImovelActionForm.setSituacaoEsgotoPagamentos(null);
            
//        }else if(idImovelPagamentos != null && !idImovelPagamentos.equalsIgnoreCase("")){
        }else if( (idImovelPagamentos != null && !idImovelPagamentos.equalsIgnoreCase(""))
            	|| (idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")) ){ 
            	
        	if(idImovelPagamentos != null && !idImovelPagamentos.equalsIgnoreCase("")){
        		
        		if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		
        			if(indicadorNovo != null && !indicadorNovo.equals("")){
            			consultarImovelActionForm.setIdImovelPagamentos(idImovelPagamentos);            		
        				
        			}else if(!(idImovelPagamentos.equals(idImovelPrincipalAba))){
            			consultarImovelActionForm.setIdImovelPagamentos(idImovelPrincipalAba);            		
                		idImovelPagamentos = idImovelPrincipalAba;
            		}
            		
            		
            	}
        	}else if(idImovelPrincipalAba != null && !idImovelPrincipalAba.equalsIgnoreCase("")){
            		consultarImovelActionForm.setIdImovelRegistroAtendimento(idImovelPrincipalAba);            		
            		idImovelPagamentos = idImovelPrincipalAba;
            } 	                	
	        
	        Imovel imovel = null;
	        //verifica se o objeto imovel � o mesmo ja pesquisado, para n�o precisar pesquisar mas.
	        boolean imovelNovoPesquisado = false;
	        if(sessao.getAttribute("imovelPagamentos") != null){
	        	imovel = (Imovel) sessao.getAttribute("imovelPagamentos");
	        	if(!(imovel.getId().toString().equals(idImovelPagamentos.trim()))){
	        		imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelPagamentos.trim()));
	        		imovelNovoPesquisado = true;
	        	}
	        }else{
	        	imovel = fachada.consultarImovelHistoricoFaturamento(new Integer(idImovelPagamentos.trim()));
	        	imovelNovoPesquisado = true;
	        }
	
            if (imovel != null) {
                sessao.setAttribute("imovelPagamentos", imovel);
                sessao.setAttribute("idImovelPrincipalAba", imovel.getId().toString());
                consultarImovelActionForm.setIdImovelPagamentos(imovel.getId().toString());
                
				if (imovel.getIndicadorExclusao().equals(ConstantesSistema.SIM)) {
					httpServletRequest.setAttribute("imovelExcluido", true);
				}
				
				Cliente clienteUsuario = fachada.pesquisarClienteUsuarioImovelExcluidoOuNao( imovel.getId() );
				
				if(clienteUsuario != null){
					consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado(String.valueOf(clienteUsuario.getIndicadorValidaCpfCnpj()));
				}else{
					consultarImovelActionForm.setIndicadorClienteCPFCNPJValidado("2");
				}
                
                //caso o imovel pesquisado seja diferente do pesquisado anterior ou seja a primeira vez que se esteja pesquisando
                if(imovelNovoPesquisado){
	            	//seta na tela a inscri��o do imovel
	                httpServletRequest.setAttribute(
	                        "idImovelPagamentosNaoEncontrado", null);
	                
	                consultarImovelActionForm.setMatriculaImovelPagamentos(fachada.pesquisarInscricaoImovelExcluidoOuNao(new Integer(idImovelPagamentos.trim())));
	                
					//seta a situa��o de agua
					if(imovel.getLigacaoAguaSituacao() != null){
						consultarImovelActionForm.setSituacaoAguaPagamentos(imovel.getLigacaoAguaSituacao().getDescricao());
					}
					//seta a situa��o de esgoto
					if(imovel.getLigacaoEsgotoSituacao() != null){
						consultarImovelActionForm.setSituacaoEsgotoPagamentos(imovel.getLigacaoEsgotoSituacao().getDescricao());
					}

		 			// 1. O sistema seleciona os pagamentos do im�vel 
					//(a partir da tabela PAGAMENTO com IMOV_ID = Id do im�vel informado 
					//e demais par�metros de sele��o informada)
			
					//pesquisa utilizada pelo do Consultar Pagamento
					Collection colecaoImoveisPagamentos = fachada.pesquisarPagamentoImovel(idImovelPagamentos.trim(), null, null, null,
						null, null, null,null, null,null, null,null, null,null, null,null, null, null, null);

					Collection colecaoImoveisPagamentosHistorico = fachada.pesquisarPagamentoHistoricoImovel(idImovelPagamentos.trim(), null, null, null,
							null, null, null,null, null,null, null,null, null,null, null,null, null);

					
					//N�o h� Pagamentos para o im�vel de matr�cula {0}.
					/*if (colecaoImoveisPagamentos == null || colecaoImoveisPagamentos.isEmpty()) {
			        	httpServletRequest.setAttribute(
			                    "idImovelPagamentosNaoEncontrado", null);

			        	sessao.removeAttribute("imovelPagamentos");
			        	sessao.removeAttribute("colecaoPagamentosImovelConta");
			        	sessao.removeAttribute("colecaoPagamentosImovelGuiaPagamento");
			        	sessao.removeAttribute("colecaoPagamentosImovelDebitoACobrar");
			        	sessao.removeAttribute("idImovelPrincipalAba");
			        	consultarImovelActionForm.setIdImovelPagamentos(null);
			        	consultarImovelActionForm.setMatriculaImovelPagamentos(null);
			        	consultarImovelActionForm.setSituacaoAguaPagamentos(null);
			        	consultarImovelActionForm.setSituacaoEsgotoPagamentos(null);
						
						
						
							throw new ActionServletException(
									"atencao.nao.pagamentos.imovel", null, ""
									+ idImovelPagamentos.trim());
					}*/
					
					// Im�vel
					Collection<Pagamento> colecaoPagamentosImovelConta = new ArrayList();
					Collection<Pagamento> colecaoPagamentosImovelGuiaPagamento = new ArrayList();
					Collection<Pagamento> colecaoPagamentosImovelDebitoACobrar = new ArrayList();
					
					//Caso o Pagamento tenha uma conta em historico
					Collection<Pagamento> colecaoPagamentosImovelContaHistorico = new ArrayList();
					
					Integer qtdePagContas = 0;
					Integer qtdePagGuiaPagamento = 0;
					Integer qtdePagDebitoACobrar = 0;
					Integer qtdePagContasHistorico = 0;

					// Consultar Pagamentos do Im�vel
					if (colecaoImoveisPagamentos != null && !colecaoImoveisPagamentos.isEmpty()) {

						Iterator colecaoPagamentoIterator = colecaoImoveisPagamentos.iterator();

						// Divide os pagamentos do im�vel pelo tipo de pagamento
						while (colecaoPagamentoIterator.hasNext()) {
							Pagamento pagamento = ((Pagamento) colecaoPagamentoIterator.next());

							/*
							 * Alterado por Raphael Rossiter em 15/01/2007 - Analistas: Aryed e Eduardo
							 * OBJ: Mostrar todos os pagamentos da tabela de Pagamento
							 */ 
							if (pagamento.getDocumentoTipo().getId().equals(
									DocumentoTipo.DEBITO_A_COBRAR)) {
								colecaoPagamentosImovelDebitoACobrar.add(pagamento);
							} else if (pagamento.getDocumentoTipo().getId().equals(
									DocumentoTipo.GUIA_PAGAMENTO)) {
								colecaoPagamentosImovelGuiaPagamento.add(pagamento);
							}
							else{
								//Caso o pagamento possua uma conta que j� foi para historico, 
								//Pesquisa a conta na tabela de conta historico
//								if (pagamento.getContaGeral() != null && pagamento.getContaGeral().getIndicadorHistorico() == ContaGeral.INDICADOR_HISTORICO) {
//									colecaoPagamentosImovelContaHistorico.add(pagamento);
//								} else {
									colecaoPagamentosImovelConta.add(pagamento);
//								}
								
							}
						}

						// Organizar a cole��o de Conta
						if (colecaoPagamentosImovelConta != null
								&& !colecaoPagamentosImovelConta.isEmpty()) {
							Collections.sort((List) colecaoPagamentosImovelConta,
									new Comparator() {
										public int compare(Object a, Object b) {
											Integer anoMesReferencia1 = ((Pagamento) a)
													.getAnoMesReferencia();
											Integer anoMesReferencia2 = ((Pagamento) b)
													.getAnoMesReferencia();

											return anoMesReferencia1
													.compareTo(anoMesReferencia2);

										}
								});
							
							
							sessao.setAttribute("colecaoPagamentosImovelConta",
									colecaoPagamentosImovelConta);
							
							qtdePagContas = colecaoPagamentosImovelConta.size();
						}
						else{
							
							sessao.removeAttribute("colecaoPagamentosImovelConta");
						}
						
						//Organizar a cole��o de Conta Historico
						if (colecaoPagamentosImovelContaHistorico != null
								&& !colecaoPagamentosImovelContaHistorico.isEmpty()) {
							Collections.sort((List) colecaoPagamentosImovelContaHistorico,
									new Comparator() {
										public int compare(Object a, Object b) {
											Integer anoMesReferencia1 = ((Pagamento) a)
													.getAnoMesReferencia();
											Integer anoMesReferencia2 = ((Pagamento) b)
													.getAnoMesReferencia();

											return anoMesReferencia1
													.compareTo(anoMesReferencia2);

										}
								});
							
							
							sessao.setAttribute("colecaoPagamentosImovelContaHistorico",
									colecaoPagamentosImovelContaHistorico);
							
							qtdePagContasHistorico = colecaoPagamentosImovelContaHistorico.size();
						}
						else{
							
							sessao.removeAttribute("colecaoPagamentosImovelContaHistorico");
						}

						// Organizar a cole��o de Guia de Pagamento 
						if (colecaoPagamentosImovelGuiaPagamento != null
								&& !colecaoPagamentosImovelGuiaPagamento.isEmpty()) {
							Collections.sort((List) colecaoPagamentosImovelGuiaPagamento,
									new Comparator() {
										public int compare(Object a, Object b) {
											String tipoDebito1 = ((Pagamento) a)
													.getDebitoTipo() == null ? ""
													: ((Pagamento) a).getDebitoTipo()
															.getDescricao();
											String tipoDebito2 = ((Pagamento) b)
													.getDebitoTipo() == null ? ""
													: ((Pagamento) b).getDebitoTipo()
															.getDescricao();

											return tipoDebito1.compareTo(tipoDebito2);

										}
									});
							
							
							sessao.setAttribute("colecaoPagamentosImovelGuiaPagamento",
									colecaoPagamentosImovelGuiaPagamento);
							
							qtdePagGuiaPagamento = colecaoPagamentosImovelGuiaPagamento.size();
						}
						else{
							
							sessao.removeAttribute("colecaoPagamentosImovelGuiaPagamento");
						}
						
						// Organizar a cole��o de Guia de Pagamento 
						if (colecaoPagamentosImovelDebitoACobrar != null
								&& !colecaoPagamentosImovelDebitoACobrar.isEmpty()) {

							// Organizar a cole��o
							Collections.sort((List) colecaoPagamentosImovelDebitoACobrar,
									new Comparator() {
										public int compare(Object a, Object b) {
											Integer anoMesReferencia1 = ((Pagamento) a)
													.getAnoMesReferencia();
											Integer anoMesReferencia2 = ((Pagamento) b)
													.getAnoMesReferencia();

											return anoMesReferencia1
													.compareTo(anoMesReferencia2);

										}
									});
							
							
							sessao.setAttribute("colecaoPagamentosImovelDebitoACobrar",
									colecaoPagamentosImovelDebitoACobrar);
							
							qtdePagDebitoACobrar =  colecaoPagamentosImovelDebitoACobrar.size();
						}
						else{
							
							sessao.removeAttribute("colecaoPagamentosImovelDebitoACobrar");
						}
						
					}
					else{
						
						sessao.removeAttribute("colecaoPagamentosImovelConta");
		            	sessao.removeAttribute("colecaoPagamentosImovelGuiaPagamento");
		            	sessao.removeAttribute("colecaoPagamentosImovelDebitoACobrar");
					}
					
					
					// Im�vel - Pagamento Historico
					Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelConta = new ArrayList();
					Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelGuiaPagamento = new ArrayList();
					Collection<PagamentoHistorico> colecaoPagamentosHistoricoImovelDebitoACobrar = new ArrayList();
					
					
					
					// Consultar Pagamentos do Im�vel
					if (colecaoImoveisPagamentosHistorico != null && !colecaoImoveisPagamentosHistorico.isEmpty()) {

						Iterator colecaoPagamentoHistoricoIterator = colecaoImoveisPagamentosHistorico.iterator();
					
						// Divide os pagamentos do im�vel pelo tipo de pagamento
						while (colecaoPagamentoHistoricoIterator.hasNext()) {
							PagamentoHistorico pagamentoHistorico = ((PagamentoHistorico) colecaoPagamentoHistoricoIterator.next());
							
							if (pagamentoHistorico.getAvisoBancario() == null) {
								AvisoBancario avisoBancario = new AvisoBancario();
								Arrecadador arrecadador = new Arrecadador();
								Cliente cliente = new Cliente();
								
								String nomeCliente = fachada.pesquisarNomeAgenteArrecadador(pagamentoHistorico.getId());
								
								if (nomeCliente != null){
									
									cliente.setNome(nomeCliente);
									arrecadador.setCliente(cliente);
									avisoBancario.setArrecadador(arrecadador);
									pagamentoHistorico.setAvisoBancario(avisoBancario);
								}
							}
							
							/*
							 * Alterado por Raphael Rossiter em 15/01/2007 - Analistas: Aryed e Eduardo
							 * OBJ: Mostrar todos os pagamentos da tabela de Pagamento
							 */
							if (pagamentoHistorico.getDocumentoTipo().getId().equals(
									DocumentoTipo.DEBITO_A_COBRAR)) {
								colecaoPagamentosHistoricoImovelDebitoACobrar.add(pagamentoHistorico);
							} else if (pagamentoHistorico.getDocumentoTipo().getId().equals(
									DocumentoTipo.GUIA_PAGAMENTO)) {
								colecaoPagamentosHistoricoImovelGuiaPagamento.add(pagamentoHistorico);
							}
							else{
								colecaoPagamentosHistoricoImovelConta.add(pagamentoHistorico);
							}
						}
	
						// Organizar a cole��o de Conta
						if (colecaoPagamentosHistoricoImovelConta != null
								&& !colecaoPagamentosHistoricoImovelConta.isEmpty()) {
							/*Collections.sort((List) colecaoPagamentosHistoricoImovelConta,
									new Comparator() {
										public int compare(Object a, Object b) {
											Integer anoMesReferencia1 = ((PagamentoHistorico) a)
													.getAnoMesReferencia();
											Integer anoMesReferencia2 = ((PagamentoHistorico) b)
													.getAnoMesReferencia();
	
											return anoMesReferencia1
													.compareTo(anoMesReferencia2);
	
										}
								});*/
							
							
							sessao.setAttribute("colecaoPagamentosHistoricoImovelConta",
									colecaoPagamentosHistoricoImovelConta);
							
							qtdePagContas = qtdePagContas + colecaoPagamentosHistoricoImovelConta.size();
						}
						else{
							
							sessao.removeAttribute("colecaoPagamentosHistoricoImovelConta");
						}
	
						// Organizar a cole��o de Guia de Pagamento 
						if (colecaoPagamentosHistoricoImovelGuiaPagamento != null
								&& !colecaoPagamentosHistoricoImovelGuiaPagamento.isEmpty()) {
							/*Collections.sort((List) colecaoPagamentosHistoricoImovelGuiaPagamento,
									new Comparator() {
										public int compare(Object a, Object b) {
											String tipoDebito1 = ((PagamentoHistorico) a)
													.getDebitoTipo() == null ? ""
													: ((PagamentoHistorico) a).getDebitoTipo()
															.getDescricao();
											String tipoDebito2 = ((PagamentoHistorico) b)
													.getDebitoTipo() == null ? ""
													: ((PagamentoHistorico) b).getDebitoTipo()
															.getDescricao();
	
											return tipoDebito1.compareTo(tipoDebito2);
	
										}
									});*/
							
							
							sessao.setAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento",
									colecaoPagamentosHistoricoImovelGuiaPagamento);
							qtdePagGuiaPagamento =  qtdePagGuiaPagamento + colecaoPagamentosHistoricoImovelGuiaPagamento.size();
						}
						else{
							
							sessao.removeAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento");
						}
						
						// Organizar a cole��o de D�bito a Cobrar
						if (colecaoPagamentosHistoricoImovelDebitoACobrar != null
								&& !colecaoPagamentosHistoricoImovelDebitoACobrar.isEmpty()) {
	
							// Organizar a cole��o
							/*Collections.sort((List) colecaoPagamentosHistoricoImovelDebitoACobrar,
									new Comparator() {
										public int compare(Object a, Object b) {
											Integer anoMesReferencia1 = ((PagamentoHistorico) a)
													.getAnoMesReferencia();
											Integer anoMesReferencia2 = ((PagamentoHistorico) b)
													.getAnoMesReferencia();
	
											return anoMesReferencia1
													.compareTo(anoMesReferencia2);
	
										}
									});*/
							
							
							sessao.setAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar",
									colecaoPagamentosHistoricoImovelDebitoACobrar);
							qtdePagDebitoACobrar = qtdePagDebitoACobrar + colecaoPagamentosHistoricoImovelDebitoACobrar.size();
						}
						else{
							
							sessao.removeAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar");
						}
					}
					else{
						
						sessao.removeAttribute("colecaoPagamentosHistoricoImovelConta");
		            	sessao.removeAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento");
		       			sessao.removeAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar");
					}
                
							
					sessao.setAttribute("qtdePagContas",qtdePagContas);
					sessao.setAttribute("qtdePagGuiaPagamento",qtdePagGuiaPagamento);
					sessao.setAttribute("qtdePagDebitoACobrar",qtdePagDebitoACobrar);
					sessao.setAttribute("qtdePagContasHistorico",qtdePagContasHistorico);
                }
            } else {
                httpServletRequest.setAttribute(
                        "idImovelPagamentosNaoEncontrado", "true");
                consultarImovelActionForm.setMatriculaImovelPagamentos("IM�VEL INEXISTENTE");
                
                //limpar os dados pesquisados
                sessao.removeAttribute("imovelPagamentos");
            	sessao.removeAttribute("colecaoPagamentosImovelConta");
            	sessao.removeAttribute("colecaoPagamentosImovelGuiaPagamento");
            	sessao.removeAttribute("colecaoPagamentosImovelDebitoACobrar");
            	
            	sessao.removeAttribute("colecaoPagamentosHistoricoImovelConta");
            	sessao.removeAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento");
       			sessao.removeAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar");
       			sessao.removeAttribute("idImovelPrincipalAba");
            	
       			sessao.removeAttribute("qtdePagContas");
       			sessao.removeAttribute("qtdePagGuiaPagamento");
       			sessao.removeAttribute("qtdePagDebitoACobrar");
       			
       			
       			consultarImovelActionForm.setIdImovelDadosComplementares(null);
				consultarImovelActionForm.setIdImovelDadosCadastrais(null);
				consultarImovelActionForm.setIdImovelAnaliseMedicaoConsumo(null);
				consultarImovelActionForm.setIdImovelHistoricoFaturamento(null);
				consultarImovelActionForm.setIdImovelDebitos(null);
				consultarImovelActionForm.setIdImovelPagamentos(null);
				consultarImovelActionForm.setIdImovelDevolucoesImovel(null);
				consultarImovelActionForm.setIdImovelDocumentosCobranca(null);
				consultarImovelActionForm.setIdImovelParcelamentosDebitos(null);
				consultarImovelActionForm.setIdImovelRegistroAtendimento(null);
				consultarImovelActionForm.setImovIdAnt(null);
            	consultarImovelActionForm.setSituacaoAguaPagamentos(null);
            	consultarImovelActionForm.setSituacaoEsgotoPagamentos(null);
            }
        }else{
        	 consultarImovelActionForm.setIdImovelPagamentos(idImovelPagamentos);

         	httpServletRequest.setAttribute(
                    "idImovelPagamentosNaoEncontrado", null);

        	sessao.removeAttribute("imovelPagamentos");
        	sessao.removeAttribute("colecaoPagamentosImovelConta");
        	sessao.removeAttribute("colecaoPagamentosImovelGuiaPagamento");
        	sessao.removeAttribute("colecaoPagamentosImovelDebitoACobrar");
        	sessao.removeAttribute("colecaoPagamentosHistoricoImovelConta");
        	sessao.removeAttribute("colecaoPagamentosHistoricoImovelGuiaPagamento");
   			sessao.removeAttribute("colecaoPagamentosHistoricoImovelDebitoACobrar");

   			sessao.removeAttribute("qtdePagContas");
   			sessao.removeAttribute("qtdePagGuiaPagamento");
   			sessao.removeAttribute("qtdePagDebitoACobrar");   			
   			
        	sessao.removeAttribute("idImovelPrincipalAba");
        	consultarImovelActionForm.setMatriculaImovelPagamentos(null);
        	consultarImovelActionForm.setSituacaoAguaPagamentos(null);
        	consultarImovelActionForm.setSituacaoEsgotoPagamentos(null);
        
        }

        return retorno;
    }

}
