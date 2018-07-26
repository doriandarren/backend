package com.backend.ws.rest.business.user;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.backend.ws.rest.business.DAOInfBrand;
import com.backend.ws.rest.business.DAOInfUser;
import com.backend.ws.rest.business.tools.TestEjbHelper;

import entities.User;

public class TestDelete extends BaseTestUser {

	@Inject
	private DAOInfUser service; 
		
	
	@Before
    public void before() throws Exception{   
		EJBContainer ejbContainer = TestEjbHelper.getEjbContainer(); 
    	ejbContainer.getContext().bind("inject", this);    
    	 //service.removeAll(User.class);
    }
	
	@Test
	public void testDelete() {
		User user = getMockUser();
		service.create(user);
		
		
		User userDelete = service.find(User.class, user.getId());
		service.delete(userDelete.getId());
				
		User userFind = service.find(User.class, userDelete.getId());
		Assert.assertNull(userFind);
		
	}
	
	
}
