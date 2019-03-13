package com.hb.acadia.controller;

import com.hb.acadia.constante.Mode;
import com.hb.acadia.model.user.User;
import com.hb.acadia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView users(@RequestParam("search") String search) {
        List<User> users =  users = new LinkedList<>();
        ModelAndView modelAndView = new ModelAndView();

        //Check if research is an email or a name or a firstname
        if (search.contains("@")) {
            User user = userService.getUserByEmail(search);
            users.add(user);
            modelAndView.addObject("users", users);

        } else if (!(users = userService.getUserByName(search)).isEmpty()) {
            modelAndView.addObject("users", users);

        } else if (!(users = userService.getUserByFirstName(search)).isEmpty()) {
            modelAndView.addObject("users", users);
        } else {
            modelAndView.addObject("users", users);
        }
        modelAndView.setViewName("users");
        return modelAndView;
    }

    @RequestMapping(value = "/temp", method = RequestMethod.GET)
    public ModelAndView user(){
        return new ModelAndView("temp");
    }

    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public ModelAndView user(@RequestParam("uuid") String uuid) {
        ModelAndView modelAndView = new ModelAndView("userDetail");
        User user = userService.getUserByUuid(uuid);
        modelAndView.addObject("user", user);
        modelAndView.addObject("mode", Mode.DISPLAY_DETAIL.getName());
        return modelAndView;
    }

    @GetMapping(value = "/userUpdate")
    public ModelAndView userUpdate(@RequestParam("uuid")String uuid){
        ModelAndView modelAndView = new ModelAndView("userDetail");
        User user = userService.getUserByUuid(uuid);
        modelAndView.addObject("userUpdated", user);
        modelAndView.addObject("mode", Mode.UPDATE.getName());
        return modelAndView;
    }


}
