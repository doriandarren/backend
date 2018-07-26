package com.backend.ws.rest.business.brand;

import java.util.Date;

import entities.Brand;

public class BaseTestBrand {

	protected static final String NAME_BRAND = "GUCCI";
	protected static final String DESCRIPTION_BRAND = "Tienda Mayorista";
	protected static final Date CREATE_AT_BRAND = new Date();
	protected static final Date UPDATE_AT_BRAND = null;
	
	
	protected static final String NAME_BRAND_UPDATE = "MACA";
	protected static final String DESCRIPTION_BRAND_UPDATE = "LA MEJOR";
	protected static final Date CREATE_AT_BRAND_UPDATE = new Date();
	protected static final Date UPDATE_AT_BRAND_UPDATE = new Date();
	
	protected Brand getMockBrand() {
		return getMockBrand(NAME_BRAND,DESCRIPTION_BRAND, CREATE_AT_BRAND, UPDATE_AT_BRAND);
	}

	protected Brand getMockBrand(String name, String description, Date createAt, Date updateAt) {
		Brand brand = new Brand();
		brand.setName(name);
		brand.setDescription(description);
		brand.setCreateAt(createAt);
		brand.setUpdateAt(updateAt);
		return brand;
	}
	
	
	
}
