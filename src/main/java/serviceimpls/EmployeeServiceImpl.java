package serviceimpls;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.ejb.HibernateEntityManager;

import entities.Employee;
import services.EmployeeService;

public class EmployeeServiceImpl implements EmployeeService {
	
	private EntityManagerFactory emf;
	private EntityManager em;
	private HibernateEntityManager hem;
	
	@PersistenceUnit
	public void setEntityManagerFactory(EntityManagerFactory emf) {
		this.emf = emf;
		em = emf.createEntityManager();
		hem = (HibernateEntityManager) em;
	}	

	//@PersistenceContext
    public void setEntityManager(EntityManager em) {
        this.em = em;
        hem = (HibernateEntityManager) em;
    }
	
	public void setHibernateEntityManager(HibernateEntityManager hem) {
		this.hem = hem;
	}
	
	public void deleteAll() {
		em.getTransaction().begin();
		em.createQuery("DELETE FROM Employee").executeUpdate();
		em.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Employee> findAll() {
		Session session = hem.getSession();
		Criteria crit = session.createCriteria(Employee.class);
		session.getTransaction().begin();
		List<Employee> result = crit.list();
		session.getTransaction().commit();

//		em.getTransaction().begin();
//		List<Employee> result = em.createQuery("SELECT empl FROM Employee empl").getResultList();
//		em.getTransaction().commit();
		return result;
	}

	@Override
	public void save(Employee entity) {
		Session session = hem.getSession();
		session.getTransaction().begin();
		session.saveOrUpdate(entity);
		session.getTransaction().commit();
		
//		em.getTransaction().begin();
//		if (entity.getId() == null) {
//			//insert
//			em.persist(entity);
//		} else {
//			//update
//			em.merge(entity);
//		}
//		em.getTransaction().commit();
	}

	@Override
	public void removeById(Long id) {
//		Session session = hem.getSession();
//		session.getTransaction().begin();
//		Employee empl = (Employee) session.get(Employee.class, id);
//		if (empl != null) {
//			session.delete(empl);
//		}
//		session.getTransaction().commit();
		
		
		em.getTransaction().begin();
		Employee empl = em.find(Employee.class, id);
		if (empl != null) {
			em.remove(empl);
		}
		em.getTransaction().commit();
	}

	@Override
	public Employee find(Long id) {
//		Session session = hem.getSession();
//		session.getTransaction().begin();
//		Employee result = (Employee) session.get(Employee.class, id);
//		session.getTransaction().commit();		
		
		em.getTransaction().begin();
		Employee result = em.find(Employee.class, id);
		em.getTransaction().commit();
		
		return result;
	}

	@Override
	public Long countAll() {
		Session session = hem.getSession();
		Criteria crit = session.createCriteria(Employee.class);
		session.getTransaction().begin();
		Integer resultInt = (Integer) crit.setProjection(Projections.rowCount()).uniqueResult();
		Long result = resultInt.longValue();
		session.getTransaction().commit();
		
//		em.getTransaction().begin();
//		Long result = (Long) em.createQuery("SELECT COUNT(*) FROM Employee emp").getSingleResult();
//		em.getTransaction().commit();
		return result;
	}

}
