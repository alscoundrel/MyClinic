/**
 * 
 */
package pt.ipv.qualifica.myclinic.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

/**
 * @author Programador
 * cria um méodo para recuperar o EntityManger que vai ser criado no nosso MyClinicFilter
 */
public class Uteis {

	
	public static EntityManager JpaEntityManager(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest request  = (HttpServletRequest)externalContext.getRequest();
		return (EntityManager)request.getAttribute("entityManager");
	}
	
	/**
	 * Mostra Mensagem Alerta
	 * @param mensagem
	 */
	public static void Mensagem(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage("Alerta",mensagem));
	}
 
	/**
	 * Mostra Mensagem Atencao
	 * @param mensagem
	 */
	public static void MensagemAtencao(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", mensagem));
	}
	
	/**
	 * Mostra Mensagem Erro
	 * @param mensagem
	 */
	public static void MensagemErro(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro:", mensagem));
	}
 
	/**
	 * Mostra Mensagem Informacao
	 * @param mensagem
	 */
	public static void MensagemInfo(String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", mensagem));
	}
	
	/**
	 * Mostra Mensagem Informacao
	 * @param titulo
	 * @param mensagem
	 */
	public static void MensagemInfo(String titulo, String mensagem){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, titulo, mensagem));
	}
}
