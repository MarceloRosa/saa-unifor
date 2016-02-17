/**
 * 
 */
package br.unifor.saa.business;

import java.io.IOException;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.saa.aspectj.PermitAll;
import br.unifor.saa.aspectj.RolesAllowed;
import br.unifor.saa.dao.UsuarioDAO;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.exceptions.BOException;
import br.unifor.saa.util.Encripta;
import br.unifor.saa.util.MessagesResources;

/**
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class UsuarioBO {

	@Inject
	private UsuarioDAO usuarioDAO;

	@RolesAllowed("INSERIR_USUARIO")
	public void salvar(Usuario usuario) throws BOException, IOException {

		Usuario user = this.usuarioDAO.buscarPorEmail(usuario.getEmail());
		if (user != null) {
			throw new BOException(MessagesResources.getMessages("erro_duplicate_email"));
		}

		this.usuarioDAO.salvar(usuario);
	}

	@RolesAllowed("ATUALIZAR_USUARIO")
	public void atualizar(Usuario usuario) {
		this.usuarioDAO.atualizar(usuario);
	}

	@PermitAll	
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
