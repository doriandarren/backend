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

public class TestDelete extends BaseTestBrand {

	@Inject
	private DAOInfBrand service; 
	
		
	//protected static EntityManagerFactory emf;
    //protected EntityManager em;

	    
	
	@Before
    public void before() throws NamingException{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 //service.removeAll(User.class);	
    	 
    	 
    	 //emf = Persistence.createEntityManagerFactory("persistence-unit");
    	 //em = emf.createEntityManager();
    }
	
	
	@Test
	public void testDelete() {
		Brand brand = getMockBrand();
		
		/*em.getTransaction().begin();
		em.persist(brand);
		em.getTransaction().commit();
		em.close();*/
		
		
		service.createBrand(brand);
				
		Brand brandDelete = service.find(Brand.class, brand.getId());
		service.deleteBrand(brandDelete.getId());	
		
		Brand brandFind = service.find(Brand.class, brandDelete.getId());		
		Assert.assertNull(brandFind);
	}
	
}
