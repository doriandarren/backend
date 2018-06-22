package com.backend.ws.rest.business.tools;

import entities.Collection;
import entities.Item;
import entities.User;

public class MockHelper {
	
	public static final String TEST_USER_EMAIL = "doriandarren1@gmail.com";
	
	
	 
    public static  User mockUser(String userName, String email){ 
   	 User user = new User();
   	 user.setName(userName); 
   	 user.setEmail(email);     	 
   	 return user; 
    }
     
    public static Collection mockCollection(String nameCollection){
	   	  Collection collection = new Collection();
		  collection.setDescription("Esta es una coleccion de test"); 
		  collection.setName(nameCollection); 
		  return collection; 
    }
    
    public static Item mockItem(String title, String description){
    	Item item = new Item();
    	item.setTitle(title);
    	item.setDescription(description);
    	return item;
    }

}
