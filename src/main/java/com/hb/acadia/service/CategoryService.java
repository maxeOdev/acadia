package com.hb.acadia.service;

import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.Category;
import com.hb.acadia.repository.CategoryRepository;

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
	public Category createCategory(String categoryName) {
		Category category = Category.builder().name(categoryName).build();
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
	 * @param name
	 * @return The corresponding Category.
	 */
	public Category getByName(String name) {
		return categoryRepository.findByName(name);
	}

}
