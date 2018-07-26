package com.backend.ws.rest.service;

import java.io.PrintWriter;
import java.io.StringWriter;
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
import com.backend.ws.rest.business.DAOInfBrand;

import entities.Brand;

@Path("/apibrand/v1")
@RequestScoped
public class ApiBrand {

	@Inject
	private DAOInfBrand service;
	
	
	/**
	 * Envio por POST
	 * URL: http://localhost:8080/backend/_ah/api/apibrand/v1/save
	 * @param brand
	 * @return
	 */
	@POST
	@Path("/save")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Message save(Brand brand) {		
		Message message = new Message();
		try {
			brand.setCreateAt(new Date());
			service.createBrand(brand);
			message.setMessage("Save...");
		} catch (Exception e) {
			e.printStackTrace();
			message.setMessage("Fail...");
			//String mess = getStackTrace(e);
			//message.setMessage("Fail..."+ e.getMessage() + mess);			
		}						
		return message;
	}
	
	/**
	 * envio por POST
	 * URL: http://localhost:8080/backend/_ah/api/apibrand/v1/update
	 * @param brand
	 * @return
	 */
	@POST
	@Path("/update")
	@Consumes({MediaType.APPLICATION_JSON})
	@Produces({MediaType.APPLICATION_JSON})
	public Message update(Brand brand) {		
		Message message = new Message();
		try {		
			Brand clientUpdate = service.find(Brand.class, brand.getId());
			clientUpdate.setName(brand.getName());
			clientUpdate.setDescription(brand.getDescription());
			clientUpdate.setUpdateAt(new Date());
			service.updateBrand(clientUpdate);			
			message.setMessage("Update...");			
		}catch(Exception e){
			e.printStackTrace();
			message.setMessage("Fail...");
		}
		return message;
	}
	
	
	
	/**
	 * URL: http://localhost:8080/backend/_ah/api/apibrand/v1/delete/1
	 * @param id
	 * @return
	 */
	@GET
	@Path("/delete/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Message delete(@PathParam("id") String id) {
		Message message = new Message();		
		try {			
			Brand brand = service.find(Brand.class, id);
			service.deleteBrand(brand.getId());
			message.setMessage("Delete...");
		} catch (Exception e) {			
			e.printStackTrace();
			message.setMessage("Fail...");
		}						
		return message;
	}
	
	
	
	/**
	 * URL: http://localhost:8080/backend/_ah/api/apibrand/v1/find_all
	 * @return
	 */
	@GET
	@Path("/find_all")
	@Produces({MediaType.APPLICATION_JSON})
	public List<Brand> findAll() {
		
		//Map<String, Object> dummy = new HashMap<String, Object>();
		List<Brand> list =null;		
		try {
			list = service.findAll(Brand.class);
			//dummy.put("items", list);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		return list;	
	}
	
	
	/**
	 * URL: http://localhost:8080/backend/_ah/api/apibrand/v1/find/1
	 * @param clientId
	 * @return
	 */
	
	@GET
	@Path("/find/{id}")
	@Produces({MediaType.APPLICATION_JSON})
	public Brand find(@PathParam("id") String id) {	
		Brand brand = null;		
		try {
			brand = service.find(Brand.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		return brand;
	}
	
	
	/**
	 * SOLO PARA TEST Y ENVIAR ERRORES
	 * @param throwable
	 * @return
	 */
	public static String getStackTrace(final Throwable throwable) {
	     final StringWriter sw = new StringWriter();
	     final PrintWriter pw = new PrintWriter(sw, true);
	     throwable.printStackTrace(pw);
	     return sw.getBuffer().toString();
	}
	
	
}
