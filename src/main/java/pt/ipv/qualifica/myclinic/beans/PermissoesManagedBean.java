package pt.ipv.qualifica.myclinic.beans;


import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("permissoesMB")
@ViewScoped
public class PermissoesManagedBean implements Serializable {
  
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** 
	 * permissoes na pagina [C, R, U, D]
	 * 
	 *  a area de consultas tem mais dois [C, R, U, D, I, P]
	 *  - I Invalidar (cancelar) usado nas consultas
	 *  - P Prescricao (pode passar/editar a prescricao)
	 * niveis
	 * A - Administrador
	 * S - Secretaria (Administrativo)
	 * M - Medico
	 * P - Paciente
	 */
	private boolean[] areaPacientes = {false, false, false, false};
	private boolean[] areaMedicos = {false, false, false, false};
	private boolean[] areaConsultas = {false, false, false, false, false, false};
	private boolean[] areaUtilizadores = {false, false, false, false};
	
	/**
	 * Construtor
	 * guarda as permissoes do utilizador definidas para o seu nivel
	 */
	public PermissoesManagedBean() {
		SessaoManagedBean sessaoManagedBean =  new SessaoManagedBean();

		switch (sessaoManagedBean.getNivel()) {
		case 'A':
			areaPacientes = new boolean[]{true, true, true, true};
			areaMedicos = new boolean[]{true, true, true, true};
			areaConsultas = new boolean[]{true, true, true, true, true, false};
			areaUtilizadores = new boolean[]{true, true, true, true};
			break;
		case 'M':
			areaPacientes = new boolean[]{false, true, false, false};
			areaMedicos = new boolean[]{false, false, false, false};
			areaConsultas = new boolean[]{true, true, true, true, false, true};
			areaUtilizadores = new boolean[]{false, false, false, false};
			break;
		case 'P':
			areaPacientes = new boolean[]{false, false, false, false};
			areaMedicos = new boolean[]{false, false, false, false};
			areaConsultas = new boolean[]{false, true, false, false, false, false};
			areaUtilizadores = new boolean[]{false, false, false, false};
			break;
		case 'S':
			areaPacientes = new boolean[]{true, true, true, false};
			areaMedicos = new boolean[]{true, true, true, false};
			areaConsultas = new boolean[]{true, true, true, false, true, false};
			areaUtilizadores = new boolean[]{false, false, false, false};
			break;
		default:
			break;
		}
	}

	/**
	 * @return the areaPacientes
	 */
	public boolean[] getAreaPacientes() {
		return areaPacientes;
	}

	/**
	 * @param areaPacientes the areaPacientes to set
	 */
	public void setAreaPacientes(boolean[] areaPacientes) {
		this.areaPacientes = areaPacientes;
	}

	/**
	 * @return the areaMedicos
	 */
	public boolean[] getAreaMedicos() {
		return areaMedicos;
	}

	/**
	 * @param areaMedicos the areaMedicos to set
	 */
	public void setAreaMedicos(boolean[] areaMedicos) {
		this.areaMedicos = areaMedicos;
	}

	/**
	 * @return the areaConsultas
	 */
	public boolean[] getAreaConsultas() {
		return areaConsultas;
	}

	/**
	 * @param areaConsultas the areaConsultas to set
	 */
	public void setAreaConsultas(boolean[] areaConsultas) {
		this.areaConsultas = areaConsultas;
	}

	/**
	 * @return the areaUtilizadores
	 */
	public boolean[] getAreaUtilizadores() {
		return areaUtilizadores;
	}

	/**
	 * @param areaUtilizadores the areaUtilizadores to set
	 */
	public void setAreaUtilizadores(boolean[] areaUtilizadores) {
		this.areaUtilizadores = areaUtilizadores;
	}
}