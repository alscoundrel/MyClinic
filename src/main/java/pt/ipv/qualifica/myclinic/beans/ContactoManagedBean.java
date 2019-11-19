package pt.ipv.qualifica.myclinic.beans;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pt.ipv.qualifica.myclinic.util.Uteis;

import java.io.Serializable;

@Named("contactoMB")
@ViewScoped
public class ContactoManagedBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nome = "";
	private String emailDe = "";
	private String contacto = "";
	private String assunto = "";
	private String corpo = "";
	private boolean aceitouTermos = false;
	

	/**
	 * envia a mensagem de e-mail
	 * ... em construcao ...
	 * @return
	 */
	public String envia() {
		Uteis.Mensagem("Teste de envio de mensagem... De '" + nome + "' com o assunto '" + assunto + "'");
		return null;
	}
	
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
	 * @return the emailDe
	 */
	public String getEmailDe() {
		return emailDe;
	}
	/**
	 * @param emailDe the emailDe to set
	 */
	public void setEmailDe(String emailDe) {
		this.emailDe = emailDe;
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
	 * @return the assunto
	 */
	public String getAssunto() {
		return assunto;
	}
	/**
	 * @param assunto the assunto to set
	 */
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	/**
	 * @return the corpo
	 */
	public String getCorpo() {
		return corpo;
	}
	/**
	 * @param corpo the corpo to set
	 */
	public void setCorpo(String corpo) {
		this.corpo = corpo;
	}

	/**
	 * @return the aceitouTermos
	 */
	public boolean isAceitouTermos() {
		return aceitouTermos;
	}

	/**
	 * @param aceitouTermos the aceitouTermos to set
	 */
	public void setAceitouTermos(boolean aceitouTermos) {
		this.aceitouTermos = aceitouTermos;
	}

}
