package pt.ipv.qualifica.myclinic.beans;


import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pt.ipv.qualifica.myclinic.db.MedicoDAO;
import pt.ipv.qualifica.myclinic.db.PacienteDAO;
import pt.ipv.qualifica.myclinic.db.ConsultaDAO;
import pt.ipv.qualifica.myclinic.model.Consulta;
import pt.ipv.qualifica.myclinic.util.PageAgreement;
import pt.ipv.qualifica.myclinic.util.Uteis;



@Named("consultaMB")
@ViewScoped
public class ConsultasManagedBean implements Serializable {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ConsultaDAO consultaDAO = new ConsultaDAO();
	private PacienteDAO pacienteDAO = new PacienteDAO();
	private MedicoDAO medicoDAO = new MedicoDAO();
	private Consulta consulta = new Consulta();
	private Date marcacao;
	private int id_pacienteSelected = 0;
	private int id_medicoSelected = 0;
	
	/**
	 * Construtor
	 * Pega o identificador passado em URL e guarda a Consulta correspondente
	 */
	public ConsultasManagedBean() {
		String consultaIdParam = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("id");
		if(consultaIdParam != null) {
			consulta = consultaDAO.getConsulta(Integer.parseInt(consultaIdParam));
			marcacao = consulta.getMarcacao();
			id_pacienteSelected = consulta.getPaciente().getId();
			id_medicoSelected = consulta.getMedico().getId();
		}
	}
	
	/**
	 * Regista Consulta
	 * @return null se nao regista, caminho da lista de regista
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 */
	public String registar() throws ParseException {
		consulta.setMarcacao(marcacao);
		consulta.setPrescricao("");
		if(consultaDAO.regista(consulta)) {
	    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    	try {
	    	    ec.redirect("../?faces-redirect=true");
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
			return "../?faces-redirect=true";
		}
		return null;
	}
	
	/**
	 * Redirecciona para pagina de edicao e captura o identificador
	 * @return caminho para a pagina de edicao
	 */
	public String editar() {
    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

		String consultaIdParam = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("id");
    	try {
    	    ec.redirect(PageAgreement.Caminhos.ROOT_CONSULTAS + "/form/editar.xhtml?id=" + consultaIdParam + "&faces-redirect=true");
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
		return PageAgreement.Caminhos.ROOT_CONSULTAS + "/form/editar.xhtml?faces-redirect=true";
	}

	/**
	 * Actualiza a consulta
	 * @return null se nao actualizar, caminho lista se elimina
	 * @throws NoSuchAlgorithmException
	 */
	public String actualizar() {
		consulta.setMarcacao(marcacao);
		consulta.setPaciente(pacienteDAO.getPaciente(id_pacienteSelected));
		consulta.setMedico(medicoDAO.getMedico(id_medicoSelected));
		if(consultaDAO.regista(consulta)) {
	    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    	try {
	    	    ec.redirect(PageAgreement.Caminhos.ROOT_CONSULTAS + "?faces-redirect=true");
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
			return PageAgreement.Caminhos.ROOT_CONSULTAS + "?faces-redirect=true";
		}
		return null;
	}
    
	/**
	 * Elimina Consulta
	 * @param utilizador
	 * @return null se nao eliminar, caminho da lista se elimina
	 */
    public String eliminar(Consulta consulta) {
    	
    	if(consultaDAO.elimina(consulta)) {
	    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    	try {
	    		Uteis.MensagemInfo("Eliminar dados","Dados eliminados com sucesso."); 
	    	    ec.redirect("./");
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
			return "./";
    	}
    	return null;
    }
    
    /**
     * Formata data 
     * @param data
     * @return string na forma definida na sdf
     */
    public String dataFormatada(Date data) {
		if(data != null){
			return (new SimpleDateFormat("yyyy-MM-dd HH:mm")).format(data);
		}
		return "";  
    }
    

	/**
	 * @return the consultaDAO
	 */
	public ConsultaDAO getConsultaDAO() {
		return consultaDAO;
	}

	/**
	 * @param consultaDAO the consultaDAO to set
	 */
	public void setConsultaDAO(ConsultaDAO consultaDAO) {
		this.consultaDAO = consultaDAO;
	}

	/**
	 * @return the consulta
	 */
	public Consulta getConsulta() {
		return consulta;
	}

	/**
	 * @param consulta the consulta to set
	 */
	public void setConsulta(Consulta consulta) {
		this.consulta = consulta;
	}

	/**
	 * @return the pacienteDAO
	 */
	public PacienteDAO getPacienteDAO() {
		return pacienteDAO;
	}

	/**
	 * @param pacienteDAO the pacienteDAO to set
	 */
	public void setPacienteDAO(PacienteDAO pacienteDAO) {
		this.pacienteDAO = pacienteDAO;
	}

	/**
	 * @return the medicoDAO
	 */
	public MedicoDAO getMedicoDAO() {
		return medicoDAO;
	}

	/**
	 * @param medicoDAO the medicoDAO to set
	 */
	public void setMedicoDAO(MedicoDAO medicoDAO) {
		this.medicoDAO = medicoDAO;
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
	 * @return the id_pacienteSelected
	 */
	public int getId_pacienteSelected() {
		return id_pacienteSelected;
	}

	/**
	 * @param id_pacienteSelected the id_pacienteSelected to set
	 */
	public void setId_pacienteSelected(int id_pacienteSelected) {
		this.id_pacienteSelected = id_pacienteSelected;
	}

	/**
	 * @return the id_medicoSelected
	 */
	public int getId_medicoSelected() {
		return id_medicoSelected;
	}

	/**
	 * @param id_medicoSelected the id_medicoSelected to set
	 */
	public void setId_medicoSelected(int id_medicoSelected) {
		this.id_medicoSelected = id_medicoSelected;
	}


	
}