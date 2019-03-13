package com.hb.acadia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.hb.acadia.service.CategoryService;

@RequestMapping("/admin")
@Controller
public class CategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@GetMapping("/categories")
	public ModelAndView categories() {
		ModelAndView mav = new ModelAndView("categories");
		mav.addObject("categories", categoryService.getAllCategories());
		return mav;
	}
	
}
