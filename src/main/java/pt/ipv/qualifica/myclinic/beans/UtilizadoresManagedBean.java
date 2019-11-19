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
import pt.ipv.qualifica.myclinic.db.UtilizadorDAO;
import pt.ipv.qualifica.myclinic.model.Utilizador;
import pt.ipv.qualifica.myclinic.util.PageAgreement;
import pt.ipv.qualifica.myclinic.util.Uteis;



@Named("utilizadorMB")
@ViewScoped
public class UtilizadoresManagedBean implements Serializable {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UtilizadorDAO utilizadorDAO = new UtilizadorDAO();
	private Utilizador utilizador = new Utilizador();
	private String palavraPasse ="";
	private String confirmaPalavraPasse = "";
	
	/**
	 * Construtor
	 * Pega o identificador passado em URL e guarda a Utilizador correspondente
	 */
	public UtilizadoresManagedBean() {
		String utilizadorIdParam = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("id");
		if(utilizadorIdParam != null) {
			utilizador = utilizadorDAO.getUtilizador(Integer.parseInt(utilizadorIdParam));
		}
	}
	
	/**
	 * Regista Utilizador
	 * @return null se nao regista, caminho da lista de regista
	 * @throws ParseException
	 * @throws NoSuchAlgorithmException
	 */
	public String registar() throws ParseException, NoSuchAlgorithmException {
		if(palavraPasse.compareTo(confirmaPalavraPasse) != 0) {
			Uteis.MensagemErro("As palavras-passe não coincidem!");
			return null;
		}

		String senhaHash = LoginManagedBean.getSHA(palavraPasse);
		utilizador.setSenha(senhaHash);
		if(utilizadorDAO.regista(utilizador)) {
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

		String utilizadorIdParam = FacesContext.getCurrentInstance()
		        .getExternalContext().getRequestParameterMap().get("id");
    	try {
    	    ec.redirect(PageAgreement.Caminhos.ROOT_UTILIZADORES + "/form/editar.xhtml?id=" + utilizadorIdParam + "&faces-redirect=true");
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
		return PageAgreement.Caminhos.ROOT_UTILIZADORES + "/form/editar.xhtml?id=" + utilizadorIdParam + "&faces-redirect=true";
	}
	
	/**
	 * Actualiza o Utilizador
	 * @return null se nao actualizar, caminho lista se elimina
	 * @throws NoSuchAlgorithmException
	 */
	public String actualizar() throws NoSuchAlgorithmException {
		if(palavraPasse.compareTo(confirmaPalavraPasse) != 0) {
			Uteis.MensagemErro("As palavras-passe não coincidem!");
			return null;
		}
		String senhaHash = LoginManagedBean.getSHA(palavraPasse);
		utilizador.setSenha(senhaHash);
		
		if(utilizadorDAO.regista(utilizador)) {
	    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	    	try {
	    	    ec.redirect(PageAgreement.Caminhos.ROOT_UTILIZADORES + "?faces-redirect=true");
	    	} catch (IOException e) {
	    	    e.printStackTrace();
	    	}
			return PageAgreement.Caminhos.ROOT_UTILIZADORES + "?faces-redirect=true";
		}
		return null;
	}
    
	/**
	 * Elimina Utilizador
	 * @param utilizador
	 * @return null se nao eliminar, caminho da lista se elimina
	 */
    public String eliminar(Utilizador utilizador) {
    	
    	if(utilizadorDAO.elimina(utilizador)) {
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
	 * @return the utilizadorDAO
	 */
	public UtilizadorDAO getUtilizadorDAO() {
		return utilizadorDAO;
	}

	/**
	 * @param utilizadorDAO the utilizadorDAO to set
	 */
	public void setUtilizadorDAO(UtilizadorDAO utilizadorDAO) {
		this.utilizadorDAO = utilizadorDAO;
	}

	/**
	 * @return the utilizador
	 */
	public Utilizador getUtilizador() {
		return utilizador;
	}

	/**
	 * @param utilizador the utilizador to set
	 */
	public void setUtilizador(Utilizador utilizador) {
		this.utilizador = utilizador;
	}

	/**
	 * @return the palavraPasse
	 */
	public String getPalavraPasse() {
		return palavraPasse;
	}

	/**
	 * @param palavraPasse the palavraPasse to set
	 */
	public void setPalavraPasse(String palavraPasse) {
		this.palavraPasse = palavraPasse;
	}

	/**
	 * @return the confirmaPalavraPasse
	 */
	public String getConfirmaPalavraPasse() {
		return confirmaPalavraPasse;
	}

	/**
	 * @param confirmaPalavraPasse the confirmaPalavraPasse to set
	 */
	public void setConfirmaPalavraPasse(String confirmaPalavraPasse) {
		this.confirmaPalavraPasse = confirmaPalavraPasse;
	}

	
}