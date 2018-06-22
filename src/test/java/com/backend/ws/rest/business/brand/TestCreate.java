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

public class TestCreate extends BaseTest {

	@Inject
	private DAOInfBrand service; 
	
	@Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 //service.removeAll(User.class);	
    }
	
	
	@Test
	public void testCreate() {
		
		Brand brand = getMockBrand();
		service.createBrand(brand);
		
		Brand brandFind = service.find(Brand.class, brand.getId());
		
		Assert.assertNotNull(brandFind);
		Assert.assertEquals(NAME_CLIENT, brandFind.getName());
		Assert.assertEquals(DESCRIPTION_CLIENT, brandFind.getDescription());
		Assert.assertEquals(CREATE_AT_CLIENT, brandFind.getCreateAt());
		Assert.assertEquals(UPDATE_AT_CLIENT,brandFind.getUpdateAt());
		
	}
	
}
