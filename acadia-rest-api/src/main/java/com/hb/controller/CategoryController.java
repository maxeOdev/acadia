package com.hb.controller;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.model.Category;
import com.hb.service.CategoryService;

/**
 * 
 * @author anis
 *
 */
@RestController
@Validated
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	/**
	 * @return every saved categories
	 */
	@GetMapping("/public/categories")
	public List<Category> categories() {
		log.debug("categories");
		return categoryService.getAllCategories();
	}
	
//	/**
//	 * @param name
//	 * @return categories exactly matching with param name
//	 */
//	@GetMapping("/public/categories/{name}")
//	public Category byName(@PathVariable("name") String name) { 
//		return categoryService.getByName(name);
//	}
//	
	/**
	 * @param id
	 * @return categories matching with param id
	 */
	@GetMapping("/public/categories/{id}")
	public Category byId(@PathVariable("id") long id) { 
		log.debug("categoryById");
		return categoryService.getById(id);
	}
	
	/**
	 * @param category
	 * @param result
	 * @return errors if there're some, else return null
	 */
	@PostMapping("/api/categories")
	public List<ObjectError> create(@Valid Category category, BindingResult result) {
		log.debug("categoryCreation");
		
		if (result.hasErrors()) {
			return result.getAllErrors();
		}
		
		if (categoryService.getByName(category.getName()) != null) {
			
			List<ObjectError> listError = new LinkedList<>();
			listError.add(
					new ObjectError("already-exists", "La catégorie " + category.getName() + " existe déjà."));
			
			return listError;
		}
		
		categoryService.createCategory(category);
		
		return null;	
	}

	/**
	 * @param category
	 * @param result
	 * @return errors if there're some, else return null
	 */
	@PutMapping("/api/categories")
	public List<ObjectError> update(@Valid Category category, BindingResult result) {
		log.debug("categoryUpdate");
		
		if (result.hasErrors()) {	return result.getAllErrors();	}
		
		categoryService.updateCategory(category);
		
		return null;
	}
	
	/**
	 * Delete the category matching with the param "id"
	 * @param id
	 */
	@DeleteMapping("/api/categories/{id}")
	public void delete(@PathVariable("id") long id) {
		log.debug("categoryDeletion");
		categoryService.deleteCategory(
				categoryService.getById(id));
	}
	
}
