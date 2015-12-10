/**
 * 
 */
package br.implement.system.avocatus.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

/**
 * @author adrianopatrick@gmail.com
 * @since 2 de dez de 2015
 */
@ViewScoped
@Scope("session")
@Named(value = "menuManager")
@ManagedBean(name = "menuManager")
public class MenuManager {

	private Integer option;

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
