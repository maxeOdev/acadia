package com.hb.service;

import com.hb.model.Category;
import lombok.extern.slf4j.Slf4j;
import org.junit.*;

import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertThat;

@Slf4j
public class CategoryServiceTest extends AbstractApplicationTest {

	private Category category;
	
	@BeforeClass
	public static void startingTest() {
		log.info("************************************ STARTING CATEGORY TEST ***************************************");
	}

	@AfterClass
	public static void endingTest() {
		log.info("************************************ DELETING DATAS ***************************************");
	}

	@Before
	public void onLaunch() {
		
		category = categoryService.createCategory(new Category("Informatique"));
		
	}
	
	@After
	public void onExit() {
		
		categoryService.deleteAllCategories();
		
	}
	
	@Test
	public void create() {
		
		String name = "Bricolage";
		Category cat = categoryService.createCategory(new Category(name));
		
		assertThat(name.toLowerCase(), equalTo(cat.getName()));
		assertThat(cat.getId(), not(0));
		assertThat(cat.getTrainings(), nullValue());
		
	}

	@Test
	public void allCategories() {
		
		Category cat1 = categoryService.createCategory(new Category("testName"));
		List<Category> cats = categoryService.getAllCategories();
		
		assertThat(cats.size(), equalTo(2));
		assertThat(cats.get(0), equalTo(category));
		assertThat(cats.get(1), equalTo(cat1));
		
	}
	
	@Test
	public void byName() {
		
		assertThat(categoryService.getByName(category.getName()), equalTo(category));
		
	}
	
	@Test
	public void count() {
	
		assertThat(categoryService.countCategories(), equalTo(1L));
		categoryService.createCategory(new Category("testName"));
		assertThat(categoryService.countCategories(), equalTo(2L));
		
	}
	
	@Test
	public void update() {
		
		Category cat = categoryService.getByName(category.getName());
		String name1 = cat.getName();
		cat.setName(cat.getName()+"Changeeees");
		cat = categoryService.updateCategory(cat);
		
		assertThat(cat.getName(), not(equalTo(name1)));
		assertThat(categoryService.countCategories(), equalTo(1L));
		
	}
	
	@Test
	public void delete() {
		
		assertThat(categoryService.countCategories(), equalTo(1L));
		categoryService.deleteCategory(category);
		assertThat(categoryService.countCategories(), equalTo(0L));
		
	}
	
	@Test
	public void deleteAllCategories() {
		
		categoryService.createCategory(new Category("testCategory"));
		assertThat(categoryService.countCategories(), equalTo(2L));
		categoryService.deleteAllCategories();
		assertThat(categoryService.countCategories(), equalTo(0L));
		
	}
	
}
