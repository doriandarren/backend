package com.backend.ws.rest.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import entities.User;


@Stateless
public class DAOUser implements DAOInfUser{
	
	@PersistenceContext(unitName="persistence-unit")
	private EntityManager entityManager;
		

	@Override
	public void create(User user) {
		if(user==null) {
			throw new IllegalArgumentException("> El User no puede ser null");
		}
		user.setCreateAt(new Date());
		entityManager.persist(user);				
	}

	@Override
	public void update(User user) {
		if(user==null) {
			throw new IllegalArgumentException("> El User no puede ser null");
		}
		User userUpdate = entityManager.find(User.class, user.getId());				
		
		if (user.getName() != null && !user.getName().equals("")) {
			userUpdate.setName(user.getName());
		}
		
		if (user.getEmail() != null && !user.getEmail().equals("")) {
			userUpdate.setEmail(user.getEmail());
		}
		
		if (user.getPassword() != null && !user.getPassword().equals("")) {
			userUpdate.setPassword(user.getPassword());
		}
		
		if (user.getName() != null && !user.getName().equals("")) {
			userUpdate.setName(user.getName());
		}
		
		
		if (user.getToken() != null && !user.getToken().equals("")) {
			userUpdate.setToken(user.getToken());
		}
		
		userUpdate.setValidUser(user.isValidUser());		
		userUpdate.setUpdateAt(new Date());
	}

	@Override
	public void delete(String key) {
		User user = entityManager.find(User.class, key);
		entityManager.remove(user);		
	}

	@Override
	public <T> T find(Class<T> clazz, Object id) {
		return entityManager.find(clazz, id);
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		String className = clazz.getSimpleName();
		return entityManager.createQuery("SELECT o FROM " + className  + " o")
				.getResultList();
	}

		
	@SuppressWarnings("unchecked")
	@Override
	public User signIn(User user) {
		User u = null;
		user.setValidUser(false);
		
		
		List<User> list = entityManager
									.createNamedQuery(User.QUERY_USER_BY_EMAIL_PASSWORD)
									.setParameter("email", user.getEmail())
									.setParameter("password", user.getPassword())
									.getResultList();
		
		if(list==null || list.size()!=1) {			
			throw new EntityNotFoundException(""
					+ "No se encuentra un usuario con el email: " + user.getEmail());
		}		
		
		createUserValidToken(list.get(0));		
		u = createUser(list.get(0));		
		return u;		
	}
	
	
	/**
	 * Guarda en la base datos 
	 * @param user
	 */
	private void createUserValidToken(User user) {
		User u = entityManager.find(User.class, user.getId());
		u.setValidUser(true);
		// TODO Crear y generar token unico con fecha (MD5 u otro)
		u.setToken(User.TOKEN);	
	}

	/**
	 * Crea un User sin password para retornar en el método 
	 * @param user
	 * @return
	 */
	private User createUser(User user) {
		User u = new User();
		u.setName(user.getName());
		u.setEmail(user.getEmail());
		u.setToken(user.getToken());
		u.setValidUser(user.isValidUser());
		u.setCreateAt(user.getCreateAt());
		u.setUpdateAt(user.getUpdateAt());
		return u;
	}

	@Override
	public void logout(String key) {
		User user = entityManager.find(User.class, key);		
		user.setToken(null);		
	}

	@Override
	public boolean loadUserIsValid(String key) {
		User user = entityManager.find(User.class, key);
		if(user!=null && user.isValidUser()) {
			return true;
		}		
		return false;
	}
		
	
	
	

}
