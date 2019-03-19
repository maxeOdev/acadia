package com.hb.acadia.controller;


import com.hb.acadia.model.user.Trainer;
import com.hb.acadia.service.TrainerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Controller trainer functions
 */

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;


    @GetMapping(value = "/trainers-index")
    public ModelAndView getTrainers() {

        return new ModelAndView("trainer/files");
    }

    @GetMapping(value = "trainers-content")
    public ModelAndView getTrainersContent() {
        return new ModelAndView("trainer/files-content");
    }

    }

