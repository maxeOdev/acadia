package com.hb.Service;

import com.hb.Model.Category;
import com.hb.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

/**
 * Classe de services permettant la gestion des catégories
 * 
 * @author simonaliotti
 *
 */
@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	/**
	 * Méthode permettant de créer une catégorie en bdd
	 * 
	 * @param categoryName le nom de la catégorie
	 * @return la catégorie crée
	 */
	@Transactional
	public Category createCategory(Category category) {
		category.setName(category.getName().toLowerCase());
		return categoryRepository.save(category);
	}

	/**
	 * Méthode permettant de récupérer la liste de toutes les catégories en bdd
	 * @return la liste des catégories existant en bdd
	 */
	public List<Category> getAllCategories() {
		List<Category> categories = new LinkedList<>();
		categories = categoryRepository.findAll();
		return categories;
	}
	
	/**
	 * Search the Category which's corresponding to a given name.
	 * @param name of the Category.
	 * @return The corresponding Category.
	 */
	public Category getByName(String name) {
		return categoryRepository.findByName(name);
	}
	
	/**
	 * Search the List of Categories which contain a given part of their name.
	 * @param part of name of the Categories.
	 * @return The corresponding Categories.
	 */
	public Category getByNameLike(String part) {
		return categoryRepository.findByName(part);
	}
	
	/**
	 * Search the Category which's corresponding to a given id.
	 * @param id of the Category.
	 * @return The corresponding Category.
	 */
	public Category getById(long id) {
		return categoryRepository.findById(id);
	}

	/**
	 * Count categories stored in the database.
	 * @return number of existing categories.
	 */
	public long countCategories() {
		return categoryRepository.count();
	}
	
	/**
	 * Update a categorie stored in the database.
	 * @param Category to update.
	 */
	@Transactional
	public Category updateCategory(Category category) {
		return categoryRepository.save(category);
	}
	
	/**
	 * Delete a categorie stored in the database.
	 * @param Category to delete.
	 */
	@Transactional
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}
	
	/**
	 * Delete all categories stored in the database.
	 */
	@Transactional
	public void deleteAllCategories() {
		categoryRepository.deleteAll();
	}
	
}
