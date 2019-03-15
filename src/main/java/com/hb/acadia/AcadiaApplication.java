package com.hb.acadia;

import com.hb.acadia.model.Address;
import com.hb.acadia.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import com.hb.acadia.model.Category;
import com.hb.acadia.model.user.Role;
import com.hb.acadia.repository.RoleRepository;
import com.hb.acadia.service.CategoryService;
import com.hb.acadia.service.UserService;

import lombok.extern.slf4j.Slf4j;


@SpringBootApplication
@Slf4j
public class AcadiaApplication {

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private CategoryService categoryService;

    @Value("${application.profile}")
    private String profile;

    public static void main(String[] args) {
        SpringApplication.run(AcadiaApplication.class, args);
    }

    //save Roles in db
    @EventListener(ApplicationReadyEvent.class)
    public void saveRolesAfterStartup() {

        if (profile.equals("local")) {

            Role customerRole = new Role();
            customerRole.setRoleName("ROLE_CUSTOMER");
            Role adminRole = new Role();
            adminRole.setRoleName("ROLE_ADMIN");
            roleRepository.save(customerRole);
            roleRepository.save(adminRole);
            log.info("");
            log.info("****** Roles saved in DB ******");
            log.info("");

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
            simone2.setEmail("claude.aliotti@gmail.com");
            simone2.setFirstName("claude");
            simone2.setName("Aliotti");
            simone2.setPassword("toto");
            simone2.setActif(true);
            simone2.setAddress(address2);
            userService.createUser(simone2);

//            categoryService.createCategory(new Category("informatique"));
//            categoryService.createCategory(new Category("bricolage"));
//            categoryService.createCategory(new Category("gestion"));
        }
    }
}



