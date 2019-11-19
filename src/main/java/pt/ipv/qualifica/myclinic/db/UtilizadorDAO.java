package pt.ipv.qualifica.myclinic.db;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import net.bootsfaces.utils.FacesMessages;
import pt.ipv.qualifica.myclinic.beans.LoginManagedBean;
import pt.ipv.qualifica.myclinic.model.Utilizador;
  
public class UtilizadorDAO {
  
    private EntityManager em;
    private boolean dbDisabled = true;
    
    public UtilizadorDAO() {
    	try {
    		EntityManagerFactory factory = Persistence.createEntityManagerFactory(DbAgreement.DataBase.DB_NAME);
    		em = factory.createEntityManager();
    		verificaSeTemUtilizadores();
    		dbDisabled = false;
    	}
    	catch (Exception e) {
    		if( -1 < e.getCause().getCause().toString().indexOf("Unknown database") ) {
    			FacesMessages.error(DbAgreement.DataBase.NO_FOUND_DB);
    		}else {
    			FacesMessages.error(DbAgreement.DataBase.NO_CONNECT_DB);
    		}
    		dbDisabled = true;
		}
    }

    public List<Utilizador> getLista(){
    	if(dbDisabled) { return null;}
		try {
			String queryList = "SELECT u FROM Utilizador u";
			List<Utilizador> lista = em.createQuery(queryList, Utilizador.class).getResultList();
			return lista;
		}
		catch (NoResultException e) { return null; }
    }
    
    public Utilizador getUtilizador(String nomeUtilizador, String senha) {
    	if(dbDisabled) { return null;}
		  try {
		    Utilizador utilizador = em
		         .createQuery("SELECT u from Utilizador u where u.nomeUtilizador = :name and u.senha = :senha", Utilizador.class)
		         .setParameter("name", nomeUtilizador)
		         .setParameter("senha", senha)
		         .getSingleResult();
		    return utilizador;
		  } catch (NoResultException e) {
		  }
		  return null;
    }
    
    public Utilizador getUtilizador(int id_utilizador) {
    	if(dbDisabled) { return null;}
        try {
          return (Utilizador) em
  	         .createQuery("SELECT u from Utilizador u where u.id = :id")
  	         .setParameter("id", id_utilizador)
  	         .getSingleResult();
        } catch (NoResultException e) {    
        }
        return null;
      }
 
  public boolean regista(Utilizador utilizador) {
          try {
        	  	em.getTransaction().begin();
                em.persist(utilizador);
                em.getTransaction().commit();
                return true;
          } catch (Exception e) {
                e.printStackTrace();
          }
          return false;
    }
     
    public boolean elimina(Utilizador utilizador) {
          try {
        	  	em.getTransaction().begin();
                em.remove(utilizador);
                em.getTransaction().commit();
                return true;
          } catch (Exception e) {
                e.printStackTrace();
                return false;
          }
    }
    
    public boolean empty() {
        try {
        		return 0 == em.createQuery("SELECT u FROM Utilizador u ").getResultList().size();
	    } catch (Exception e) {
	            //e.printStackTrace();
	    }
        return false;
    }
    
    private void verificaSeTemUtilizadores() {
    	if(this.empty()) {
    		// insere o utilizador por defeito
    		try
    		{
	    		Date data = Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	    		
	    		Utilizador utilizador =  new Utilizador();
	    		utilizador.setNomeUtilizador("admin");
	    		utilizador.setSenha(LoginManagedBean.getSHA("admin"));
	    		utilizador.setUltimoAcesso(data);
	    		utilizador.setNivel('A');
	    		this.regista(utilizador);
    		}
    		catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
    		catch (Exception e) {
				e.printStackTrace();
			}
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
