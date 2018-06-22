/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.book.app.business;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.criteria.CriteriaQuery;

import org.apache.openejb.jee.DataSource;

import entities.Item;


@Deprecated
@Stateless
public class BookService {
	
	@PersistenceContext(unitName = "persistence-unit" )
    private EntityManager entityManager;
	

	
	public void deleteAll(){
		entityManager.createQuery("DELETE FROM Book").executeUpdate();		
	}
	  
	  
    public Item find(String bookId){
    	int id = Integer.valueOf(bookId); 
    	Item book= entityManager.find(Item.class,id); 
    	return book;  
    }
	  
    public void add(Item book){
      entityManager.persist(book);
    }
    
    public void remove(String bookId){
    	int id = Integer.valueOf(bookId); 
    	Item book= entityManager.find(Item.class,id); 
        entityManager.remove(book); 
    }
    
    public void update(Item book) {
    	
    	 //entityManager.getTransaction().begin();
    	   Item bookOld= entityManager.find(Item.class,book.getId());
    	    	   
    	   if(book.getTitle()!=null&& !book.getTitle().equals("")){
     		  bookOld.setTitle(book.getTitle()); 
     	   }    	   
    	   if(book.getDescription()!=null&& !book.getDescription().equals("")){
      		  bookOld.setDescription(book.getDescription()); 
      	   }
    	   
    	 //  entityManager.getTransaction().commit();  
		
	}
    
    
    

    
    
    
}
