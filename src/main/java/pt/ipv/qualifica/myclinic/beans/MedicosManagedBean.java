package pt.ipv.qualifica.myclinic.beans;


import java.io.IOException;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pt.ipv.qualifica.myclinic.db.EspecialidadeDAO;
import pt.ipv.qualifica.myclinic.db.MedicoDAO;
import pt.ipv.qualifica.myclinic.model.Medico;
import pt.ipv.qualifica.myclinic.util.PageAgreement;
import pt.ipv.qualifica.myclinic.util.Uteis;



@Named("medicoMB")
@ViewScoped
public class MedicosManagedBean implements Serializable {
  
  /**
	 * Responde a pedidos e gere o estado dos componentes em JSF, do objecto Medico   
	 */
	private static final long serialVersionUID = 1L;
	
	private MedicoDAO medicoDAO = new MedicoDAO();
	private EspecialidadeDAO especialidadeDAO = new EspecialidadeDAO();
	private Medico medico = new Medico();
	private int id_especialidadeSelected = 0;
	
	/**
	 * Construtor
	 * Pega o identificador passado em URL e guarda a Medico correspondente
	 */
	public MedicosManagedBean() {
		String medicoIdParam = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("id");
		if(medicoIdParam != null) {
			medico = medicoDAO.getMedico(Integer.parseInt(medicoIdParam));
			id_especialidadeSelected = medico.getEspecialidade().getId();
		}
	}

	/**
	 * Regista Medico
	 * @return null se nao regista, caminho da lista de regista
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 */
	public String registar() {
		if(medicoDAO.regista(medico)) {
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
    	// pega o parametro 'id' passado no url
		String medicoIdParam = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("id");
    	try {
    	    ec.redirect(PageAgreement.Caminhos.ROOT_MEDICOS + "/form/editar.xhtml?id=" + medicoIdParam + "&faces-redirect=true");
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
		return PageAgreement.Caminhos.ROOT_MEDICOS + "/form/editar.xhtml?faces-redirect=true";
	}
	
	/**
	 * Actualiza o Medico
	 * @return null se nao actualizar, caminho lista se elimina
	 * @throws NoSuchAlgorithmException
	 */
	public String actualizar() {
		medico.setEspecialidade(especialidadeDAO.getEspecialidade(id_especialidadeSelected));
		if(medicoDAO.regista(medico)) {
	    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    	try {
	    	    ec.redirect(PageAgreement.Caminhos.ROOT_MEDICOS + "?faces-redirect=true");
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
			return PageAgreement.Caminhos.ROOT_MEDICOS + "?faces-redirect=true";
		}
		return null;
	}
    
	/**
	 * Elimina Medico
	 * @param utilizador
	 * @return null se nao eliminar, caminho da lista se elimina
	 */
    public String eliminar(Medico medico) {
    	
    	if(medicoDAO.elimina(medico)) {
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

	/**
	 * @return the especialidadeDAO
	 */
	public EspecialidadeDAO getEspecialidadeDAO() {
		return especialidadeDAO;
	}

	/**
	 * @param especialidadeDAO the especialidadeDAO to set
	 */
	public void setEspecialidadeDAO(EspecialidadeDAO especialidadeDAO) {
		this.especialidadeDAO = especialidadeDAO;
	}

	/**
	 * @return the id_especialidadeSelected
	 */
	public int getId_especialidadeSelected() {
		return id_especialidadeSelected;
	}

	/**
	 * @param id_especialidadeSelected the id_especialidadeSelected to set
	 */
	public void setId_especialidadeSelected(int id_especialidadeSelected) {
		this.id_especialidadeSelected = id_especialidadeSelected;
	}
	
}