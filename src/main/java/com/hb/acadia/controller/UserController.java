package com.hb.acadia.controller;

import com.hb.acadia.DTO.AddressDTO;
import com.hb.acadia.DTO.UserDTO;
import com.hb.acadia.constant.Mode;
import com.hb.acadia.model.Address;
import com.hb.acadia.model.user.User;
import com.hb.acadia.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;


/**
 * Controller user fonctions
 */
@Controller
@RequestMapping(value = "/admin")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/users")
    public ModelAndView listUsers(@RequestParam(value = "page", required = false) String page) {
        ModelAndView modelAndView = new ModelAndView("users");
        //Default value
        int pageNumber = 0;
        if (page != null) {
            pageNumber = Integer.parseInt(page);
        }
        //Default value
        int pageSize = 1;

        Page<User> pageUser = userService.findAll(new PageRequest(pageNumber, pageSize));
        List<User> users = pageUser.getContent();

        modelAndView.addObject("numberOfPages", pageUser.getTotalPages());
        modelAndView.addObject("actualPage", pageNumber);
        modelAndView.addObject("users", users);
        return modelAndView;
    }

    /**
     * Search a user by email, nom or firstname
     */
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public ModelAndView users(@RequestParam("search") String search) {
        List<User> users = users = new LinkedList<>();
        ModelAndView modelAndView = new ModelAndView();

        if (search == null) {
            return new ModelAndView("redirect:/admin/users");
        }

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

    /**
     * Temporary controller for developpement
     *
     * @return
     */
    @RequestMapping(value = "/temp", method = RequestMethod.GET)
    public ModelAndView user() {
        return new ModelAndView("temp");
    }

    /**
     * Display detail about a user
     *
     * @param uuid
     * @return
     */
    @RequestMapping(value = "/userDetail", method = RequestMethod.GET)
    public ModelAndView user(@RequestParam("uuid") String uuid) {

        if (uuid == null) {
            return new ModelAndView("redirect:/admin/users");
        }

        ModelAndView modelAndView = new ModelAndView("userDetail");
        User user = userService.getUserByUuid(uuid);
        if (user == null) {
            return new ModelAndView("redirect:/admin/users");
        }
        modelAndView.addObject("user", user);
        modelAndView.addObject("mode", Mode.DISPLAY_DETAIL.getName());
        return modelAndView;
    }

    /**
     * Return a form to update a user
     *
     * @param uuid
     * @return
     */
    @GetMapping(value = "/userUpdate")
    public ModelAndView userUpdate(@RequestParam("uuid") String uuid) {
        ModelAndView modelAndView = new ModelAndView("userDetail");
        User user = userService.getUserByUuid(uuid);

        UserDTO userDTO = new UserDTO();
        AddressDTO addressDTO = new AddressDTO();
        BeanUtils.copyProperties(user.getAddress(), addressDTO);
        userDTO.setAddress(addressDTO);
        BeanUtils.copyProperties(user, userDTO);


        modelAndView.addObject("user", userDTO);
        modelAndView.addObject("mode", Mode.UPDATE.getName());
        return modelAndView;

    }

    /**
     * Update a user
     *
     * @param userDTO
     * @return
     */
    @PostMapping(value = "/userUpdate")
    public ModelAndView userUpdatePost(@Valid UserDTO userDTO) {
        ModelAndView modelAndView = new ModelAndView();
        User user = userService.getUserByUuid(userDTO.getUuid());

        //Copy properties
        BeanUtils.copyProperties(userDTO.getAddress(), user.getAddress());
        BeanUtils.copyProperties(userDTO, user);
        //Update user
        userService.updateUser(user);

        modelAndView.setViewName("redirect:/admin/userDetail?uuid=" + user.getUuid());
        return modelAndView;
    }

    /**
     * Delete a user
     *
     * @param uuid
     * @return
     */
    @PostMapping(value = "/userDelete")
    public ModelAndView userDelete(@RequestParam("uuid") String uuid) {
        userService.deleteUser(userService.getUserByUuid(uuid));
        return new ModelAndView("redirect:/admin/users");

    }

    /**
     * Return a form to create a user
     *
     * @return
     */
    @GetMapping(value = "/userCreate")
    public ModelAndView userCreate() {
        ModelAndView modelAndView = new ModelAndView("userDetail").addObject("mode", Mode.CREATE.getName());
        User user = new User();
        user.setAddress(new Address());
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    /**
     * Create the user
     *
     * @param user
     * @return
     */
    @PostMapping(value = "/userCreate")
    public ModelAndView userCreatePost(@Valid User user) {
ModelAndView modelAndView = new ModelAndView();

        User userVerification = userService.getUserByEmail(user.getEmail());
        if (userVerification == null) {
            userService.createUser(user);
            modelAndView.setViewName("redirect:/admin/users");
            return modelAndView;
        } else {
            return null;
        }


    }
}
