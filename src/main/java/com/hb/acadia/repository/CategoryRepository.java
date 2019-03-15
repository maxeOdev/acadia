package com.hb.acadia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hb.acadia.model.Category;

/**
 * 
 * @author anis
 *
 */
public interface CategoryRepository extends JpaRepository<Category, Long> {

	/**
	 * @param name of the category
	 * @return Corresponding Category
	 */
	Category findByName(String name);
	
	/**
	 * @param id of the category
	 * @return Corresponding Category
	 */
	Category findById(long id);
	
	/**
	 * @param part of the name
	 * @return
	 */
	List<Category> findByNameLike(String part);
	
}
