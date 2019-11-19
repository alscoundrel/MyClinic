package pt.ipv.qualifica.myclinic.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import net.bootsfaces.utils.FacesMessages;
import pt.ipv.qualifica.myclinic.model.Especialidade;
  
public class EspecialidadeDAO {
  
    private EntityManagerFactory factory;
    private EntityManager em;
    private boolean dbDisabled = true;
    private String queryList = "SELECT e FROM Especialidade e";
    
    public EspecialidadeDAO() {
    	try {
    		factory = Persistence.createEntityManagerFactory(DbAgreement.DataBase.DB_NAME);
    		em = factory.createEntityManager();
    		dbDisabled = false;
    	}
    	catch (Exception e) {
    		FacesMessages.error(DbAgreement.DataBase.NO_CONNECT_DB);
    		dbDisabled = true;
		}
    }
    
    public List<Especialidade> getLista(){
    	if(dbDisabled) { return null;}
		  try {
			  List<Especialidade> lista = em.createQuery(queryList, Especialidade.class).getResultList();
			  return lista;
		  }
		  catch (NoResultException e) { return null; }
    }
   
    public Especialidade getEspecialidade(int id_especialidade) {
        try {
          return em
  	         .createQuery("SELECT e FROM Especialidade e where e.id = :id", Especialidade.class)
  	         .setParameter("id", id_especialidade)
  	         .getSingleResult();
   
        } catch (NoResultException e) {
              return null;
        }
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
