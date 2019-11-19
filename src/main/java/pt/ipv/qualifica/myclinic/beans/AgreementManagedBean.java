package pt.ipv.qualifica.myclinic.beans;


import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pt.ipv.qualifica.myclinic.util.PageAgreement;

@Named("agreementMB")
@ViewScoped
public class AgreementManagedBean implements Serializable {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String marca = PageAgreement.Propriedades.MARCA;

	/**
	 * @return the marca
	 */
	public String getMarca() {
		return marca;
	}

	/**
	 * @param marca the marca to set
	 */
	public void setMarca(String marca) {
		this.marca = marca;
	}
}