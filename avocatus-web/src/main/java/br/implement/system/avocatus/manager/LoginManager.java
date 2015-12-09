/**
 * 
 */
package br.implement.system.avocatus.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author adrianopatrick@gmail.com
 * @since 4 de dez de 2015
 */
@ViewScoped
@Scope("session")
@Component(value = "loginManager")
@ManagedBean(name = "loginManager")
public class LoginManager {

	public String loggar(){
		return "home";
	}
}
