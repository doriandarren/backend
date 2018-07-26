package com.backend.ws.rest.business.brand;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.backend.ws.rest.business.DAOInfBrand;
import com.backend.ws.rest.business.tools.TestEjbHelper;

import entities.Brand;

public class TestFind extends BaseTestBrand{

	
	@Inject
	private DAOInfBrand service; 
	
	@Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 //service.removeAll(User.class);	
    }
	
	
	@Test
	public void testFind() {
		Brand brand = getMockBrand();
		service.createBrand(brand);				
		
		Brand brandFind = service.find(Brand.class, brand.getId());		
		Assert.assertNotNull(brandFind);
		Assert.assertEquals(NAME_BRAND, brandFind.getName());
		Assert.assertEquals(DESCRIPTION_BRAND, brandFind.getDescription());
		//Assert.assertEquals(CREATE_AT_BRAND, brandFind.getCreateAt());
		//Assert.assertEquals(UPDATE_AT_BRAND,brandFind.getUpdateAt());
	}
	
	
}
