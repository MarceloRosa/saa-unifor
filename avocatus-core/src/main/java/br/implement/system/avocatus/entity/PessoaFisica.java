/**
 * 
 */
package br.implement.system.avocatus.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author adrianopatrick@gmail.com
 * @since 7 de dez de 2015
 */
@Entity
@DiscriminatorValue("0")
@Table(name="pessoas_fisicas")
public class PessoaFisica extends Pessoa {

	@Column(nullable=false)
	private String nome;
	
	@Column(name = "cpf", length = 14, nullable=false, unique = true )
	private String cpf;

	@Temporal(TemporalType.DATE)	
	@Column(name = "data_nascimento", nullable=false)
	private Date dataNascimento;
		
	@Column(name = "telefone", nullable = false)
	private String telefone;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
}
