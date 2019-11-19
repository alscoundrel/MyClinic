package pt.ipv.qualifica.myclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pt.ipv.qualifica.myclinic.db.DbAgreement;

@Entity
@Table(name = DbAgreement.EspecialidadesTable.TABLE_NAME)
public class Especialidade {
       
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name=DbAgreement.EspecialidadesTable.COLUMN_ID_NAME, nullable=false, unique=true)
      private int id;
       
      @Column(name=DbAgreement.EspecialidadesTable.COLUMN_NOME_NAME, nullable=false, unique=true)
      private String descricao;

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
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
      

 }