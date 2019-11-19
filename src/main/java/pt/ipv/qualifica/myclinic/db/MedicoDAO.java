package pt.ipv.qualifica.myclinic.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import net.bootsfaces.utils.FacesMessages;
import pt.ipv.qualifica.myclinic.model.Medico;
  
public class MedicoDAO {
  
    private EntityManager em;
    private boolean dbDisabled = true;
    
    public MedicoDAO() {
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
    
    public List<Medico> getLista(){
    	if(dbDisabled) { return null;}
		  try {
			  String queryList = "SELECT m FROM Medico m";
			  List<Medico> lista = em.createQuery(queryList, Medico.class).getResultList();
			  return lista;
		  }
		  catch (NoResultException e) { return null; }
    }
    
    public Medico getMedico(int id_medico) {
      try {
        return em
	         .createQuery("SELECT m FROM Medico m where m.id = :id", Medico.class)
	         .setParameter("id", id_medico)
	         .getSingleResult();
 
      } catch (NoResultException e) {
            return null;
      }
    }

    public Medico getMedico(String term) {
        try {
          List<Medico> lista = em
  	         .createQuery("SELECT m FROM Medico m where (m.nif = :term OR m.email = :term)", Medico.class)
  	         .setParameter("term", term)
  	         .getResultList();
          if(0 < lista.size()) { return lista.get(0);}
        } catch (NoResultException e) {
        		e.printStackTrace();
        }
        return null;
     }
    
  public boolean regista(Medico medico) {
          try {
        	  	em.getTransaction().begin();
                em.persist(medico);
                em.getTransaction().commit();
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
     
    public boolean elimina(Medico medico) {
          try {
        	  	em.getTransaction().begin();
                em.remove(medico);
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
