/**
 * 
 */
package br.unifor.saa.business;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.unifor.saa.dao.DashboardAtendimentoDAO;
import br.unifor.saa.entity.Usuario;
import br.unifor.saa.to.AtendimentoPorDiaTO;

/**
 * @author adrianopatrick@gmail.com
 * @since 10 de dez de 2015
 */
@Service
@Transactional(propagation=Propagation.REQUIRES_NEW)
public class DashboardAtendimentoBO {

	@Inject
	private DashboardAtendimentoDAO dashboardAtendimentoDAO;

	public List<AtendimentoPorDiaTO> calculaAtendimentosPorDia(Usuario atendente){
		return dashboardAtendimentoDAO.quantidadeDeAtendimentosPorDia(atendente);
	}
}
