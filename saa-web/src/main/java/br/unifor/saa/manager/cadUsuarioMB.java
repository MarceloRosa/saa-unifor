package br.unifor.saa.manager;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Named;

import org.springframework.context.annotation.Scope;

import br.unifor.saa.entity.enums.TipoPapel;
import br.unifor.saa.utils.Navigation;

@RequestScoped
@Scope("session")
@Named(value = "cadUsuarioManager")
@ManagedBean(name = "cadUsuarioManager")
public class cadUsuarioMB {
	
	private String nome;
	private String email;
	private TipoPapel papel;
	
	public String preparaCadastro(){
		
		return Navigation.CADASTRO;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public TipoPapel getPapel() {
		return papel;
	}
	public void setPapel(TipoPapel papel) {
		this.papel = papel;
	}

}
