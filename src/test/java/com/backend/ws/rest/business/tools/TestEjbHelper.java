package com.backend.ws.rest.business.tools;

import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.apache.openejb.junit.Configuration;

public class TestEjbHelper {

	
	@Configuration
	public static  EJBContainer getEjbContainer(){ 
						
		Properties p = new Properties();
		p.setProperty("BD", "new://Resource?type=DataSource");
		p.setProperty("DB.JdbcDriver", "com.mysql.jdbc.Driver");
		p.setProperty("DB.JdbcUrl", "jdbc:mysql://localhost:3306/dbapi");
		p.setProperty("DB.UserName", "root");
		p.setProperty("DB.Password", "1245");
		
		EJBContainer ejbContainer = EJBContainer.createEJBContainer(p); 
		return ejbContainer;
	}
	
	
}
