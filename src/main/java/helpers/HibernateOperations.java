package helpers;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class HibernateOperations {
	private static EntityManagerFactory manager;
	static {
		manager = Persistence.createEntityManagerFactory("model.Tema");
	}
	
	public static EntityManager getEntityManager() {
		return manager.createEntityManager();
	}

}
