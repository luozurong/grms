package com.hori.adms.util;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.orm.hibernate4.support.OpenSessionInViewFilter;

/**
 * The class is used to solve error like the following:
 * Write operations are not allowed in read-only mode (FlushMode.NEVER/MANUAL):
 * Turn your Session into FlushMode.COMMIT/AUTO
 * or remove 'readOnly' marker from transaction definition.
 */
public class SessionFilterUtil extends OpenSessionInViewFilter {
    /*
     * The default mode is FlushMode.NEVER
     *
     * @see org.springframework.orm.hibernate.support.OpenSessionInViewFilter#getSession(net.sf.hibernate.SessionFactory)
     */
    protected Session getSession(SessionFactory sessionFactory)
            throws DataAccessResourceFailureException {
        //Session session = super.getSession(sessionFactory);
    	Session session = super.openSession(sessionFactory);
        session.setFlushMode(FlushMode.COMMIT);
        return session;
    }

    /**
     * we do an explicit flush here just in case we do not have an automated flush
     */
    protected void closeSession(Session session, SessionFactory factory) {
      /*  try {
        	session.close();
		} catch (HibernateException e) {
			//不做处理
			//e.printStackTrace();
		}*/
        //super.closeSession(session, factory);
    	if (session != null) {
			logger.debug("Closing Hibernate Session");
			try {
				session.flush();
				//session.close();
			}
			catch (HibernateException ex) {
				logger.debug("Could not close Hibernate Session", ex);
			}
			catch (Throwable ex) {
				logger.debug("Unexpected exception on closing Hibernate Session", ex);
			}
		}
    }
}
