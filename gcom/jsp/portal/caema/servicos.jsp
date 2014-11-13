<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN">

<html:html>
	<head>
		<title>CAEMA | Servi&ccedil;os</title>
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery-1.4.2.min.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.portal.js"/>jquery.blockUI.js"></script>
		<script language="JavaScript" src="<bean:message key="caminho.js"/>util.js"></script>
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>style.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>internal.css" type="text/css">
		<link rel="stylesheet" href="<bean:message key="caminho.portalcaema.css"/>jquery.theme.css" type="text/css">
		
		<script type="text/javascript">
			$(document).ready(function(){
				$('.info-serv').hide();
				$('#lista-servicos li, #lista-informacoes li').hover(function(){
					$('.ativo').removeClass('ativo');
					$(this).find('.info-serv').fadeIn(50);
					$(this).find('a').addClass('ativo').css('color', '#FFF');
				}, function(){
					$('.ativo').removeClass('ativo').css('color', '#008FD6');;
					$(this).find('.info-serv').fadeOut(50);
				});
			
				$('.confirm').click(function(){
					$.unblockUI();
				});
			});

			function esferaPoderNaoPermiteGerarCertidaoNegativa() {

					$.blockUI({
						message : '<h3 style="text-align:left; padding-top:10px; padding-bottom: 10px;">A esfera de poder associado ao cliente respons�vel n�o permite certid�o negativa para o im�vel.</h3>'
								 +'<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>',
						theme : true,
						title : 'Aviso'
					});

					$('.confirm').live('click', function(){
						$.unblockUI();
						inputComFocus.focus()
					});

			}
		</script>
		<logic:present name="imovelSemDebito" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemDebitos'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#debitoParceladoMesCorrente'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSemQuitacaoAnual" scope="request">
				<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemQuitacaoAnual'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<!-- Valida��es Inicias de efetuar parcelamento de d�bitos -->
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelParcelamentoAtivo'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSemDebitos" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSemDebitos'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelNaoPossuiPerfilParcelamento'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#quantidadeReparcelNaoPermiteParcel'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#naoExistePerfilSituacaoImovel'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#imovelSituacaoCobranca'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
			<script type="text/javascript">
				$(document).ready(function(){
					$.blockUI({
						message : $('#numeroMesesMinimoVencimentoAlternativoSuperiorPermitido'),
						theme : true,
						title : 'Aviso'
					});
				});
			</script>
		</logic:present>
		<!-- Fim das valida��es Inicias de efetuar parcelamento de d�bitos -->
	</head>
	
	<body>
		<div id="container"> 
		   <html:form action="/exibirServicosPortalCaemaAction.do?method=servicos" method="post" 
				name="ExibirServicosPortalCaemaActionForm" type="gcom.gui.portal.caema.ExibirServicosPortalCaemaActionForm" >
	    	<%@ include file="/jsp/portal/caema/cabecalho.jsp"%>
	        
	        <!-- Content - Start -->
	         <div id="content">
	         <%@ include file="/jsp/portal/caema/cabecalhoImovel.jsp"%>
	            
	            <ul id="lista-informacoes">
	                <li>
	            		<a href="emitirSegundaViaContaPortalCaemaAction.do">
	            			<span>2� Via da conta</span>
            			</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>Este acesso permite solicitar a segunda via da sua conta, que poder� ser paga nos agentes recebedores da CAEMA. Para acessar este servi�o, clique no assunto &quot;2� Via de conta&quot; e digite o n�mero da sua matr�cula, e aguarde o t�rmino da impress�o  para pagamento, ou se preferir dirija-se a uma loja de atendimento, ou entre em contato com o call center pelo n�mero 0800 701 0195 ou pelo link fale conosco.</p>
	                        <p>A CAEMA disponibiliza mais este servi�o sem custo adicional.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-2">
	                	<a href="exibirServicosPortalCaemaAction.do?method=declaracaoAnual">
	                		<span style="font-size:14px;">Declara��o anual de quita��o de d�bito</span>
                		</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>Conforme determina o artigo 3� da lei federal 12.007 de 2009 a CAEMA disponibiliza para voc� a declara��o de quita��o anual de d�bitos. 
	                        Lembramos que para este acesso o cliente dever� estar em dia com suas contas referentes ao ano da solicita��o da Declara��o.</p>
	                        <p>Para acessar este servi�o, clique no assunto declara��o anual de quita��o de d�bito ou dirija-se a uma loja de atendimento.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-3">
	                	<a href="exibirInserirCadastroEmailClientePortalCaemaAction.do?ok=sim">
	                		<span>Recebimento de fatura por e-mail</span>
                		</a>
	                	<div class="info-serv" style="text-align:justify;">
	                        <p>A CAEMA disponibiliza para voc� a facilidade de receber suas faturas em seu e-mail, para acessar este servi�o, 
	                        clique no assunto recebimento de fatura por e-mail fa�a seu cadastro e receba suas contas sem sair de casa, ou dirija-se a uma
							loja de atendimento.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	                <li id="serv-5">
	                	<a href="exibirInserirSolicitacaoServicosPortalCaemaAction.do?init=1">
	                		<span>Outros servi�os</span>
                		</a>
	                	<div class="info-serv">
	                        <p></p>
	                        <p>Por este acesso, ser� poss�vel solicitar alguns servi�os. Fa�a sua op��o.</p>
	                        <p></p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
                    	</div>
	                </li>
                     <li>
	            		<a href="exibirConsultarImovelPagamentosPortalCaemaAction.do">
							<span>Consultar pagamento </span>
						</a>
						<div class="info-serv" style="text-align:justify;display:block;">
	                        <p></p>
	                        <p>Permite consultar o hist&oacute;rico dos pagamentos efetuados.</p>
	                        <p></p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>
	              <!-- <li>
	            		<a href="exibirConsultarConsumoHistoricoAguaPortalCaemaAction.do">
							<span>Consultar hist&oacute;rico de consumo </span>
						</a>
						<div class="info-serv" style="text-align:justify;display:block;">
	                        <p></p>
	                        <p>Consulta do hist&oacute;rico do volume de &aacute;gua fornecido referentes nos &uacute;ltimos meses.</p>
	                        <p></p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
	                    </div>
	                </li>  -->   
	                 <li id="serv-4">
	                	<a href="exibirInformarVencimentoAlternativoPortalAction.do">
	                		<span>Alterar vencimento de conta</span>
                		</a>
	                	<div class="info-serv">
	                        <p>Conforme determina a lei federal Lei n� 9.791, 24 de Mar�o de 1999 que disp�e sobre a obrigatoriedade de as concession�rias de servi�os 
	                        p�blicos estabelecerem ao consumidor e ao usu�rio datas opcionais para o vencimento de seus d�bitos.
							Aqui o cliente pode solicitar altera��o da data de pagamento de sua Fatura.</p>
	                        <span id="bottom">&nbsp;</span>
	                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
                    	</div>
	                </li>
	                  
	                  
	                  <logic:present name="esferaPoderNAOPermiteGerarCertidaoNegativa" scope="session">
	               			 <li id="serv-5">
			                	<a href="javascript:esferaPoderNaoPermiteGerarCertidaoNegativa();">
			                		<span>Certid�o Negativa de D�bitos</span>
		                		</a>
			                	<div class="info-serv">
			                        <p><br>Permite a emiss�o de Certid�o Negativa de D�bito.<br></p>
			                        <span id="bottom">&nbsp;</span>
			                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
		                    	</div>
			                 </li>
		              </logic:present>
	                  
	                  <logic:notPresent name="esferaPoderNAOPermiteGerarCertidaoNegativa" scope="session">
	                 		
	                 		<logic:present name="gerarCertidaoNegativa" scope="session">
				                 <li id="serv-5">
				                	<a href="emitirSegundaViaContaPortalCaemaAction.do">
				                		<span>Certid�o negativa de d�bitos</span>
			                		</a>
				                	<div class="info-serv">
				                		<p></p>
				                        <p><br>Permite a emiss�o de certid�o negativa de d�bito.<br></p>
				                        <p></p>
				                        <span id="bottom">&nbsp;</span>
				                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
			                    	</div>
				                </li>
			                </logic:present>
			                
			                 
			                <logic:notPresent name="gerarCertidaoNegativa" scope="session">
				                 <li id="serv-5">
				                	<a href="javascript:abrirPopup('gerarCertidaoNegativaAction.do?lojaVirtual=sim', 400, 800);">
				                		<span>Certid�o negativa de d�bitos</span>
			                		</a>
				                	<div class="info-serv">
				                        <p><br>Permite a emiss�o de Certid�o Negativa de D�bito.<br></p>
				                        <span id="bottom">&nbsp;</span>
				                        <img src="imagens/portal/caema/general/seta-info-servicos.gif" alt="Seta" />
			                    	</div>
				                </li>
			                </logic:notPresent>
							
	                 </logic:notPresent>
	                 
	            </ul>
	        </div>
	        <!-- Content - End -->
	        
	       <%@ include file="/jsp/portal/caema/rodape.jsp"%>
	       </html:form>
	    </div>
	    
		<!-- Avisos -->
		<logic:present name="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" scope="request">
			<div id="numeroMesesMinimoVencimentoAlternativoSuperiorPermitido" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;"> Im�vel com vencimento alterado h� menos de <bean:write name="numeroMesesMinimo" scope="request" /> meses.</h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		<logic:present name="imovelSemDebito" scope="request">
			<div id="imovelSemDebitos" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:center; padding-top:10px; padding-bottom: 10px;">Im�vel sem d�bitos.</h3> 
				<a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemQuitacaoAnual" scope="request">
			<div id="imovelSemQuitacaoAnual" style="display:none;cursor:default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="text-align:justify; padding-top:10px; padding-bottom: 10px;">Im�vel sem declara��o anual de quita��o de d�bitos</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 701 0195
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<!-- Valida��es Inicias de efetuar parcelamento de d�bitos -->
		<logic:present name="imovelParcelamentoAtivo" scope="request">
			<div id="imovelParcelamentoAtivo" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Im�vel j� possui um parcelamento n�o quitado/cobrado. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 701 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSemDebitos" scope="request">
			<div id="imovelSemDebitos" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O Im�vel informado n�o possui d�bitos. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 701 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="debitoParceladoMesCorrente" scope="request">
			<div id="debitoParceladoMesCorrente" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">O d�bito deste im�vel j� foi parcelado no m�s de faturamento corrente. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 701 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelNaoPossuiPerfilParcelamento" scope="request">
			<div id="imovelNaoPossuiPerfilParcelamento" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">N�o existe perfil de parcelamento correspondente � situa��o do im�vel. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 701 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="quantidadeReparcelNaoPermiteParcel" scope="request">
			<div id="quantidadeReparcelNaoPermiteParcel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Quantidade de reparcelamento do im�vel n�o permite um novo parcelamento. </h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 701 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="naoExistePerfilSituacaoImovel" scope="request">
			<div id="naoExistePerfilSituacaoImovel" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">N�o existe perfil de parcelamento correspondente � situa��o do im�vel.</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 701 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<logic:present name="imovelSituacaoCobranca" scope="request">
			<div id="imovelSituacaoCobranca" style="display:none; cursor: default;">
				<img alt="Aviso" src="imagens/portal/caema/icons/warning.png" alt="Aviso" style="float: left; padding-right:10px; margin-top: 10px;">
		        <h3 style="padding-top:10px; padding-bottom: 10px;">Im�vel com situa��o de cobran�a, n�o � possivel fazer o parcelamento de d�bitos.</h3>
		        <p>
		        	Em caso de d�vidas, procure uma loja de atendimento mais pr�xima, ou entre em contato com o call center pelo 0800 701 0195.
		        </p>
		        <a href="javascript:void(0);" class="ui-corner-all button confirm">OK</a>
			</div>
		</logic:present>
		
		<!-- Fim das valida��es Inicias de efetuar parcelamento de d�bitos -->
	</body>
</html:html>