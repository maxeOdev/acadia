package com.hb.Controller;


import com.hb.Model.user.Trainer;
import com.hb.Service.TrainerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller trainer functions
 */

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class TrainerController {

    @Autowired
    private TrainerService trainerService;


    /*tests*/
    @GetMapping(value = "/trainers-index")
    public ModelAndView getTrainers() {

        return new ModelAndView("trainer/files");
    }

    @GetMapping(value = "trainers-content")
    public ModelAndView getTrainersContent() {
        return new ModelAndView("trainer/files-content");
    }
    /*tests*/

    /**
     * @return a ModelAndView
     */
    @GetMapping(value = "/trainers")
    public ModelAndView trainerMain() {
        return new ModelAndView("trainers");
    }


    @ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping(value = "/trainers-list")
    public Page<Trainer> listTrainers(@RequestParam(value = "pageNumber", required = false)String pageNumber) {
        int pageNumberInt = 0;
        if(pageNumber==null){
            try {
               pageNumberInt = Integer.parseInt(pageNumber);
            } catch (NumberFormatException e){
                //
            }

        }
        Page<Trainer> trainers = trainerService.findAllPage(new PageRequest(pageNumberInt, 10));
        return trainers;
    }

    @ResponseBody
    @GetMapping(value = "/trainers-template-list")
    public ModelAndView getTrainersListTemplate(){
        return new ModelAndView("/fragments/trainer/list-trainers");
    }
}





