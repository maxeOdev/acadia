package com.hb.repository;

import com.hb.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

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
