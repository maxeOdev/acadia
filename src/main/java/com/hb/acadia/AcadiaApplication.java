package com.hb.acadia;

import com.hb.acadia.model.user.Role;
import com.hb.acadia.repository.RoleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;



@SpringBootApplication
@Slf4j
public class AcadiaApplication {

	@Autowired
	private RoleRepository roleRepository;

	public static void main(String[] args) {
		SpringApplication.run(AcadiaApplication.class, args);
	}

	//save Roles in db
	@EventListener(ApplicationReadyEvent.class)
	public void saveRolesAfterStartup() {
		Role customerRole = new Role();
		customerRole.setRoleName("ROLE_CUSTOMER");
		Role adminRole = new Role();
		adminRole.setRoleName("ROLE_ADMIN");
		roleRepository.save(customerRole);
		roleRepository.save(adminRole);
		log.info("");
		log.info("****** Roles saved in DB ******");
		log.info("");

	}
}



