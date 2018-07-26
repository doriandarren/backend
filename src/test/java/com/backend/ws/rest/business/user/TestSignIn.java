package com.backend.ws.rest.business.user;

import java.util.Date;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.backend.ws.rest.business.DAOInfUser;
import com.backend.ws.rest.business.tools.TestEjbHelper;

import entities.User;

public class TestSignIn extends BaseTestUser {
	
	@Inject
	private DAOInfUser service;
	
	
	@Before
    public void before() throws Exception{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 //service.removeAll(User.class);	
    }
	
	
	@Test
	public void testSignIn() {
		User user = getMockUser("Martha", "darimile@gmail.com", "1234", false, "",	new Date(), new Date());
		//para efecto de test
		service.create(user);
		
		User userSignIn = service.signIn(user);
		Assert.assertNotNull(userSignIn);
		Assert.assertTrue(userSignIn.isValidUser());
		Assert.assertEquals(User.TOKEN, userSignIn.getToken());
	}
	
	
	
	//@Test
	public void testSignInWithUserError() {
		
		User user = getMockUser("Martha", "pepa@pic.es", "4521", false, "",	new Date(), new Date());
		
		
		//service.create(user);
		
		//User userFind = service.find(User.class, user.getId());
		//Assert.assertNotNull(userFind);
		
		
		User userSignIn = service.signIn(user);		
		
		Assert.assertNull(userSignIn);
		Assert.assertFalse(userSignIn.isValidUser());
		Assert.assertEquals(User.TOKEN, userSignIn.getToken());
		
	}
	

}
