/*package com.jlit.uaas.util;

import org.hibernate.FlushMode;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessResourceFailureException;

*//**
 * The class is used to solve error like the following:
 * Write operations are not allowed in read-only mode (FlushMode.NEVER/MANUAL):
 * Turn your Session into FlushMode.COMMIT/AUTO
 * or remove 'readOnly' marker from transaction definition.
 *//*
public class SessionFilterUtil extends OpenSessionInViewFilter {
    
     * The default mode is FlushMode.NEVER
     *
     * @see org.springframework.orm.hibernate.support.OpenSessionInViewFilter#getSession(net.sf.hibernate.SessionFactory)
     
    protected Session getSession(SessionFactory sessionFactory)
            throws DataAccessResourceFailureException {
        Session session = super.getSession(sessionFactory);
        session.setFlushMode(FlushMode.COMMIT);
        return session;
    }

    *//**
     * we do an explicit flush here just in case we do not have an automated flush
     *//*
    protected void closeSession(Session session, SessionFactory factory) {
        try {
			session.flush();
			 super.closeSession(session, factory);
		} catch (HibernateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
}
*/