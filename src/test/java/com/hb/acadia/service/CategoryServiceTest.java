package com.hb.acadia.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import com.hb.acadia.model.Category;

public class CategoryServiceTest extends AbstractApplicationTest {

	@Test
	public void test_createCategory() {

		String categoryName = "Java";
		Category category = categoryService.createCategory(categoryName);

		// assertions
		assertThat(category.getId(), notNullValue());
		assertEquals(1, categoryRepository.count());
		assertEquals(categoryName, category.getName());
	}

	@Test
	public void test2_createCategory() {

		String categoryName = "C++";
		Category category = categoryService.createCategory(categoryName);

		// assertions
		assertThat(category.getId(), notNullValue());
		assertEquals(2, categoryRepository.count());
		assertEquals(categoryName, category.getName());
	}
}
