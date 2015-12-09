/**
 * 
 */
package br.implement.system.avocatus.security;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * @author adrianopatrick@gmail.com
 * @since 8 de dez de 2015
 */
@XStreamAlias("acesso")
public class Acesso {

	@XStreamAlias("url")
	private String url;
	@XStreamAlias("papeis")
	private List<Papel> papeis;
}

@XStreamAlias("papel")
class Papel {
	
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
}