package pt.ipv.qualifica.myclinic.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import net.bootsfaces.utils.FacesMessages;
import pt.ipv.qualifica.myclinic.model.Paciente;
  
public class PacienteDAO {
  
    private EntityManager em;
    private boolean dbDisabled = true;
    
    public PacienteDAO() {
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
    
    public List<Paciente> getLista(){
    	if(dbDisabled) { return null;}
		try {
			String queryList = "SELECT p FROM Paciente p";
			List<Paciente> lista = em.createQuery(queryList, Paciente.class).getResultList();
			return lista;
		}
		catch (NoResultException e) { return null; }
    }
    
    public Paciente getPaciente(int id_paciente) {
      try {
        return em
	         .createQuery("SELECT p FROM Paciente p where p.id = :id", Paciente.class)
	         .setParameter("id", id_paciente)
	         .getSingleResult();
 
      } catch (NoResultException e) {
            return null;
      }
    }
    
    public Paciente getPaciente(String term) {
        try {
          List<Paciente> lista = em
  	         .createQuery("SELECT p FROM Paciente p where (p.nif = :term OR p.email = :term)", Paciente.class)
  	         .setParameter("term", term)
  	         .getResultList();
          if(0 < lista.size()) { return lista.get(0);}
        } catch (NoResultException e) {
        		e.printStackTrace();
        }
        return null;
     }
 
  public boolean regista(Paciente paciente) {
          try {
        	  	em.getTransaction().begin();
                em.persist(paciente);
                em.getTransaction().commit();
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
     
    public boolean elimina(Paciente paciente) {
          try {
        	  	em.getTransaction().begin();
                em.remove(paciente);
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
