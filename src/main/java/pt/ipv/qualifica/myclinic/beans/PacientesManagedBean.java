package pt.ipv.qualifica.myclinic.beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;


import pt.ipv.qualifica.myclinic.db.PacienteDAO;
import pt.ipv.qualifica.myclinic.model.Paciente;
import pt.ipv.qualifica.myclinic.util.PageAgreement;
import pt.ipv.qualifica.myclinic.util.Uteis;

import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

@Named("pacienteMB")
@ViewScoped
public class PacientesManagedBean implements Serializable {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private PacienteDAO pacienteDAO = new PacienteDAO();
	private Paciente paciente = new Paciente();
	
	/**
	 * Construtor
	 * Pega o identificador passado em URL e guarda a Paciente correspondente
	 */
	public PacientesManagedBean() {
		String pacienteIdParam = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("id");
		if(pacienteIdParam != null) {
			paciente = pacienteDAO.getPaciente(Integer.parseInt(pacienteIdParam));
		}
	}
	
	/**
	 * Regista Paciente
	 * @return null se nao regista, caminho da lista de regista
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 */
	public String registar() {
		if(pacienteDAO.regista(paciente)) {
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

		String pacienteIdParam = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("id");
    	try {
    	    ec.redirect(PageAgreement.Caminhos.ROOT_PACIENTES + "/form/editar.xhtml?id=" + pacienteIdParam + "&faces-redirect=true");
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
		return PageAgreement.Caminhos.ROOT_PACIENTES + "/form/editar.xhtml?faces-redirect=true";
	}
	
	/**
	 * Actualiza o Paciente
	 * @return null se nao actualizar, caminho lista se elimina
	 * @throws NoSuchAlgorithmException
	 */
	public String actualizar() {
		if(pacienteDAO.regista(paciente)) {
	    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    	try {
	    	    ec.redirect(PageAgreement.Caminhos.ROOT_PACIENTES + "?faces-redirect=true");
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
			return PageAgreement.Caminhos.ROOT_PACIENTES + "?faces-redirect=true";
		}
		return null;
	}
    
	/**
	 * Elimina Paciente
	 * @param utilizador
	 * @return null se nao eliminar, caminho da lista se elimina
	 */
    public String eliminar(Paciente paciente) {
    	
    	if(pacienteDAO.elimina(paciente)) {
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


}