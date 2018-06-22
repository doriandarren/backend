package com.backend.ws.rest.business;

import java.util.List;

import javax.ejb.Local;
import javax.validation.constraints.NotNull;

import entities.Brand;

@Local
public interface DAOInfBrand {
	
	public void createBrand(@NotNull Brand brand);
	public void deleteBrand(@NotNull String key);	
	public void updateBrand(@NotNull Brand brand);
	
	
	public <T> T find(Class<T> clazz, Object id);
	public <T> List<T> findAll(Class<T> clazz);
	
}
