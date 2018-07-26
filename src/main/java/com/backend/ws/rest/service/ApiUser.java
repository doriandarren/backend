package com.backend.ws.rest.service;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.backend.ws.rest.business.DAOUser;

import entities.User;

public class ApiUser {
	
	
	@Inject
	private DAOUser service;
	
	
	//TODO FAKTA por hacer
		
	@POST
	@Path("/validUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public User validUser(User user) {		
		return service.signIn(user);
	}
	
}
