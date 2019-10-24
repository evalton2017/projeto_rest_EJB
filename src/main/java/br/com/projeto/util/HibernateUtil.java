package br.com.projeto.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class HibernateUtil {
	
//	private static EntityManagerFactory emf;
//	
//	private static EntityManagerFactory getEntityManagerFactory() {
//		
//		if(emf==null) {
//			synchronized(HibernateUtil.class){
//				if(emf==null) {
//					try {
//						emf=(EntityManagerFactory) Persistence.createEntityManagerFactory("rest_estudo");
//					} catch (RuntimeException ex) {
//						throw ex;
//					}
//				}
//			}
//		}
//		
//		return (EntityManagerFactory) emf;
//				
//	}
//	
//	public static EntityManager getEntityManager() {
//		try {
//			return getEntityManagerFactory().createEntityManager();
//		} catch (RuntimeException ex) {
//			throw ex;
//		}
//	}		
		
	private static EntityManagerFactory factory;
	
	static {
		if(factory == null) {
			factory = Persistence.createEntityManagerFactory("rest_estudo");	
		}
		
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static Object getPrimaryKey(Object entity) {
		return factory.getPersistenceUnitUtil().getIdentifier(entity);
	}
	
	
}