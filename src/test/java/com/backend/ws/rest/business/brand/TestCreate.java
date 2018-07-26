package com.backend.ws.rest.business.brand;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.backend.ws.rest.business.DAOInfBrand;
import com.backend.ws.rest.business.tools.TestEjbHelper;
import entities.Brand;

public class TestCreate extends BaseTestBrand {

	@Inject
	private DAOInfBrand service; 
		
	
	@Before
    public void before() throws Exception{   
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
		Assert.assertEquals(NAME_BRAND, brandFind.getName());
		Assert.assertEquals(DESCRIPTION_BRAND, brandFind.getDescription());
		//la comprobacion es ser mayor a la fecha de creación
		//ya que en el metodo lo hace automaticamente
		Assert.assertTrue(brandFind.getCreateAt().after(CREATE_AT_BRAND));
		Assert.assertNull(brandFind.getUpdateAt());		
		
		//Assert.assertTrue(brand instanceof Brand);
		
	}

	
}
