package com.hb.controller;

import com.hb.model.Category;
import com.hb.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

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

//	@GetMapping("/category-details") 
//	public ModelAndView categoryDetails(@RequestParam String name) {
//		ModelAndView mav = new ModelAndView("category-details");
//		Category category = categoryService.getByName(name);
//		mav.addObject("category", category);
//		return mav;
//	}

	@PutMapping("/update-category")
	@ResponseBody
	public ResponseEntity<List<ObjectError>> updateCategory(@Valid Category category, BindingResult result) {
		
		if (result.hasErrors()) {
			return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		
		categoryService.updateCategory(category);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
//	@GetMapping("/create-category")
//	public ModelAndView createCategory() {
//		ModelAndView mav = new ModelAndView("create-category");
//		mav.addObject("category", new Category());
//		return mav;
//	}

	@PostMapping("/create-category")
	@ResponseBody
	public ResponseEntity<List<ObjectError>> createCategory(@Valid Category category, BindingResult result) {
		
		if (result.hasErrors()) {
			return new ResponseEntity<List<ObjectError>>(result.getAllErrors(), HttpStatus.BAD_REQUEST);
		}
		
		if (categoryService.getByName(category.getName()) != null) {
			
			List<ObjectError> listError = new LinkedList<>();
			listError.add(new ObjectError("already-exists", "La catégorie " + category.getName() + " existe déjà."));
			
			return new ResponseEntity<List<ObjectError>>(listError, HttpStatus.BAD_REQUEST);
		}
		
		categoryService.createCategory(category);
		
		return new ResponseEntity<>(HttpStatus.OK);	
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
