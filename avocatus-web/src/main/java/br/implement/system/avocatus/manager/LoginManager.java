/**
 * 
 */
package br.implement.system.avocatus.manager;

import javax.crypto.EncryptedPrivateKeyInfo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.implement.system.avocatus.business.UsuarioBO;
import br.implement.system.avocatus.entity.Usuario;
import br.implement.system.avocatus.utils.Encripta;
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
	
	private String email;
	private String senha;
	
	@Inject
	private UsuarioBO usuarioBO;

	public String loggar(){
		
		Usuario usuario = usuarioBO.loggar(this.email, this.senha);
//		if(usuario.isFuncionario() && usuario.isPrimeiroAcesso()){
//			return 
//		}
		
		return Navigation.HOME_FUNCIONARIO;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
