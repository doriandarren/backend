package com.backend.ws.rest.business.user;

import java.util.Date;

import entities.User;

public class BaseTestUser {
	
	protected static final String NAME_USER = "Martha";
	protected static final String EMAIL_USER = "darimile@gmail.com";
	protected static final String PASSWORD_USER = "1234";
	protected static final boolean VALID_USER = true;
	protected static final String TOKEN_USER = "KILOKMJIM5625";
	protected static final Date CREATE_AT_USER = new Date();
	protected static final Date UPDATE_AT_USER = new Date();
	
	
	protected static final String NAME_USER_UPDATE = "User";
	protected static final String EMAIL_USER_UPDATE = "user@user.es";
	protected static final String PASSWORD_USER_UPDATE = "4321";
	protected static final boolean VALID_USER_UPDATE = false;
	protected static final String TOKEN_USER_UPDATE = "FTGRYGGG666";
	protected static final Date CREATE_AT_USER_UPDATE = new Date();
	protected static final Date UPDATE_AT_USER_UPDATE = new Date();
	

	protected User getMockUser() {
		return getMockUser(NAME_USER, EMAIL_USER,PASSWORD_USER, VALID_USER,TOKEN_USER,CREATE_AT_USER,UPDATE_AT_USER);	
	}

	protected User getMockUser(String nameUser, String emailUser, String passwordUser, boolean validUser, String tokenUser,
			Date createAtUser, Date updateAtUser) {
		
		User user = new User();		
		user.setName(nameUser);
		user.setEmail(emailUser);
		user.setPassword(passwordUser);
		user.setValidUser(validUser);
		user.setToken(tokenUser);
		user.setCreateAt(createAtUser);
		user.setUpdateAt(updateAtUser);
		return user;
	}
	
	
}
