package com.backend.ws.rest.service;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.backend.ws.rest.business.DAOInfBrand;

import entities.Brand;
import entities.User;

@Path("/myserviceapi/v1")
@RequestScoped 
public class MyServiceApi {
	
	@Inject
	private DAOInfBrand service;
	
	@GET
	@Path("/test")
	@Produces({MediaType.APPLICATION_JSON})	
	public Response testApi() {		
				
		Brand brand = new Brand();
		brand.setName("SAVE A BRAND");
		brand.setDescription("Description SAVE A BRAND");
		service.createBrand(brand);
		//String output = "{\"singer\":\"Metallica\",\"title\":\"Fade To Black\"}";
		
		Message output = new Message();
		output.setMessage("Save");
		
		return Response.ok() //status 200
//				.header("Access-Control-Allow-Origin", "*")
	//			.header("Access-Control-Allow-Methods", "POST, GET, PUT, UPDATE, OPTIONS")
//				.header("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With")				
				.entity(output)
				.build();
	}
		
	
	@POST
	@Path("/validUser")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public User validUser(User user) {
		user.setValidUser(false);		
		if(user.getEmail().equals("darimile@gmail.com") && user.getPassword().equals("4321")) {
			user.setValidUser(true);
		}
		user.setPassword("#############");
		return user;		
	}
	
}

