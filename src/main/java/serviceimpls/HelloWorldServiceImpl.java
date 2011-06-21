package serviceimpls;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import services.HelloWorldService;

public class HelloWorldServiceImpl implements HelloWorldService {
	
	private EntityManager em;
	private EntityManagerFactory emf;

	@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
    }
	
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
		this.em = emf.createEntityManager();
	}	

	@Override
	public String getGreeting() {
		return "Hello World!";
	}

}
