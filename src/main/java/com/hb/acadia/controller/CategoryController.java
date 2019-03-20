package com.hb.acadia.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hb.acadia.model.Category;
import com.hb.acadia.service.CategoryService;

/**
 * 
 * @author anis
 *
 */
@RequestMapping("/admin")
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/categories")
	public ModelAndView categories() {
		return new ModelAndView("category");
	}
	
	// @GetMapping("/search-categories")
	// public ModelAndView categories(String part) {
	// 	ModelAndView mav = new ModelAndView("generic");
	// 	mav.addObject("categories", categoryService.getByNameLike(part));
	// 	return mav;
	// }

	@GetMapping("/category-details") 
	public ModelAndView categoryDetails(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("category-details");
		Category category = categoryService.getByName(name);
		mav.addObject("category", category);
		return mav;
	}

	@PutMapping("/update-category")
	public ModelAndView updateCategory(@Valid Category category, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("category-details");
		
		if (bindingResult.hasErrors()) {
			mav.addObject("error", "Erreur lors de l'envois des données.");
			return mav;
		}
		
		categoryService.updateCategory(category);
		mav.addObject("category", category);
		return mav;
	}
	
	@GetMapping("/create-category")
	public ModelAndView createCategory() {
		ModelAndView mav = new ModelAndView("create-category");
		mav.addObject("category", new Category());
		return mav;
	}

	@PostMapping("/create-category")
	@ResponseBody
	public void createCategory(Category category) {
		category = categoryService.createCategory(category);
	}

	@DeleteMapping("/delete-category")
	@ResponseBody
	public void deleteCategory(Category category) {
		categoryService.deleteCategory(category);
	}
	
	
	/*******
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@GetMapping("/every-categories")
	@ResponseBody
	public List<Category> everyCategories() { 
		return categoryService.getAllCategories();
	}
	
	@GetMapping("/list-categories-template")
	public ModelAndView getCategoriesTemplate() { return new ModelAndView("fragments/list-categories"); }

	@GetMapping("/by-category-name")
	@ResponseBody
	public Category byCategoryName(String name) { 
		return categoryService.getByName(name);
	}

	@GetMapping("/category-details-template")
	public ModelAndView getCategoryDetailsTemplate() { return new ModelAndView("fragments/category-details"); }

	@GetMapping("/create-category-template")
	public ModelAndView getCreateCategoryTemplate() { return new ModelAndView("fragments/create-category"); }

}
