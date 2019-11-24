package br.com.projeto.util;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@ApplicationScoped
public class HibernateUtil {
	
	private static EntityManagerFactory emf;
	
	private static EntityManagerFactory getEntityManagerFactory() {
		
		if(emf==null) {
			synchronized(HibernateUtil.class){
				if(emf==null) {
					try {
						emf=(EntityManagerFactory) Persistence.createEntityManagerFactory("rest_estudo");
					} catch (RuntimeException ex) {
						throw ex;
					}
				}
			}
		}
		
		
		
		return (EntityManagerFactory) emf;
				
	}
	
	public static EntityManager getEntityManager() {
		try {
			return getEntityManagerFactory().createEntityManager();
		} catch (RuntimeException ex) {
			throw ex;
		}
	}		
		
	
//	private static EntityManagerFactory factory;
//	
//	static {
//		if(factory == null) {
//			factory = Persistence.createEntityManagerFactory("rest_estudo");	
//		}
//		
//	}
//	
//	@Produces
//	@RequestScoped
//	public EntityManager getEntityManager() {
//		return factory.createEntityManager();
//	}
//	
//	public Object getPrimaryKey(Object entity) {
//		return factory.getPersistenceUnitUtil().getIdentifier(entity);
//	}
//	
	
}