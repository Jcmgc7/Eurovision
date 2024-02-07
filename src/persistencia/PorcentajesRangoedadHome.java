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
 * Home object for domain model class PorcentajesRangoedad.
 * @see persistencia.PorcentajesRangoedad
 * @author Hibernate Tools
 */
public class PorcentajesRangoedadHome {

	private static final Logger logger = Logger.getLogger(PorcentajesRangoedadHome.class.getName());

	private final SessionFactory sessionFactory = getSessionFactory();

	protected SessionFactory getSessionFactory() {
		try {
			return (SessionFactory) new InitialContext().lookup("SessionFactory");
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Could not locate SessionFactory in JNDI", e);
			throw new IllegalStateException("Could not locate SessionFactory in JNDI");
		}
	}

	public void persist(PorcentajesRangoedad transientInstance) {
		logger.log(Level.INFO, "persisting PorcentajesRangoedad instance");
		try {
			sessionFactory.getCurrentSession().persist(transientInstance);
			logger.log(Level.INFO, "persist successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "persist failed", re);
			throw re;
		}
	}

	public void attachDirty(PorcentajesRangoedad instance) {
		logger.log(Level.INFO, "attaching dirty PorcentajesRangoedad instance");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate(instance);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void attachClean(PorcentajesRangoedad instance) {
		logger.log(Level.INFO, "attaching clean PorcentajesRangoedad instance");
		try {
			sessionFactory.getCurrentSession().lock(instance, LockMode.NONE);
			logger.log(Level.INFO, "attach successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "attach failed", re);
			throw re;
		}
	}

	public void delete(PorcentajesRangoedad persistentInstance) {
		logger.log(Level.INFO, "deleting PorcentajesRangoedad instance");
		try {
			sessionFactory.getCurrentSession().delete(persistentInstance);
			logger.log(Level.INFO, "delete successful");
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "delete failed", re);
			throw re;
		}
	}

	public PorcentajesRangoedad merge(PorcentajesRangoedad detachedInstance) {
		logger.log(Level.INFO, "merging PorcentajesRangoedad instance");
		try {
			PorcentajesRangoedad result = (PorcentajesRangoedad) sessionFactory.getCurrentSession()
					.merge(detachedInstance);
			logger.log(Level.INFO, "merge successful");
			return result;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "merge failed", re);
			throw re;
		}
	}

	public PorcentajesRangoedad findById(java.lang.String id) {
		logger.log(Level.INFO, "getting PorcentajesRangoedad instance with id: " + id);
		try {
			PorcentajesRangoedad instance = (PorcentajesRangoedad) sessionFactory.getCurrentSession()
					.get("persistencia.PorcentajesRangoedad", id);
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

	public List findByExample(PorcentajesRangoedad instance) {
		logger.log(Level.INFO, "finding PorcentajesRangoedad instance by example");
		try {
			List results = sessionFactory.getCurrentSession().createCriteria("persistencia.PorcentajesRangoedad")
					.add(Example.create(instance)).list();
			logger.log(Level.INFO, "find by example successful, result size: " + results.size());
			return results;
		} catch (RuntimeException re) {
			logger.log(Level.SEVERE, "find by example failed", re);
			throw re;
		}
	}
}
