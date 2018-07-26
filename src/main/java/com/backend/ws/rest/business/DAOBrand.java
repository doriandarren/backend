package com.backend.ws.rest.business;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Brand;


@Stateless
public class DAOBrand implements DAOInfBrand{

	@PersistenceContext(unitName="persistence-unit")
	private EntityManager entityManager;
	
	
	@Override
	public void createBrand(Brand brand) {
		if(brand==null) {
			throw new IllegalArgumentException("> El client no puede ser null");
		}
		brand.setCreateAt(new Date());
		entityManager.persist(brand);	
	}

	@Override
	public void deleteBrand(String key) {
		Brand clientFind = entityManager.find(Brand.class, key);
		entityManager.remove(clientFind);
	}

	@Override
	public void updateBrand(Brand brand) {
		if (brand == null) {
			throw new IllegalArgumentException("> El client no puede ser null");
		}

		Brand brandOld = entityManager.find(Brand.class, brand.getId());

		if (brand.getName() != null && !brand.getName().equals("")) {
			brandOld.setName(brand.getName());
		}

		if (brand.getDescription() != null && !brand.getDescription().equals("")) {
			brandOld.setDescription(brand.getDescription());
		}

		/*if (brand.getCreateAt() != null && !brand.getCreateAt().equals(null)) {
			brandOld.setCreateAt(brand.getCreateAt());
		}

		if (brand.getUpdateAt() != null && !brand.getUpdateAt().equals(null)) {
			brandOld.setUpdateAt(brand.getUpdateAt());
		}*/
		brandOld.setUpdateAt(new Date());
		
	}

	@Override
	public <T> T find(Class<T> clazz, Object id) {
		return entityManager.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findAll(Class<T> clazz) {
		String className = clazz.getSimpleName();
		return entityManager.createQuery("SELECT o FROM " + className  + " o")
				.getResultList();
	}
	
	
	/*
	 * OJO NO BORRAR
	 * VERIFICAR EL CRITERIO PARA findAll
	 * 
	 * 
	public List<Book> getAllBooks()
    {
        CriteriaQuery<Book> cq = entityManager.getCriteriaBuilder().createQuery(Book.class);
        cq.select(cq.from(Book.class));
        return entityManager.createQuery(cq).getResultList();
    }*/
	
	
	/**
	 * Para crate query con Constante declara al principio
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Brand> getByAll() {
		return entityManager.createNamedQuery(Brand.QUERY_CLIENT_ALL).getResultList();
		// .setParameter("author",author).getResultList();
	}
	

}
