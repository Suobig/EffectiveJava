/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.feib.popov.HibernateTutorial;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


/**
 *
 * @author popov
 */
public class Util {
    private static final SessionFactory sessionFactory = buildSessionFactory();
    
    private static SessionFactory buildSessionFactory() {
        try {
            Configuration config = new Configuration();
            try {
                String password = SecurityManager.getPassword();
                config.setProperty("hibernate.connection.password", password);
                return config.configure().buildSessionFactory();
            } catch (BadAuthorizationException e) {
                System.err.println("Initial SessionFactory creation failed. " + e);
                throw new BadAuthorizationException(e);
            }
        } catch (HibernateException e) {
            System.err.println("Initial SessionFactory creation failed. " + e);
            throw new ExceptionInInitializerError(e);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    
}
