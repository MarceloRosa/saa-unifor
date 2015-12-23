/**
 * 
 */
package br.implement.system.avocatus.manager;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.springframework.context.annotation.Scope;

import br.implement.system.avocatus.business.DashboardAtendimentoBO;
import br.implement.system.avocatus.to.AtendimentoPorDiaTO;
import br.implement.system.avocatus.to.SegurancaTO;

/**
 * @author adrianopatrick@gmail.com
 * @since 14 de dez de 2015
 */
@Named
@ManagedBean
@RequestScoped
@Scope("session")
public class DashboardAtendenteMB implements Serializable {

	private static final long serialVersionUID = 6470424082408187247L;
	
	private LineChartModel atendimentosPorDiaModel;
	
	@Inject
	private DashboardAtendimentoBO dashboardAtendimentoBO;
	
	@Inject
	private SegurancaTO segurancaTO;

	@PostConstruct
	public void init(){
		calculaAtendimentosPorDia();
	}

	private void calculaAtendimentosPorDia() {
		atendimentosPorDiaModel = initAtendimentoPorDiaCategory();
		atendimentosPorDiaModel.setTitle("Quantidade de Atendimentos Por dia");
		atendimentosPorDiaModel.setLegendPosition("e");
		atendimentosPorDiaModel.setShowPointLabels(true);
		atendimentosPorDiaModel.getAxes().put(AxisType.X, new CategoryAxis("Dias"));
		atendimentosPorDiaModel.getAxes().put(AxisType.Y, new CategoryAxis("Atendimentos"));
		
	}

	private LineChartModel initAtendimentoPorDiaCategory() {
		
		LineChartModel model = new LineChartModel();

		ChartSeries atendimentosSeries = new ChartSeries();
		atendimentosSeries.setLabel("Atendimentos");
		
		List<AtendimentoPorDiaTO> atendimentos = dashboardAtendimentoBO.calculaAtendimentosPorDia(segurancaTO.getUsuario());
		for (AtendimentoPorDiaTO atendimento : atendimentos) {
			atendimentosSeries.set(atendimento.getData(), atendimento.getQtdaAtendimentoPorDia());
		}
		
		model.addSeries(atendimentosSeries);
		return model;
	}
	
}
