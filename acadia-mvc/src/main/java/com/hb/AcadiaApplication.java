package com.hb;

import com.hb.datasForDev.CreateDatas;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;


@SpringBootApplication
@Slf4j
public class AcadiaApplication {

    @Autowired
    private CreateDatas createDatas;

    @Value("${application.profile}")
    private String profile;

    public static void main(String[] args) {
        SpringApplication.run(AcadiaApplication.class, args);
    }

    //save Roles in db
    @EventListener(ApplicationReadyEvent.class)
    public void saveRolesAfterStartup() {

        if (profile.equals("local")) {

            //Create Roles
            createDatas.createRoles();

            //Create Users
            createDatas.createUsers();

            //Create trainer
            createDatas.createTrainers();

          //Create category
            createDatas.createCategory();

          //Create videos
            createDatas.createVideos();

        }
    }
}



