/**
 * 
 */
package br.implement.system.avocatus.dao;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.implement.system.avocatus.entity.Usuario;

/**
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	public void salvar(Usuario usuario) {
		em.persist(usuario);
	}

	public void atualizar(Usuario usuario) {
		em.merge(usuario);
	}

	public Usuario buscarPorEmailSenha(String email, String senha) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder
				.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteriaQuery.from(Usuario.class);
		Predicate restriction = criteriaBuilder.and(
				criteriaBuilder.equal(Usuario.<String> get("email"), email),
				criteriaBuilder.equal(Usuario.<String> get("senha"), senha));
		criteriaQuery.where(criteriaBuilder.and(restriction));

		Query query = em.createQuery(criteriaQuery);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Usuario buscarPorEmail(String email) {
		CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> criteriaQuery = criteriaBuilder
				.createQuery(Usuario.class);
		Root<Usuario> Usuario = criteriaQuery.from(Usuario.class);
		criteriaQuery.where(criteriaBuilder.equal(
				Usuario.<String> get("email"), email));

		Query query = em.createQuery(criteriaQuery);
		try {
			return (Usuario) query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

}
