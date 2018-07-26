package com.backend.ws.rest.business.user;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.backend.ws.rest.business.DAOInfUser;
import com.backend.ws.rest.business.tools.TestEjbHelper;

import entities.User;

public class TestCreate extends BaseTestUser {
	
	@Inject
	private DAOInfUser service;
	
	
	@Before
    public void before() throws Exception{   
    	EJBContainer ejbContainer = TestEjbHelper.getEjbContainer();  	
    	 ejbContainer.getContext().bind("inject", this);    	
    	 //service.removeAll(User.class);	
    }
	
	
	@Test
	public void testCreate() {
		
		User user = getMockUser();
		service.create(user);
		
		User userFind = service.find(User.class, user.getId());
		
		Assert.assertNotNull(userFind);
		
		Assert.assertEquals(NAME_USER, userFind.getName());
		Assert.assertEquals(EMAIL_USER, userFind.getEmail());
		Assert.assertEquals(PASSWORD_USER,user.getPassword());
		Assert.assertEquals(VALID_USER, userFind.isValidUser());
		Assert.assertEquals(TOKEN_USER, userFind.getToken());
		//Assert.assertEquals(CREATE_AT_USER, userFind.getCreateAt());
		//Assert.assertEquals(UPDATE_AT_USER, userFind.getUpdateAt());
		
	}
	

}
