package bean;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import org.hibernate.cfg.Configuration;
import org.postgresql.core.SetupQueryRunner;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author rafa
 */
public class HibernateUtil {

    private static SessionFactory sessionFactory;
    
    static {
    	try {
    	// A SessionFactory is set up once for an application!
    	final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
    			.configure() // configures settings from hibernate.cfg.xml
    			.build();
    	
    	try {
    		sessionFactory = new MetadataSources( registry ).buildMetadata().buildSessionFactory();
    		System.out.println("opa");
    		
    	}
    	catch (Exception e) {
    		// The registry would be destroyed by the SessionFactory, but we had trouble building the SessionFactory
    		// so destroy it manually.
    		e.printStackTrace();
    		StandardServiceRegistryBuilder.destroy( registry );
    	}
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
    
  
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
