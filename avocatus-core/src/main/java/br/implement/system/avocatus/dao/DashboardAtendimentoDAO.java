package br.implement.system.avocatus.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.implement.system.avocatus.entity.Usuario;
import br.implement.system.avocatus.to.AtendimentoPorDiaTO;

@Repository
@Transactional(propagation = Propagation.REQUIRED)
public class DashboardAtendimentoDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<AtendimentoPorDiaTO> quantidadeDeAtendimentosPorDia(Usuario atendente) {
		String sql = "select count(0) qtdaAtendimentoPorDia, data_hora_atendimento::date data from atendimentos "
				+ "where atendente_id = 2 group by data_hora_atendimento::date order by data_hora_atendimento::date";
		Query query = em.createNativeQuery(sql, AtendimentoPorDiaTO.class);
		return query.getResultList();
	}

}
