package com.backend.ws.rest.business.brand;

import java.util.Date;

import entities.Brand;

public class BaseTest {

	protected static final String NAME_CLIENT = "GUCCI";
	protected static final String DESCRIPTION_CLIENT = "Tienda Mayorista";
	protected static final Date CREATE_AT_CLIENT = new Date();
	protected static final Date UPDATE_AT_CLIENT = null;
	
	
	protected static final String NAME_CLIENT_UPDATE = "MACA";
	protected static final String DESCRIPTION_CLIENT_UPDATE = "LA MEJOR";
	protected static final Date CREATE_AT_CLIENT_UPDATE = new Date();
	protected static final Date UPDATE_AT_CLIENT_UPDATE = new Date();
	
	protected Brand getMockBrand() {
		return getMockBrand(NAME_CLIENT,DESCRIPTION_CLIENT, CREATE_AT_CLIENT, UPDATE_AT_CLIENT);
	}

	protected Brand getMockBrand(String name, String description, Date createAt, Date updateAt) {
		Brand client = new Brand();
		client.setName(name);
		client.setDescription(description);
		client.setCreateAt(createAt);
		client.setUpdateAt(updateAt);
		return client;
	}
	
	
	
}
