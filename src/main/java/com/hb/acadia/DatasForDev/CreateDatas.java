package com.hb.acadia.DatasForDev;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.Category;
import com.hb.acadia.model.user.Role;
import com.hb.acadia.model.user.Trainer;
import com.hb.acadia.model.user.User;
import com.hb.acadia.repository.RoleRepository;
import com.hb.acadia.service.CategoryService;
import com.hb.acadia.service.TrainerService;
import com.hb.acadia.service.UserService;
import com.hb.acadia.service.VideoService;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
 * Classe permettant de créer des jeux de données pour le développement
 */
@Slf4j
@Component
public class CreateDatas {

    @Autowired
    private TrainerService trainerService;

    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private VideoService videoService;

    public void createRoles(){
        Role customerRole = new Role();
        customerRole.setRoleName("ROLE_CUSTOMER");
        Role adminRole = new Role();
        adminRole.setRoleName("ROLE_ADMIN");
        Role trainerRole = new Role();
        trainerRole.setRoleName("ROLE_TRAINER");
        roleRepository.save(trainerRole);
        roleRepository.save(customerRole);
        roleRepository.save(adminRole);
        log.info("");
        log.info("****** Roles saved in DB ******");
        log.info("");
    }

    public void createTrainers() {

        Trainer t1 = new Trainer();

        Address addressTrainer = new Address();
        addressTrainer.setCity("Adresse trainer");
        addressTrainer.setCountry("France");
        addressTrainer.setNumber(34);
        addressTrainer.setRoad("Du chateau");
        addressTrainer.setCp("35876");
        addressTrainer.setRoadType("Avenue");


        t1.setEmail("trainer@gmail.com");
        t1.setFirstName("max");
        t1.setName("well");
        t1.setPassword("max");
        t1.setActif(true);
        t1.setAddress(addressTrainer);

        t1.setExperience(8);
        t1.setIdStripe("11111");
        t1.setQualifications("master");

        trainerService.createTrainer(t1);


        Trainer t2 = new Trainer();

        Address addressTrainer2 = new Address();
        addressTrainer2.setCity("Adresse trainer");
        addressTrainer2.setCountry("France");
        addressTrainer2.setNumber(34);
        addressTrainer2.setRoad("Du chateau");
        addressTrainer2.setCp("35876");
        addressTrainer2.setRoadType("Avenue");


        t2.setEmail("trainer2@gmail.com");
        t2.setFirstName("Bob");
        t2.setName("Dylan");
        t2.setPassword("max");
        t2.setActif(false);
        t2.setAddress(addressTrainer2);

        t2.setExperience(8);
        t2.setIdStripe("11111");
        t2.setQualifications("master");

        trainerService.createTrainer(t2);
    }

    public void createUsers() {

        Address address = new Address();
        address.setCity("Cailloux-sur-Fontaine");
        address.setCountry("France");
        address.setNumber(34);
        address.setRoad("Du chateau");
        address.setCp("35876");
        address.setRoadType("Avenue");


        User simone = new User();
        simone.setEmail("sim.aliotti@gmail.com");
        simone.setFirstName("simon");
        simone.setName("Aliotti");
        simone.setPassword("toto");
        simone.setActif(true);
        simone.setAddress(address);
        userService.createUser(simone);

        Address address2 = new Address();
        address2.setCity("Cailloux-sur-Fontaine");
        address2.setCountry("France");
        address2.setNumber(34);
        address2.setRoad("Du chateau");
        address2.setCp("35876");
        address2.setRoadType("Avenue");


        User simone2 = new User();
        simone2.setEmail("anis@gmail.com");
        simone2.setFirstName("Anis");
        simone2.setName("Lalami");
        simone2.setPassword("anis");
        simone2.setActif(true);
        simone2.setAddress(address2);
        userService.createUser(simone2);
    }

    public void createCategory() {
        categoryService.createCategory(new Category("informatique"));
        categoryService.createCategory(new Category("gestion"));
        categoryService.createCategory(new Category("bricolage"));
    }

    public void createVideos() {
    	
    }
    
}

