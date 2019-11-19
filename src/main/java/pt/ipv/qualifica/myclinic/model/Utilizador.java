package pt.ipv.qualifica.myclinic.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pt.ipv.qualifica.myclinic.db.DbAgreement;

@Entity
@Table(name = DbAgreement.UtilizadoresTable.TABLE_NAME)
public class Utilizador {
       
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name=DbAgreement.UtilizadoresTable.COLUMN_ID_NAME, nullable=false, unique=true)
      private int id;
       
      @Column(name=DbAgreement.UtilizadoresTable.COLUMN_UTILIZADOR_NAME, nullable=false, unique=true)
      private String nomeUtilizador;
       
      @Column(name=DbAgreement.UtilizadoresTable.COLUMN_SENHA_NAME, nullable=false, unique=false)
      private String senha;
      
      @Column(name=DbAgreement.UtilizadoresTable.COLUMN_NIVEL_NAME, nullable=false, unique=false)
      private char nivel;
  
      @Column(name=DbAgreement.UtilizadoresTable.COLUMN_CONTROL_NAME, unique=false)
      private Date ultimoAcesso;

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
	 * @return the nomeUtilizador
	 */
	public String getNomeUtilizador() {
		return nomeUtilizador;
	}

	/**
	 * @param nomeUtilizador the nomeUtilizador to set
	 */
	public void setNomeUtilizador(String nomeUtilizador) {
		this.nomeUtilizador = nomeUtilizador;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}

	/**
	 * @return the nivel
	 */
	public char getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(char nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the ultimoAcesso
	 */
	public Date getUltimoAcesso() {
		return ultimoAcesso;
	}

	/**
	 * @param ultimoAcesso the ultimoAcesso to set
	 */
	public void setUltimoAcesso(Date ultimoAcesso) {
		this.ultimoAcesso = ultimoAcesso;
	}

 }