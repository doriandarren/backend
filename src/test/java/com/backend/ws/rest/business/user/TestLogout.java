package com.backend.ws.rest.business.user;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.backend.ws.rest.business.DAOInfUser;
import com.backend.ws.rest.business.tools.TestEjbHelper;

import entities.User;

public class TestLogout extends BaseTestUser{
	
	@Inject
	private DAOInfUser service;
	
	
	@Before
    public void before() throws Exception{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 //service.removeAll(User.class);	
    }
	
	@Test
	public void testLogout() {
		
		User user = getMockUser();
		service.create(user);
		
		User userLogout = service.find(User.class, user.getId());
		
		Assert.assertNotNull(userLogout);
		
		service.logout(userLogout.getId());
		
		User userFind = service.find(User.class, user.getId());
		Assert.assertEquals(null, userFind.getToken());
			
	}
		
	
	

}
