<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-template.tld" prefix="template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/gsanLib.tld" prefix="gsan"%>
<%@ taglib uri="/WEB-INF/c.tld" prefix="c"%>
<%@ taglib uri="/WEB-INF/pager-taglib.tld" prefix="pg"%>


<%@page import="gcom.util.Util" isELIgnored="false"%>

<%@ page import="gcom.util.ConstantesSistema"%>

	<%@ include file="/jsp/util/telaespera.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html:html>

<head>

<%@ include file="/jsp/util/titulo.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link rel="stylesheet"
	href="<bean:message key="caminho.css"/>EstilosCompesa.css"
	type="text/css">
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>validacao/regras_validator.js"></script>
<html:javascript staticJavascript="false"
	formName="GerarRelatorioPagamentosContasCobrancaEmpresaActionForm" />
<script language="JavaScript"
	src="<bean:message key="caminho.js"/>util.js"></script>

<script language="JavaScript"
	src="<bean:message key="caminho.js"/>Calendario.js"></script>

<SCRIPT LANGUAGE="JavaScript"><!--

	
	function limparEmpresa() {
		var form = document.forms[0];
		form.idEmpresa.value = "";
		form.nomeEmpresa.value = "";
	}
	
	function limparEmpresaTecla() {
		var form = document.forms[0];
		form.nomeEmpresa.value = "";	
	}
	
	
	
	function pesquisarEmpresa() {
		var form = document.forms[0];

			abrirPopup('exibirPesquisarEmpresaAction.do?limpaForm=S', 495, 300);
	}
	



	function limparLocalidade() {
	    var form = document.forms[0];
	    
	      form.codigoLocalidade.value = '';
	      form.descricaoLocalidade.value = '';
	}
	
	
	function recuperarDadosPopup(codigoRegistro, descricaoRegistro, tipoConsulta) {

    	var form = document.forms[0];
   
	    if (tipoConsulta == 'empresa') {
			form.idEmpresa.value = codigoRegistro;
			form.nomeEmpresa.value = descricaoRegistro;
			form.nomeEmpresa.style.color = "#000000";
			form.action = 'exibirGerarRelatorioPagamentosContasCobrancaEmpresaAction.do?limpar=sim';
    		submeterFormPadrao(form);
    	}else if (tipoConsulta == 'localidade') {
		      form.codigoLocalidade.value = codigoRegistro;
		      form.descricaoLocalidade.value = descricaoRegistro;
		      form.descricaoLocalidade.style.color = "#000000";

   			 }
    
    }
	
	function validaForm() {
		var form =  document.forms[0];
			if (validateGerarRelatorioPagamentosContasCobrancaEmpresaActionForm(form)){
			 	
			 	var opcao = '';
			
				 	for(i=0; i < form.opcaoTotalizacao.length; i++) {
				 		if (form.opcaoTotalizacao[i].checked) {
				 			opcao = form.opcaoTotalizacao[i].value;
				 		}
				 	}
				 
		      		if (opcao == 'localidade') {
		      			if (form.codigoLocalidade.value == '') {
		      				alert('Preencha o c�digo da localidade');
		      				return false;
		      			}
		      		} else if (opcao == '') {
		      			alert('Preencha a op��o de totaliza��o');
		      			return false;
		      		}
					
//					submeterFormPadrao(form);
					botaoAvancarTelaEspera('/gsan/gerarRelatorioPagamentosContasCobrancaEmpresaAction.do');

				}
	}
	
	
	function limparCampos(opcaoTotalizacao){
		var form = document.forms[0];
		
		if (opcaoTotalizacao.value != 'localidade'){
			limparLocalidade() ;
		}
		
		if (opcaoTotalizacao.value != 'gerenciaRegional'){
			form.gerenciaRegionalId.value = "-1";
		}
		
		if (opcaoTotalizacao.value != 'gerenciaRegionalLocalidade'){
			form.gerenciaRegionalporLocalidadeId.value = "-1" ;
		}
		
		if (opcaoTotalizacao.value != 'unidadeNegocio'){
			form.unidadeNegocioId.value = "-1" ;
		}
	}
	

	
	
--></script>

</head>

<body leftmargin="5" topmargin="5"
	onload="javascript:setarFoco('${requestScope.nomeCampo}');">
<div id="formDiv">
<html:form action="/gerarRelatorioPagamentosContasCobrancaEmpresaAction"
	name="GerarRelatorioPagamentosContasCobrancaEmpresaActionForm"
	type="gcom.gui.cobranca.GerarRelatorioPagamentosContasCobrancaEmpresaActionForm"
	method="post">

	<%@ include file="/jsp/util/cabecalho.jsp"%>
	<%@ include file="/jsp/util/menu.jsp"%>

	


	<table width="770" border="0" cellspacing="5" cellpadding="0">
		<tr>
			<td width="150" valign="top" class="leftcoltext">
			<div align="center">
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/informacoes_usuario.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>

			<%@ include file="/jsp/util/mensagens.jsp"%>

			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			<p align="left">&nbsp;</p>
			</div>
			</td>
			<td width="625" valign="top" bgcolor="#003399" class="centercoltext">
			<table height="100%">

				<tr>
					<td></td>
				</tr>
			</table>
			<table width="100%" border="0" align="center" cellpadding="0"
				cellspacing="0">
				<tr>
					<td width="11"><img border="0" src="imagens/parahead_left.gif" /></td>
					<td class="parabg">Gerar Relat�rio de Pagamentos das Contas em
					Cobran�a por Empresa</td>
					<td width="11" valign="top"><img border="0"
						src="imagens/parahead_right.gif" /></td>
				</tr>
			</table>
			<!--Fim Tabela Reference a P�gina��o da Tela de Processo-->
			<p>&nbsp;</p>
			<table width="100%" border="0">
				<tr>
					<td colspan="3">Para gerar o relat�rio de pagamentos das contas em
					cobran�a por empresa, informe os dados abaixo:</td>
				</tr>
				
				
				
				
				<tr>
					<td width="30%"><strong>Empresa:<font color="#FF0000">*</font></strong></td>
					<td colspan="2" ><html:text maxlength="9" property="idEmpresa" size="9"
						tabindex="14"
						onkeypress="validaEnter(event, 'exibirGerarRelatorioPagamentosContasCobrancaEmpresaAction.do', 'idEmpresa'); 
						limparEmpresaTecla();
						return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].idEmpresa, 'Empresa');" />
					<a href="javascript:pesquisarEmpresa();"><img
						src="<bean:message key='caminho.imagens'/>pesquisa.gif" title="Pesquisar Empresa" width="23"
						height="21" alt="Pesquisar" border="0"></a> <logic:present
						name="empresaInexistente" scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="border: 0pt none ; background-color:#EFEFEF; color: #ff0000" />
					</logic:present> <logic:notPresent name="empresaInexistente"
						scope="request">
						<html:text property="nomeEmpresa" size="40" maxlength="40"
							readonly="true"
							style="background-color:#EFEFEF; border:0; color: #000000" />
					</logic:notPresent> <a href="javascript:limparEmpresa();"> <img
						border="0" src="imagens/limparcampo.gif" title="Apagar Empresa" height="21" width="23"> </a>
					</td>
				</tr>
				
				
				<tr>
					<td><strong>Per�odo de Refer�ncia do Pagamento:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:text maxlength="7" property="periodoComandoInicial"
						size="7" tabindex="8"
						onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraAnoMes(this, event); 
						replicarCampo(document.forms[0].periodoComandoFinal, document.forms[0].periodoComandoInicial);"
						onchange="validarCampoDataComMensagemLimpandoCampo(document.forms[0].periodoComandoInicial, 'Per�odo Refer�ncia Inicial');" />
					<strong> a</strong> <html:text maxlength="7"
						property="periodoComandoFinal" size="7" tabindex="9"
						onkeypress="return isCampoNumerico(event);"
						onkeyup="mascaraAnoMes(this, event);"
						onchange="validarCampoDataComMensagemLimpandoCampo(document.forms[0].periodoComandoFinal, 'Per�odo Refer�ncia Final');" /> </strong> (mm/aaaa)</td>
				</tr>
				
				

				<tr>
					<td width="120"><strong>Op��o do Relat�rio:<font color="#FF0000">*</font></strong></td>
					<td colspan="2"><strong> <html:radio
						property="opcaoRelatorio" value="1" /> <strong>Sint�tico <html:radio
						property="opcaoRelatorio" value="2" /> Anal�tico</strong>
					</strong></td>

				</tr>
				
				
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>
				<tr>
					<td><strong>Op&ccedil;&atilde;o de Totaliza&ccedil;&atilde;o:<font
						color="#FF0000">*</font></strong></td>
					<td colspan="2" align="left"><html:radio
						property="opcaoTotalizacao" value="estado" onclick = "limparCampos(this);"/> <strong>Estado </strong></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"><strong> <html:radio
						property="opcaoTotalizacao" value="estadoGerencia" onclick = "limparCampos(this);"/> Estado por
					Ger&ecirc;ncia Regional </strong></td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"><strong> 
						<html:radio property="opcaoTotalizacao" value="estadoUnidadeNegocio" 
						onclick = "limparCampos(this);"/>
					<strong>Estado por Unidade de Neg�cio</strong></strong></td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td colspan="2" align="left"><strong> <html:radio
						property="opcaoTotalizacao" value="estadoLocalidade" onclick = "limparCampos(this);"/> <strong>Estado</strong>
					por Localidade</strong></td>
				</tr>
				
				
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td width="36%" align="left"><strong> <html:radio
						property="opcaoTotalizacao" value="gerenciaRegional" onclick = "limparCampos(this);"/> <strong>Ger&ecirc;ncia
					Regional </strong></strong></td>
					<td width="38%" align="left"><strong><strong> <html:select
						property="gerenciaRegionalId">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select> </strong></strong></td>
				</tr>
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"><strong> <html:radio property="opcaoTotalizacao"
						value="gerenciaRegionalLocalidade" onclick = "limparCampos(this);"/> <strong>Ger&ecirc;ncia
					Regional</strong> por Localidade</strong></td>
					<td align="left"><strong><strong> <strong> <html:select
						property="gerenciaRegionalporLocalidadeId">
						<html:option value="-1">&nbsp;</html:option>
						<html:options collection="colecaoGerenciaRegional"
							labelProperty="nome" property="id" />
					</html:select> </strong> </strong></strong></td>
				</tr>
				
						<tr>
						<td><strong> <font color="#FF0000"></font></strong></td>
						<td width="36%" align="left"><strong> 
							<html:radio property="opcaoTotalizacao" value="unidadeNegocio" 
							onclick = "limparCampos(this);"/>
						<strong>Unidade de Neg�cio </strong></strong></td>
						<td width="38%" align="left"><strong><strong> 
							<html:select property="unidadeNegocioId">
							<html:option value="-1">&nbsp;</html:option>
							<html:options collection="colecaoUnidadeNegocio" labelProperty="nome" property="id" />
						</html:select> </strong></strong></td>
					</tr>
					
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"><strong> <html:radio property="opcaoTotalizacao"
						value="localidade" onclick = "limparCampos(this);" /> Localidade</strong></td>
					<td align="left"><html:text
						value="${requestScope.codigoLocalidade}"
						property="codigoLocalidade" size="3" maxlength="3"
						onkeypress="validaEnter(event, 'exibirGerarRelatorioPagamentosContasCobrancaEmpresaAction.do', 'codigoLocalidade');
						return isCampoNumerico(event);"
						onchange="validarCampoNumericoComMensagemLimpandoCampo(document.forms[0].codigoLocalidade, 'Localidade');" />
					<img src="<bean:message key="caminho.imagens"/>pesquisa.gif"
						width="23" title="Pesquisar Localidade" height="21" border="0"
						style="cursor: pointer;cursor:hand;"
						onclick="javascript:abrirPopup('exibirPesquisarLocalidadeAction.do', 400, 800);">
						
					<input type="text" name="descricaoLocalidade" readonly
							style="background-color:#EFEFEF; border:0" size="20"
							maxlength="30" value="${requestScope.descricaoLocalidade}" />
						
					<img src="<bean:message key="caminho.imagens"/>limparcampo.gif"
						width="23" height="21" title="Apagar Localidade" onclick="javascript:limparLocalidade();"
						style="cursor: pointer;cursor:hand;">
							
					<c:if test="${empty requestScope.codigoLocalidade}" var="testeCor">
						<script>
							document.GerarRelatorioPagamentosContasCobrancaEmpresaActionForm.descricaoLocalidade.style.color = '#FF0000';
						</script>
					</c:if>
					
					<c:if test="${!testeCor}">
						<script>
							document.GerarRelatorioPagamentosContasCobrancaEmpresaActionForm.descricaoLocalidade.style.color = '#000000';
						</script>
					</c:if>
					</td>
				</tr>
				
				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="left"><strong> </strong></td>
					<td align="left"> 					
						
							

					 </td>
				</tr>
				<tr>
					<td colspan="3">
					<hr>
					</td>
				</tr>


				<tr>
					<td><strong> <font color="#FF0000"></font></strong></td>
					<td align="right">
					<div align="left"><strong><font color="#FF0000">*</font></strong>
					Campos obrigat&oacute;rios</div>
					</td>
				</tr>

				<tr>
					<td><input name="Button" type="button" class="bottonRightCol"
						value="Desfazer" align="left"
						onclick="window.location.href='<html:rewrite page="/exibirGerarRelatorioPagamentosContasCobrancaEmpresaAction.do?menu=sim"/>'">
					<input name="Button" type="button" class="bottonRightCol"
						value="Cancelar" align="left"
						onclick="javascript:window.location.href='/gsan/telaPrincipal.do'"></td>
					<td colspan="2" align="right"><input type="button" name="gerar"
						class="bottonRightCol" value="Gerar"
						onClick="javascript:validaForm();" /></td>
				</tr>

			</table>
	</table>

	<%@ include file="/jsp/util/rodape.jsp"%>
</html:form>
</div>
</body>
</html:html>
