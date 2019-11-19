package pt.ipv.qualifica.myclinic.beans;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.commons.lang3.StringUtils;

import pt.ipv.qualifica.myclinic.db.UtilizadorDAO;
import pt.ipv.qualifica.myclinic.model.Utilizador;
import pt.ipv.qualifica.myclinic.util.PageAgreement;
import pt.ipv.qualifica.myclinic.util.Uteis;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Named("loginMB")
@ViewScoped
public class LoginManagedBean implements Serializable {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UtilizadorDAO utilizadorDAO = new UtilizadorDAO();
	private Utilizador utilizador = new Utilizador();
   
	/**
	 * Recebe as credenciais passadas pelo formulario
	 * verifica as mesmas e se temos correspondencia na base de dados
	 * Codifica a senha com o algoritmo SHA-256
	 * @return null invalidas, caso contrario o caminho da area de trabalho
	 * @throws NoSuchAlgorithmException
	 */
	public String envia() throws NoSuchAlgorithmException {
	  	
        if(StringUtils.isEmpty(utilizador.getNomeUtilizador()) || StringUtils.isBlank(utilizador.getNomeUtilizador())) {
        	Uteis.Mensagem("Favor informar o utilizador!");
        	return null;
        }
        if(StringUtils.isEmpty(utilizador.getSenha()) || StringUtils.isBlank(utilizador.getSenha())) {
        	Uteis.Mensagem("Favor informar a palavra-passe!");
        	return null;
        }
        
        String senhaHash = getSHA(utilizador.getSenha());
        
		utilizador = utilizadorDAO.getUtilizador(utilizador.getNomeUtilizador(), senhaHash);
	    if (utilizador == null) {
	    	utilizador = new Utilizador();
	    	Uteis.MensagemErro("Utilizador não encontrado! Erro no Login!");
	    	return null;
	    }
	    
	    // regista a data do acesso
	    Instant instant = Instant.from(LocalDate.now().atStartOfDay(ZoneId.of("GMT")));
	    utilizador.setUltimoAcesso(Date.from(instant));
	    utilizadorDAO.regista(utilizador);
	    
    	FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.getExternalContext().getSessionMap().put("utilizadorAutenticado", utilizador);
    	
    	//FacesContext.getCurrentInstance().getExternalContext().dispatch("teste.html");
    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    	try {
    	    ec.redirect(ec.getRequestContextPath() + "/main");
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    	return "/main";
	}
 
	/**
	 * Faz o fecho da sessao
	 * @return
	 */
	public String logout(){
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
    	try {
    	    ec.redirect(PageAgreement.Caminhos.ROOT);
    	    return "/";
    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
    	return null;
	}
	
	/**
	 * Gera uma string codificada em SHA-256 da string passada
	 * @param input
	 * @return string com o codigo
	 * @throws NoSuchAlgorithmException
	 */
    public static String getSHA(String input) throws NoSuchAlgorithmException 
    {  
        // Static getInstance method is called with hashing SHA  
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
  
        // digest() method called  
        // to calculate message digest of an input  
        // and return array of byte 
        byte[] hashCode = md.digest(input.getBytes(StandardCharsets.UTF_8));
        return toHexString(hashCode);
    }
 
    /**
     * passa o array de byte's para codigo hexadecimal, e depois para caracteres
     * @param hash
     * @return string de caracteres
     */
    public static String toHexString(byte[] hash) 
    { 
        // Convert byte array into signum representation  
        BigInteger number = new BigInteger(1, hash);  
  
        // Convert message digest into hex value  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
  
        // Pad with leading zeros 
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
  
        return hexString.toString();  
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
}