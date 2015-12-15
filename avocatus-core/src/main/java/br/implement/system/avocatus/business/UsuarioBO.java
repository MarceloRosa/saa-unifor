/**
 * 
 */
package br.implement.system.avocatus.business;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import br.implement.system.avocatus.dao.UsuarioDAO;
import br.implement.system.avocatus.entity.Usuario;
import br.implement.system.avocatus.exceptions.BOException;
import br.implement.system.avocatus.util.Encripta;
import br.implement.system.avocatus.util.MessagesResources;

/**
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
@Service
public class UsuarioBO {

	@Inject
	private UsuarioDAO usuarioDAO;

	public void salvar(Usuario usuario) throws BOException, IOException {

		Usuario user = this.usuarioDAO.buscarPorEmail(usuario.getEmail());
		if (user != null) {
			throw new BOException(MessagesResources.getMessages("erro_duplicate_email"));
		}

		this.usuarioDAO.salvar(usuario);
	}

	public void atualizar(Usuario usuario) {
		this.usuarioDAO.atualizar(usuario);
	}

	public Usuario loggar(String email, String senha) throws BOException {
		
		if (email == null || email.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
			throw new BOException(MessagesResources.getMessages("msg.required"));
		}
		
		Usuario usuario = this.usuarioDAO.buscarPorEmailSenha(email, Encripta.encripta(senha)); 
		
		if(usuario == null){
			throw new BOException(MessagesResources.getMessages("erro_login_invalido"));
		}
		
		return usuario;
	}
}
