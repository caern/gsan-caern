<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<logic:notPresent scope="session" name="origemGIS">

<table width="770" cellspacing="4" cellpadding="0">
	<tr>
    	<td>
      	<table width="100%" cellpadding="2" class="footer">
        	<tr>
          		<td  align="left"> 
	          		<logic:present scope="application" name="versaoDataBase"> 
					Banco: ${applicationScope.versaoDataBase}
					</logic:present>  
					<logic:notPresent scope="application" name="versaoDataBase"> 
					PMSS
					</logic:notPresent>
				</td>

          		<td align="right">Vers&atilde;o: 9.2.11.2p (${applicationScope.versaoTipo}) 12/11/2014 - 14:37:42 </td>
        	</tr>
      	</table>
		</td>
  	</tr>
</table>
</logic:notPresent>