package pt.ipv.qualifica.myclinic.beans;


import java.io.IOException;
import java.io.Serializable;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import pt.ipv.qualifica.myclinic.db.MedicoDAO;
import pt.ipv.qualifica.myclinic.db.PacienteDAO;
import pt.ipv.qualifica.myclinic.db.UtilizadorDAO;
import pt.ipv.qualifica.myclinic.model.Medico;
import pt.ipv.qualifica.myclinic.model.Paciente;
import pt.ipv.qualifica.myclinic.model.Utilizador;
import pt.ipv.qualifica.myclinic.util.PageAgreement;
import pt.ipv.qualifica.myclinic.util.Uteis;

@Named("sessaoMB")
@ViewScoped
public class SessaoManagedBean implements Serializable {
  
  /**
   * 
   * detem sementes (dados) da sessao
   */
	private static final long serialVersionUID = 1L;
	
	private Utilizador utilizadorSessao;
	private Utilizador utilizadorBD;
	private String nomeUtilizador;
	private char nivel = 'Z';
	private boolean ligacaoBD = false;
	
	/**
	 * Construtor
	 * pega o utilizador da sessao
	 */
	public SessaoManagedBean() {
		utilizadorSessao = (Utilizador) FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getSessionMap()
				.get("utilizadorAutenticado");
		montaPropriedadesUtilizador();
	}
	
	/**
	 * Guarda os dados referentes ao utilizador da sessao
	 */
	private void montaPropriedadesUtilizador() {
		
		if(utilizadorSessao != null){
			UtilizadorDAO utilizadorDAO = new UtilizadorDAO();
			this.ligacaoBD = !utilizadorDAO.isDbDisabled();
			
			utilizadorBD = utilizadorDAO.getUtilizador(utilizadorSessao.getId());
			this.nomeUtilizador = utilizadorSessao.getNomeUtilizador();
			this.nivel = utilizadorSessao.getNivel();
		
			if(utilizadorBD != null) {
				this.nomeUtilizador = utilizadorBD.getNomeUtilizador();
				Paciente paciente = (new PacienteDAO()).getPaciente(this.nomeUtilizador);
				if(paciente != null) {
					String[] nomes = paciente.getNome().split(" ");
					this.nomeUtilizador = nomes[0] + (1 < nomes.length ? " " + nomes[nomes.length-1] : "");
				}else {
					Medico medico = (new MedicoDAO()).getMedico(this.nomeUtilizador);
					if(medico != null) {
						String[] nomes = medico.getNome().split(" ");
						this.nomeUtilizador = nomes[0] + (1 < nomes.length ? " " + nomes[nomes.length-1] : "");
					}
				}
				
			}
		}
	}
	
	
	/**
	 * Verifica temos utilizador autenticado
	 * caso temos autenticacao e estamos na pagina de autenticacao, direcciona para a area de trabalho
	 * caso nao temos autenticacao e estamos na area de trabalho, direcciona para a pagina de autenticacao
	 * @param area
	 * @return se autenticado
	 */
	public boolean verificaSeAutenticado(String area) {
		boolean autenticado = utilizadorSessao != null;
		
		// caso nao temos autenticacao valida direcciona para a pagina ROOT
    	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();

    	try {
    		
    		if(autenticado && 0 == area.compareTo("login")) {
    			// se autenticado e no login entao direcciona para a pagina MAIN
    			ec.redirect(PageAgreement.Caminhos.ROOT + "/main");
    		}
    		if(!autenticado && 0 == area.compareTo("trabalho")) {
    			// se nao autenticado e dentro area de trablho direcciona para a pagina ROOT
    			ec.redirect(PageAgreement.Caminhos.ROOT);
    		}

    	} catch (IOException e) {
    	    e.printStackTrace();
    	}
		return autenticado;
	}

	/**
	 * @return the nomeUtilizador
	 */
	public String getNomeUtilizador() {
		return nomeUtilizador;
	}

	/**
	 * @param nomeUtilizador the nomeUtilizador to set
	 */
	public void setNomeUtilizador(String nomeUtilizador) {
		this.nomeUtilizador = nomeUtilizador;
	}

	/**
	 * @return the nivel
	 */
	public char getNivel() {
		return nivel;
	}

	/**
	 * @param nivel the nivel to set
	 */
	public void setNivel(char nivel) {
		this.nivel = nivel;
	}

	/**
	 * @return the utilizadorSessao
	 */
	public Utilizador getUtilizadorSessao() {
		return utilizadorSessao;
	}

	/**
	 * @return the utilizadorBD
	 */
	public Utilizador getUtilizadorBD() {
		return utilizadorBD;
	}

	/**
	 * @return the ligacaoBD
	 */
	public boolean isLigacaoBD() {
		return ligacaoBD;
	}
}