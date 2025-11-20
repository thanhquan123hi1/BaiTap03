package vn.Quan.config;

import jakarta.persistence.*;


public class JPAConfig {
	public static EntityManager getEntityManager() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("DBMySQL");
		return factory.createEntityManager();
	}

}
