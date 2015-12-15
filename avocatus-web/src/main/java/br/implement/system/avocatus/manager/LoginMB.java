/**
 * 
 */
package br.implement.system.avocatus.manager;

import java.io.IOException;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.implement.system.avocatus.business.UsuarioBO;
import br.implement.system.avocatus.entity.Usuario;
import br.implement.system.avocatus.entity.enums.TipoPapel;
import br.implement.system.avocatus.to.SegurancaTO;
import br.implement.system.avocatus.util.MessagesResources;
import br.implement.system.avocatus.utils.MessagesUtils;
import br.implement.system.avocatus.utils.Navigation;

/**
 * @author adrianopatrick@gmail.com
 * @since 4 de dez de 2015
 */
@RequestScoped
@Scope("session")
@Named(value = "loginManager")
@ManagedBean(name = "loginManager")
public class LoginMB {
	
	@PostConstruct
	public void carregaLocale(){
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		MessagesResources.setLocale(locale);
	}
	
	private String email;
	private String senha;
	
	@Inject
	private UsuarioBO usuarioBO;
	@Inject
	private SegurancaTO segurancaTO;

	public String loggar() throws IOException{
		
		Usuario usuario = usuarioBO.loggar(this.email, this.senha);
		
		if(usuario != null && !usuario.isPrimeiroAcesso()){
			segurancaTO.setUsuario(usuario);
			MessagesUtils.info("");
			return redirecionarPorPapel(usuario.maxPapel());
		} else if(usuario.isPrimeiroAcesso()){
			return Navigation.ATUALIZA;
		} else {
			MessagesUtils.error(MessagesResources.getMessages("erro_login_invalido"));
			return Navigation.FRACASSO;
		}
		
	}
	
	public String redirecionarPorPapel(TipoPapel tipoPapel){
		
		if(TipoPapel.CLIENTE.equals(tipoPapel)){
			return Navigation.HOME_CLIENTE;
		} else if (TipoPapel.CAPTADOR.equals(tipoPapel)){
			return Navigation.HOME_CAPTADOR;
		} else if (TipoPapel.ATENDENTE.equals(tipoPapel)){
			return Navigation.HOME_ATENDENTE;
		} else if(TipoPapel.ADVOGADO.equals(tipoPapel)){
			return Navigation.HOME_ADVOGADO;
		} else if(TipoPapel.FINANCEIRO.equals(tipoPapel)){
			return Navigation.HOME_FINANCEIRO;
		} else if(TipoPapel.ESCRITORIO.equals(tipoPapel)){
			return Navigation.HOME_ESCRITORIO;
		} else if(TipoPapel.ADMINISTRADOR.equals(tipoPapel)){
			return Navigation.HOME_ADMINISTRADOR;
		} else {
			return null;
		}
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
