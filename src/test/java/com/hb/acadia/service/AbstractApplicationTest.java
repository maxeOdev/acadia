package com.hb.acadia.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hb.acadia.repository.BillRepository;
import com.hb.acadia.repository.CategoryRepository;
import com.hb.acadia.repository.TrainingRepository;
import com.hb.acadia.repository.UserRepository;
import com.hb.acadia.repository.VideoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AbstractApplicationTest {

	//DI of Services
	@Autowired
	protected CategoryService categoryService;
	
	@Autowired
	protected VideoService videoService;

}
