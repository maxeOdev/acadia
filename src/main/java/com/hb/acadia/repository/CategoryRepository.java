package com.hb.acadia.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.Category;

/**
 * 
 * @author anis
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * @param name
	 * @return Corresponding Category
	 */
	Category findByName(String name);
	
	/**
	 * @param id
	 * @return Corresponding Category
	 */
	Category findById(long id);
	
}
