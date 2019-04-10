package com.hb.Service;

import com.hb.Repository.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
	@Autowired
	protected VideoRepository videoRepository;

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
	@Autowired
	protected TrainingService trainingService;

}
