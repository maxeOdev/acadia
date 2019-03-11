package com.hb.acadia.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.hb.acadia.repository.AddressRepository;
import com.hb.acadia.repository.BillRepository;
import com.hb.acadia.repository.CategoryRepository;
import com.hb.acadia.repository.RoleRepository;
import com.hb.acadia.repository.TrainerRepository;
import com.hb.acadia.repository.TrainingRepository;
import com.hb.acadia.repository.UserRepository;
import com.hb.acadia.repository.VideoRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public abstract class AbstractApplicationTest {

	// DI of repositories
	@Autowired
	protected BillRepository billRepository;
	@Autowired
	protected CategoryRepository categoryRepository;
	@Autowired
	protected TrainingRepository trainingRepository;
	@Autowired
	protected UserRepository userRepository;
	@Autowired
	protected AddressRepository addressRepository;
	@Autowired
	protected RoleRepository roleRepository;
	@Autowired
	protected TrainerRepository trainerRepository;

	// DI of Services
	@Autowired
	protected CategoryService categoryService;
	@Autowired
	protected UserService userService;
	@Autowired
	protected RoleService roleService;
	@Autowired
	protected VideoService videoService;
	@Autowired
	protected AddressService addressService;
	@Autowired
	protected TrainerService trainerService;

}
