package repository.hibernate;

import model.*;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    static {
        try {
            Configuration configuration = new Configuration().configure();
            configuration.addAnnotatedClass(Account.class);
            configuration.addAnnotatedClass(Event.class);
            configuration.addAnnotatedClass(File.class);
            configuration.addAnnotatedClass(User.class);
            sessionFactory = configuration.buildSessionFactory();
        } catch (Throwable e){
            System.err.println("Initial SessionFactory creation failed. "+ e);
        }
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }
}

