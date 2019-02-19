package com.hb.acadia.service;

<<<<<<< HEAD
=======
import static org.assertj.core.api.Assertions.assertThat;
>>>>>>> 8964e946f697309e68672e8362170ca65c796276
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

<<<<<<< HEAD
import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.hb.acadia.model.Category;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CategoryServiceTest extends AbstractApplicationTest {

	@BeforeClass
	public static void startTest() {
		log.info("************************************ STARTING CATEGORY TEST ***************************************");
	}

	@After
	public void deleteDataAfterTest() {
		log.info("************************************ DELETING DATAS ***************************************");
		categoryRepository.deleteAll();
	}

	public void create2categories() {
		String categoryName = "Java";
		Category category = categoryService.createCategory(categoryName);

		String categoryName2 = "C++";
		Category category2 = categoryService.createCategory(categoryName2);
	}

=======
import org.junit.Test;
import com.hb.acadia.model.Category;

public class CategoryServiceTest extends AbstractApplicationTest {

>>>>>>> 8964e946f697309e68672e8362170ca65c796276
	@Test
	public void test_createCategory() {

		String categoryName = "Java";
		Category category = categoryService.createCategory(categoryName);

<<<<<<< HEAD
		String categoryName2 = "C++";
		Category category2 = categoryService.createCategory(categoryName2);

		// assertions
		assertThat(category.getId(), notNullValue());
		assertEquals(categoryName, category.getName());
		assertThat(category2.getId(), notNullValue());
		assertEquals(categoryName2, category2.getName());
		assertEquals(2, categoryRepository.count());
	}

	@Test
	public void test_findAllCategories() {
		create2categories();
		List<Category> categories = categoryService.findAllCategories();

		// assertions
		assertEquals(2, categories.size());
		assertEquals("Java", categories.get(0).getName());
		assertEquals("C++", categories.get(1).getName());
		assertThat(categories.get(0).getId(), notNullValue());
		assertThat(categories.get(1).getId(), notNullValue());
	}

=======
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
>>>>>>> 8964e946f697309e68672e8362170ca65c796276
}
