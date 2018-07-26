package com.backend.ws.rest.business;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import entities.User;

@Local
public interface DAOInfUser {

	
	public void create(@NotNull User user);
	public void update(@NotNull User user);
	public void delete(@NotNull String key);
	
	public User signIn(@NotNull User user);	
	public void logout(@NotNull String key);
	public boolean loadUserIsValid(String key);
	
	public <T> T find(Class<T> clazz, Object id);
	public <T> List<T> findAll(Class<T> clazz);
	
}
