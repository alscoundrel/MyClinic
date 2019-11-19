package pt.ipv.qualifica.myclinic.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import net.bootsfaces.utils.FacesMessages;
import pt.ipv.qualifica.myclinic.beans.SessaoManagedBean;
import pt.ipv.qualifica.myclinic.model.Consulta;
import pt.ipv.qualifica.myclinic.model.Medico;
import pt.ipv.qualifica.myclinic.model.Paciente;
  
public class ConsultaDAO {
  
    private EntityManager em;
    private boolean dbDisabled = true;
    
    public ConsultaDAO() {
    	try {
    		EntityManagerFactory factory = Persistence.createEntityManagerFactory(DbAgreement.DataBase.DB_NAME);
    		em = factory.createEntityManager();
    		dbDisabled = false;
    	}
    	catch (Exception e) {
    		FacesMessages.error(DbAgreement.DataBase.NO_CONNECT_DB);
    		dbDisabled = true;
		}
    }
    
    public List<Consulta> getLista(){
		if(dbDisabled) { return null;}
		// se medico ou paciente
		SessaoManagedBean sessaoManagedBean = new SessaoManagedBean();
		try {
			if('M' == sessaoManagedBean.getNivel()) {
				String query = "SELECT c FROM Consulta c WHERE c.medico = :obj";
				MedicoDAO medicoDAO = new MedicoDAO();
				Medico medico = medicoDAO.getMedico(sessaoManagedBean.getNomeUtilizador());
				return em.createQuery(query, Consulta.class)
						.setParameter("obj", medico)
						.getResultList();
			}
			else if('P' == sessaoManagedBean.getNivel()){
				String query = "SELECT c FROM Consulta c WHERE c.paciente = :obj";
				PacienteDAO pacienteDAO = new PacienteDAO();
				Paciente paciente = pacienteDAO.getPaciente(sessaoManagedBean.getNomeUtilizador());
				return em.createQuery(query, Consulta.class)
						.setParameter("obj", paciente)
						.getResultList();
			}
			String queryList = "SELECT c FROM Consulta c";
			return em.createQuery(queryList, Consulta.class)
					.getResultList();
		 }
		 catch (NoResultException e) { return null; }
    }
    
    public Consulta getConsulta(int id_consulta) {
      try {
        return em
	         .createQuery("SELECT c FROM Consulta c where c.id = :id", Consulta.class)
	         .setParameter("id", id_consulta)
	         .getSingleResult();
 
      } catch (NoResultException e) {
            return null;
      }
    }
 
  public boolean regista(Consulta consulta) {
          try {
        	  	em.getTransaction().begin();
                em.persist(consulta);
                em.getTransaction().commit();
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
     
    public boolean elimina(Consulta consulta) {
          try {
        	  	em.getTransaction().begin();
                em.remove(consulta);
                em.getTransaction().commit();
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
    
    public boolean empty() {
        return getLista().size() == 0;
    }

	/**
	 * @return the dbDisabled
	 */
	public boolean isDbDisabled() {
		return dbDisabled;
	}

	/**
	 * @param dbDisabled the dbDisabled to set
	 */
	public void setDbDisabled(boolean dbDisabled) {
		this.dbDisabled = dbDisabled;
	}
}
