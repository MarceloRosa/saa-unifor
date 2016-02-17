/**
 * 
 */
package br.unifor.saa.manager;

import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.business.UsuarioBO;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.entity.enums.TipoPapel;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.to.SegurancaTO;
import br.unifor.saa.util.MessagesResources;
import br.unifor.saa.utils.MessagesUtils;
import br.unifor.saa.utils.Navigation;

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
	public void carregaLocale() {
		Locale locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
		MessagesResources.setLocale(locale);
	}

	private String email;
	private String senha;

	@Inject
	private UsuarioBO usuarioBO;
	@Inject
	private SegurancaTO segurancaTO;

	public String loggar() {

		try {
			Usuario usuario = usuarioBO.loggar(this.email, this.senha);
			
			if (!usuario.isPrimeiroAcesso()) {
				segurancaTO.setUsuario(usuario);
				MessagesUtils.info(MessagesResources.getMessages("home.welcome"));
				return redirecionarPorPapel(usuario.maxPapel());
			} else {
				return Navigation.ATUALIZA;
			}
		} catch (BOException e) {
			MessagesUtils.error(e.getMessage());
			return Navigation.FRACASSO;
		} 
	}

	public String redirecionarPorPapel(TipoPapel tipoPapel) {

		if (TipoPapel.PROFESSOR.equals(tipoPapel)) {
			return Navigation.HOME_PROFESSOR;
		} else if (TipoPapel.ALUNO.equals(tipoPapel)) {
			return Navigation.HOME_ALUNO;
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
