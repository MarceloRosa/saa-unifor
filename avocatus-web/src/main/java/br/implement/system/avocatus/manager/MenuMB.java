/**
 * 
 */
package br.implement.system.avocatus.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.security.auth.login.LoginException;

import org.springframework.context.annotation.Scope;

import br.implement.system.avocatus.to.SegurancaTO;
import br.implement.system.avocatus.utils.Navigation;

/**
 * @author adrianopatrick@gmail.com
 * @since 2 de dez de 2015
 */
@Named
@ManagedBean
@RequestScoped
@Scope("session")
public class MenuMB {

	private Integer option;
	@Inject
	private SegurancaTO segurancaTO;

	public String actionMenu(int option) {
		this.option = option;
		String retorno = null;
		
		switch (this.option) {
		case 0:
			retorno = "atendimento";
			break;
		case 1:
			retorno = "acompanhamento";
			break;
		case 2:
			retorno = "agenda";
			break;
		case 3:
			retorno = "funcionarios";
			break;
		case 4:
			try {
				segurancaTO.logout();
			} catch (LoginException e) {
				// TODO criar página geral de erro
				e.printStackTrace();
			}
			return Navigation.SAIR;
		}
		
		return retorno;
	}

	public Integer getOption() {
		return option;
	}

	public void setOption(Integer option) {
		this.option = option;
	}

}
