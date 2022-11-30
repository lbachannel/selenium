package com.atteam.selenium.model;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import com.atteam.selenium.utils.jpaUtils;

//import antlr.collections.List;
import java.util.List; //Quan trong

public class UserDAO {
	private EntityManager em = jpaUtils.getEntityManager();
	
	@Override
	protected void finalize()throws Throwable{
		em.close();
	}
	
	public User create(User entity) {
		try {
			em.getTransaction().begin();
			em.persist(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	public User update(User entity) {
		try {
			em.getTransaction().begin();
			em.merge(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	public User remove(User entity) {
		try {
			em.getTransaction().begin();
			em.remove(entity);
			em.getTransaction().commit();
			return entity;
		} catch (Exception e) {
			em.getTransaction().rollback();
			throw new RuntimeException(e);
		}
	}
	
	public User findById(String id) {
		User entity = em.find(User.class, id);
		return entity;
	}
	
	public List<User> findByFullname(String fullname) {
		String jpql = "SELECT o FROM User o WHERE o.Fullname=:fullname";
		TypedQuery<User> query = em.createQuery(jpql,User.class);
		List<User> list = query.getResultList();
		return list;
	}
	
	public List<User> findAll(){
		String jpql = "SELECT o FROM User o";
		TypedQuery<User> query = em.createQuery(jpql,User.class);
		List<User> list = query.getResultList();
		return list;
	}
}
