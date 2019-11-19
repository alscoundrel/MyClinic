package pt.ipv.qualifica.myclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import pt.ipv.qualifica.myclinic.db.DbAgreement;

@Entity
@Table(name = DbAgreement.ConsultasTable.TABLE_NAME)
public class Consulta {
       
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name=DbAgreement.ConsultasTable.COLUMN_ID_NAME, nullable=false, unique=true)
      private int id;
      
      @Column(name=DbAgreement.ConsultasTable.COLUMN_DATA_NAME, nullable=false, unique=true)
      private Date marcacao;
      
      @Column(name=DbAgreement.ConsultasTable.COLUMN_PRESCRICAO, nullable=false, unique=false)
      private String prescricao;
      
      @Column(name=DbAgreement.ConsultasTable.COLUMN_CANCELADA, nullable=false, unique=false)
      private boolean cancelada = false;

      @ManyToOne
      @JoinColumn(name =DbAgreement.ConsultasTable.COLUMN_PACIENTE_NAME)
      private Paciente paciente;

      @ManyToOne
      @JoinColumn(name =DbAgreement.ConsultasTable.COLUMN_MEDICO_NAME)
      private Medico medico;


	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the marcacao
	 */
	public Date getMarcacao() {
		return marcacao;
	}

	/**
	 * @param marcacao the marcacao to set
	 */
	public void setMarcacao(Date marcacao) {
		this.marcacao = marcacao;
	}

	/**
	 * @return the prescricao
	 */
	public String getPrescricao() {
		return prescricao;
	}

	/**
	 * @param prescricao the prescricao to set
	 */
	public void setPrescricao(String prescricao) {
		this.prescricao = prescricao;
	}

	/**
	 * @return the cancelada
	 */
	public boolean isCancelada() {
		return cancelada;
	}

	/**
	 * @param cancelada the cancelada to set
	 */
	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the medico
	 */
	public Medico getMedico() {
		return medico;
	}

	/**
	 * @param medico the medico to set
	 */
	public void setMedico(Medico medico) {
		this.medico = medico;
	}


 }