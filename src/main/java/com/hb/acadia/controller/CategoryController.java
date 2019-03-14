package com.hb.acadia.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		ModelAndView mav = new ModelAndView("generic");
		mav.addObject("categories", categoryService.getAllCategories());
		return mav;
	}

	@GetMapping("/category-details")
	public ModelAndView categoryDetails(@RequestParam String name) {
		ModelAndView mav = new ModelAndView("category-details");
		Category category = categoryService.getByName(name);
		mav.addObject("category", category);
		return mav;
	}

	@PostMapping("/update-category")
	public ModelAndView updateCategory(@Valid Category category, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("categories");
		
		if (null == category) {
			mav.addObject("error", "Erreur lors de l'envois des données.");
			return mav;
		}
		
		categoryService.updateCategory(category);
		mav.addObject("category", category);
		return mav;
	}

	@PostMapping("/create-category")
	public ModelAndView createCategory(@Valid Category category, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("categories");
		
		if (bindingResult.hasErrors()) {
			mav.addObject("error", "Erreur lors de l'envois des données.");
			return mav;
		}
		
		if (categoryService.getById(category.getId()) == null) {
			mav.addObject("error", "La categorie '" + category.getName() + "' existe déjà.");
			return mav;
		}
		
		category = categoryService.createCategory(category.getName());
		mav.addObject("category", category);
		return mav;
	}

	@PostMapping("/delete-category")
	public ModelAndView deleteCategory(@Valid Category category, BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView("categories");
		
		if (bindingResult.hasErrors()) {
			mav.addObject("error", "Erreur lors de l'envois des données.");
			return mav;
		}
		
		categoryService.deleteCategory(category);
		return categories();
	}
	
}
