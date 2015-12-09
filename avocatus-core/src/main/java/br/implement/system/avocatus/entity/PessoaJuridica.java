/**
 * 
 */
package br.implement.system.avocatus.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author adrianopatrick@gmail.com
 * @since 7 de dez de 2015
 */
@Entity
@DiscriminatorValue("1")
@Table(name="pessoas_juridicas")
public class PessoaJuridica extends Pessoa {
	
	@Column(name="cnpj_instituicao", nullable = false, length = 18, unique = true)
	private String cnpj;
	
	@Column(name="razao_social", nullable = false)
	private String razaoSocial;
	
	@Column(nullable = false)
	private String telefone;


}
