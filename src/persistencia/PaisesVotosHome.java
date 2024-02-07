package persistencia;
// Generated 7 feb 2024 13:02:00 by Hibernate Tools 5.4.33.Final

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

/**
 * Home object for domain model class PaisesVotos.
 * @see persistencia.PaisesVotos
 * @author Hibernate Tools
 */
public class PaisesVotosHome {

	private static final Logger logger = Logger.getLogger(PaisesVotosHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(PaisesVotos transientInstance) {
		logger.log(Level.INFO, "persisting PaisesVotos instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PaisesVotos instance) {
		logger.log(Level.INFO, "attaching dirty PaisesVotos instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(PaisesVotos instance) {
		logger.log(Level.INFO, "attaching clean PaisesVotos instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(PaisesVotos persistentInstance) {
		logger.log(Level.INFO, "deleting PaisesVotos instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public PaisesVotos merge(PaisesVotos detachedInstance) {
		logger.log(Level.INFO, "merging PaisesVotos instance");
		try {
			PaisesVotos result = (PaisesVotos) sessionFactory.getCurrentSession().merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public PaisesVotos findById(java.lang.String id) {
		logger.log(Level.INFO, "getting PaisesVotos instance with id: " + id);
		try {
			PaisesVotos instance = (PaisesVotos) sessionFactory.getCurrentSession().get("persistencia.PaisesVotos", id);
			if (instance == null) {
				logger.log(Level.INFO, "get successful, no instance found");
			} else {
				logger.log(Level.INFO, "get successful, instance found");
			}
			return instance;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "get failed", re);
			throw re;
		}
	}

	public List findByExample(PaisesVotos instance) {
		logger.log(Level.INFO, "finding PaisesVotos instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("persistencia.PaisesVotos")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
}
