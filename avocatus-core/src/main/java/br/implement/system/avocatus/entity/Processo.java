package br.implement.system.avocatus.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author adrianopatrick@gmail.com
 * @since 15 de dez de 2015
 */
@Entity
@Table(name="processos")
public class Processo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="numero_processo")
	private String numeroProcesso;
	
	@ManyToOne
	@JoinColumn(name="advogado_id", nullable=false)
	private Usuario advogado;
	
	@ManyToOne
	@JoinColumn(name="ramo_id")
	private Ramo ramo;
	
	@ManyToOne
	@JoinColumn(name="status_processo_id", nullable=false)
	private StatusProcesso statusProcesso;
	
	@ManyToOne
	@JoinColumn(name="tipo_acao_id")
	private TipoAcao tipoAcao;
	
	@ManyToOne
	@JoinColumn(name="atendimento_id")
	private Atendimento atendimento;
	
	@ManyToOne
	@JoinColumn(name="local_id")
	private Local local;
	
	@ManyToOne
	@JoinColumn(name="captador_id")
	private Usuario captador;
	
	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date created;
	
	@Column
	private BigDecimal valor;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroProcesso() {
		return numeroProcesso;
	}

	public void setNumeroProcesso(String numeroProcesso) {
		this.numeroProcesso = numeroProcesso;
	}

	public Usuario getAdvogado() {
		return advogado;
	}

	public void setAdvogado(Usuario advogado) {
		this.advogado = advogado;
	}

	public Ramo getRamo() {
		return ramo;
	}

	public void setRamo(Ramo ramo) {
		this.ramo = ramo;
	}

	public StatusProcesso getStatusProcesso() {
		return statusProcesso;
	}

	public void setStatusProcesso(StatusProcesso statusProcesso) {
		this.statusProcesso = statusProcesso;
	}

	public TipoAcao getTipoAcao() {
		return tipoAcao;
	}

	public void setTipoAcao(TipoAcao tipoAcao) {
		this.tipoAcao = tipoAcao;
	}

	public Atendimento getAtendimento() {
		return atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	public Local getLocal() {
		return local;
	}

	public void setLocal(Local local) {
		this.local = local;
	}

	public Usuario getCaptador() {
		return captador;
	}

	public void setCaptador(Usuario captador) {
		this.captador = captador;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Processo other = (Processo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
