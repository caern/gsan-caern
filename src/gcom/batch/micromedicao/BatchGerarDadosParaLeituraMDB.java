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
package gcom.batch.micromedicao;

import gcom.cadastro.sistemaparametro.SistemaParametro;
import gcom.faturamento.ControladorFaturamentoLocal;
import gcom.faturamento.ControladorFaturamentoLocalHome;
import gcom.micromedicao.ControladorMicromedicaoLocal;
import gcom.micromedicao.ControladorMicromedicaoLocalHome;
import gcom.micromedicao.Rota;
import gcom.micromedicao.leitura.LeituraTipo;
import gcom.util.ConstantesJNDI;
import gcom.util.ControladorException;
import gcom.util.ServiceLocator;
import gcom.util.ServiceLocatorException;
import gcom.util.SistemaException;

import java.util.Collection;
import java.util.Iterator;

import javax.ejb.CreateException;
import javax.ejb.EJBException;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

public class BatchGerarDadosParaLeituraMDB implements MessageDrivenBean,
		MessageListener {
	
	private static final long serialVersionUID = 1L;

	public BatchGerarDadosParaLeituraMDB() {
		super();
	}

	public void setMessageDrivenContext(MessageDrivenContext ctx)
			throws EJBException {

	}

	public void ejbRemove() throws EJBException {

	}

	public void onMessage(Message message) {
		if (message instanceof ObjectMessage) {

			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				
				
				Collection colecaoRotas = (Collection) ((Object[]) objectMessage.getObject()) [0];
				
				Integer anoMesCorrente = (Integer) ((Object[]) objectMessage.getObject())[1];
				Integer idGrupoFaturamento = (Integer) ((Object[]) objectMessage.getObject())[2];
				SistemaParametro sistemaParametro = (SistemaParametro) ((Object[]) objectMessage.getObject())[3];
				Integer idFuncionalidadeIniciada = (Integer) ((Object[]) objectMessage.getObject())[4];
				
				Iterator iterator = colecaoRotas.iterator();
				
				//Collection colecaoMicroColetor = new ArrayList();
				//Collection colecaoPreFaturamento = new ArrayList();
				
				while(iterator.hasNext()) {
				
					Rota rota = (Rota)iterator.next();
					
					if(rota.getLeituraTipo().getId().intValue() == LeituraTipo.CONVENCIONAL.intValue()){
						
						this.getControladorMicromedicao().gerarDadosPorLeituraParaInserir(
							rota ,
							anoMesCorrente, 
							idGrupoFaturamento, 
							sistemaParametro, 
							idFuncionalidadeIniciada);
						
					}else if (rota.getLeituraTipo().getId().intValue() == LeituraTipo.CELULAR_MOBILE.intValue()){
						
						this.getControladorMicromedicao().gerarDadosPorLeituraParaInserir(
							rota ,
							anoMesCorrente, 
							idGrupoFaturamento, 
							sistemaParametro, 
							idFuncionalidadeIniciada);
					
					}else if (rota.getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_E_ENTRADA_SIMULTANEA.intValue() ||
                            rota.getLeituraTipo().getId().intValue() == LeituraTipo.CELULAR_MOBILE_ANDROID.intValue() || 
                            rota.getLeituraTipo().getId().intValue() == LeituraTipo.LEITURA_ANDROID.intValue()){
						this.getControladorFaturamento().preFaturarGrupoFaturamento(rota,
								anoMesCorrente,
								idGrupoFaturamento,idFuncionalidadeIniciada);
					
					}
					 /*else if ((sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_COMPESA))
							&& (rota.getLeituraTipo().getId().intValue() == LeituraTipo.MICROCOLETOR.intValue())){
						
						colecaoMicroColetor.add(rota);
						
					}*/else if((!sistemaParametro.getNomeAbreviadoEmpresa().equalsIgnoreCase(SistemaParametro.EMPRESA_COMPESA))
							&& rota.getLeituraTipo().getId().intValue() == LeituraTipo.MICROCOLETOR.intValue()){
						
						this.getControladorMicromedicao().gerarDadosPorLeituraParaInserir(rota
						,anoMesCorrente, idGrupoFaturamento, sistemaParametro, idFuncionalidadeIniciada);
					}
					
					rota = null;
					
				}
				
				/*if (!colecaoMicroColetor.isEmpty()){
					this.getControladorMicromedicao().gerarDadosPorLeituraMicroColetor(colecaoRotas,anoMesCorrente,
							idGrupoFaturamento,sistemaParametro,idFuncionalidadeIniciada);
				}*/



			} catch (JMSException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			} catch (ControladorException e) {
				System.out.println("Erro no MDB");
				e.printStackTrace();
			}
		}

	}

	/**
	 * Retorna o valor de controladorMicromedicao
	 * 
	 * @return O valor de controladorMicromedicao
	 */
	private ControladorMicromedicaoLocal getControladorMicromedicao() {
		ControladorMicromedicaoLocalHome localHome = null;
		ControladorMicromedicaoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorMicromedicaoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_MICROMEDICAO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas �
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Retorna o valor de controladorLocalidade
	 * 
	 * @return O valor de controladorLocalidade
	 */
	private ControladorFaturamentoLocal getControladorFaturamento() {
		ControladorFaturamentoLocalHome localHome = null;
		ControladorFaturamentoLocal local = null;

		// pega a inst�ncia do ServiceLocator.

		ServiceLocator locator = null;

		try {
			locator = ServiceLocator.getInstancia();

			localHome = (ControladorFaturamentoLocalHome) locator
					.getLocalHomePorEmpresa(ConstantesJNDI.CONTROLADOR_FATURAMENTO_SEJB);
			// guarda a referencia de um objeto capaz de fazer chamadas
			// objetos remotamente
			local = localHome.create();

			return local;
		} catch (CreateException e) {
			throw new SistemaException(e);
		} catch (ServiceLocatorException e) {
			throw new SistemaException(e);
		}
	}
	
	/**
	 * Default create method
	 * 
	 * @throws CreateException
	 */
	public void ejbCreate() {

	}
}
