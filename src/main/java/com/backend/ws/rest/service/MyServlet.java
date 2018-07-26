package com.backend.ws.rest.service;

import java.io.IOException;
import java.util.Date;

import javax.ejb.EJBException;
import javax.inject.Inject;
import javax.persistence.EntityExistsException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.backend.ws.rest.business.DAOInfBrand;

import entities.Brand;

/**
 * NO BORRAR ESTE EJEMPLO 
 * es un simple webservlet y almacena los datos en la bd
 * se injecta mediante un contenedor de EJB 
 * @author Dorian
 */

@WebServlet("/myServlet")
public class MyServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Inject
	private DAOInfBrand service;
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Brand  brand = new Brand();
		brand.setName("EJEMPLO");
		brand.setDescription("DESCRIPTION EJEMPLO");
		brand.setCreateAt(new Date());
		
		
		try {
			service.createBrand(brand);
		} catch (EJBException e) {
			e.getCausedByException();
			if(e.getClass().isAssignableFrom(EntityExistsException.class)){
				//El usuario existe
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}else{
				//Error, comprueba los datos del formulario o inetente mas tarde
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		}
		
		response.getWriter().println("Hello...");
				
	}
	
}
