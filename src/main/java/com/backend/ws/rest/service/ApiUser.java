package com.backend.ws.rest.service;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.backend.ws.rest.business.DAOInfUser;

import entities.User;



@Path("/apiuser/v1")
@RequestScoped
public class ApiUser {
	
	
	@Inject
	private DAOInfUser service;
		
	@POST
	@Path("/user/valid")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public User validUser(User user) {
		return service.signIn(user);
	}
	
	
	
	/**
	 *  Envio por POST
	 * URL: http://localhost:8080/backend/_ah/api/apiuser/v1/save
	 * @return
	 */
	@POST
	@Path("/save")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Message save(User user) {
		Message message = new Message();		
		try {
			user.setCreateAt(new Date());
			service.create(user);
			message.setMessage("Save...");
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessage("Fail...");			
		}		
		return message;
	}
	
	
	/**
	 * envio por POST
	 * URL: http://localhost:8080/backend/_ah/api/apiuser/v1/update
	 * @param user
	 * @return
	 */
	@POST
	@Path("/update")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Message update(User user) {		
		Message message = new Message();
		try {		
			User userUpdate = service.find(User.class, user.getId());
			userUpdate.setName(user.getName());
			userUpdate.setEmail(user.getEmail());
			userUpdate.setPassword(user.getPassword());
			userUpdate.setToken(user.getToken());
			userUpdate.setUpdateAt(new Date());
			service.update(userUpdate);			
			message.setMessage("Update...");			
		}catch(Exception e){
			e.printStackTrace();
			message.setMessage("Fail...");
		}
		return message;
	}
	
	
	
	/**
	 * URL: http://localhost:8080/backend/_ah/api/apiuser/v1/delete/1
	 * @param id
	 * @return
	 */
	@GET
	@Path("/delete/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Message delete(@PathParam("id") String id) {
		Message message = new Message();		
		try {			
			User user = service.find(User.class, id);
			service.delete(user.getId());
			message.setMessage("Delete...");
		} catch (Exception e) {			
			e.printStackTrace();
			message.setMessage("Fail...");
		}						
		return message;
	}
		
	
	/**
	 * URL: http://localhost:8080/backend/_ah/api/apiuser/v1/find_all
	 * @return
	 */
	@GET
	@Path("/find_all")
	@Produces({MediaType.APPLICATION_JSON})
	public List<User> findAll() {		
		//Map<String, Object> dummy = new HashMap<String, Object>();
		List<User> list =null;		
		try {
			list = service.findAll(User.class);
			//dummy.put("items", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;	
	}
	
	
	
	/**
	 * URL: http://localhost:8080/backend/_ah/api/apiuser/v1/find/1
	 * @param id
	 * @return
	 */
	
	@GET
	@Path("/find/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public User find(@PathParam("id") String id) {	
		User user = null;
		try {
			user = service.find(User.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return user;
	}
		
}
