package com.hb.acadia.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hb.acadia.model.Category;
import com.hb.acadia.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	@Transactional
	public void createCategory(String categoryName) {
	 Category category = Category.builder().name(categoryName).build();
	 categoryRepository.save(category);
	 
	}

}
