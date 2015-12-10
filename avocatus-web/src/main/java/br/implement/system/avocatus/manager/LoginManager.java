/**
 * 
 */
package br.implement.system.avocatus.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.implement.system.avocatus.utils.Navigation;

/**
 * @author adrianopatrick@gmail.com
 * @since 4 de dez de 2015
 */
@ViewScoped
@Scope("session")
@Named(value = "loginManager")
@ManagedBean(name = "loginManager")
public class LoginManager {

	public String loggar(){
		return Navigation.HOME_FUNCIONARIO;
	}
}
