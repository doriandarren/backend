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

public class TestDelete extends BaseTest {

	@Inject
	private DAOInfBrand service; 
	
	@Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 //service.removeAll(User.class);	
    }
	
	
	@Test
	public void testDelete() {
		Brand brand = getMockBrand();
		service.createBrand(brand);
				
		Brand brandDelete = service.find(Brand.class, brand.getId());
		service.deleteBrand(brandDelete.getId());	
		
		Brand brandFind = service.find(Brand.class, brand.getId());		
		Assert.assertNull(brandFind);
	}
	
}
