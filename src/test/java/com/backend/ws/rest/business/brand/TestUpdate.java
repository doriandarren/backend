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

public class TestUpdate extends BaseTest {

	@Inject
	private DAOInfBrand service; 
	
	@Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 //service.removeAll(User.class);	
    }
	
	
	@Test
	public void testUpdate() {				
		Brand brand = getMockBrand();
		service.createBrand(brand);
				
		Brand brandUpdate = service.find(Brand.class, brand.getId());
		brandUpdate.setName(NAME_CLIENT_UPDATE);
		brandUpdate.setDescription(DESCRIPTION_CLIENT_UPDATE);
		brandUpdate.setCreateAt(CREATE_AT_CLIENT_UPDATE);
		brandUpdate.setUpdateAt(UPDATE_AT_CLIENT_UPDATE);
		
		service.updateBrand(brandUpdate);
						
		Brand brandFind = service.find(Brand.class, brand.getId());		
		Assert.assertEquals(NAME_CLIENT_UPDATE, brandFind.getName());
		Assert.assertEquals(DESCRIPTION_CLIENT_UPDATE, brandFind.getDescription());
		Assert.assertEquals(CREATE_AT_CLIENT_UPDATE, brandFind.getCreateAt());
		Assert.assertEquals(UPDATE_AT_CLIENT_UPDATE,brandFind.getUpdateAt());
	}
	
	
	
	
}
