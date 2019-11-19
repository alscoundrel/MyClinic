package pt.ipv.qualifica.myclinic.model;


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
@Table(name = DbAgreement.MedicosTable.TABLE_NAME)
public class Medico {
      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      @Column(name=DbAgreement.MedicosTable.COLUMN_ID_NAME, nullable=false, unique=true)
      private int id;
       
      @Column(name=DbAgreement.MedicosTable.COLUMN_NOME_NAME, nullable=false, unique=true)
      private String nome;
       
      @Column(name=DbAgreement.MedicosTable.COLUMN_NIF_NAME, nullable=false, unique=false)
      private String nif;
      
      @Column(name=DbAgreement.PacientesTable.COLUMN_EMAIL_NAME, nullable=false, unique=false)
      private String email;
      
      @Column(name=DbAgreement.MedicosTable.COLUMN_CONTACTO_NAME, nullable=false, unique=false)
      private String contacto;
  
      @Column(name=DbAgreement.MedicosTable.COLUMN_MORADA_NAME, unique=false)
      private String morada;
      
      @ManyToOne
      @JoinColumn(name = DbAgreement.MedicosTable.COLUMN_ID_ESPECIALIDADE)
      private Especialidade especialidade;

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the nif
	 */
	public String getNif() {
		return nif;
	}

	/**
	 * @param nif the nif to set
	 */
	public void setNif(String nif) {
		this.nif = nif;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the contacto
	 */
	public String getContacto() {
		return contacto;
	}

	/**
	 * @param contacto the contacto to set
	 */
	public void setContacto(String contacto) {
		this.contacto = contacto;
	}

	/**
	 * @return the morada
	 */
	public String getMorada() {
		return morada;
	}

	/**
	 * @param morada the morada to set
	 */
	public void setMorada(String morada) {
		this.morada = morada;
	}

	/**
	 * @return the especialidade
	 */
	public Especialidade getEspecialidade() {
		return especialidade;
	}

	/**
	 * @param especialidade the especialidade to set
	 */
	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

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

	
    @Override
    public boolean equals(Object other) {
        return (other != null && getClass() == other.getClass() && id != 0)
            ? id == (((Medico) other).getId())
            : (other == this);
    }

    @Override
    public int hashCode() {
        return (id == 0) 
            ? (getClass().hashCode() + id)
            : super.hashCode();
    }

 }