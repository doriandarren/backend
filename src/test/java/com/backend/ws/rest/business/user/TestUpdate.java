package com.backend.ws.rest.business.user;

import javax.ejb.embeddable.EJBContainer;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.backend.ws.rest.business.DAOInfUser;
import com.backend.ws.rest.business.tools.TestEjbHelper;

import entities.User;

public class TestUpdate extends BaseTestUser {
	
	@Inject
	private DAOInfUser service; 
		
	
	@Before
    public void before() throws Exception{   
		EJBContainer ejbContainer = TestEjbHelper.getEjbContainer(); 
    	ejbContainer.getContext().bind("inject", this);    
    	 //service.removeAll(User.class);
    }
	
	@Test
	public void testUpdate() {
		
		User user = getMockUser();
		service.create(user);
		
		
		User userUpdate = service.find(User.class, user.getId());
		
		userUpdate.setName(NAME_USER_UPDATE);
		userUpdate.setEmail(EMAIL_USER_UPDATE);
		userUpdate.setPassword(PASSWORD_USER_UPDATE);
		userUpdate.setToken(TOKEN_USER_UPDATE);
		userUpdate.setValidUser(VALID_USER_UPDATE);
		
		
		service.update(userUpdate);
		
		
		User userFind = service.find(User.class, userUpdate.getId());
		Assert.assertEquals(NAME_USER_UPDATE, userFind.getName());
		Assert.assertEquals(EMAIL_USER_UPDATE, userFind.getEmail());
		Assert.assertEquals(PASSWORD_USER_UPDATE, userFind.getPassword());
		
		Assert.assertEquals(TOKEN_USER_UPDATE, userFind.getToken());
		Assert.assertEquals(VALID_USER_UPDATE, userFind.isValidUser());		
		
		Assert.assertNotNull(userFind.getCreateAt());
		Assert.assertTrue(userFind.getUpdateAt().after(UPDATE_AT_USER_UPDATE));
	}
	

}
