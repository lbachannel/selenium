package com.atteam.selenium.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class jpaUtils {
//	public static EntityManager getEntityManager() {
//		EntityManagerFactory factory = Persistence.createEntityManagerFactory("PS19749_Lab5hibernate");
//		return factory.createEntityManager();
//	}
	
	private static EntityManagerFactory factory;
	public static EntityManager getEntityManager() {
		if(factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("PS19749_Lab5hibernate");
		}
		return factory.createEntityManager();
	}
	
	public static void shutdown() {
		if(factory != null && factory.isOpen()) {
			factory.close();
		}
		factory = null;
	}
}
