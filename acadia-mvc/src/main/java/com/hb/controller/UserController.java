package com.hb.controller;

import com.hb.constant.MessageError;
import com.hb.constant.Mode;
import com.hb.dto.AddressDTO;
import com.hb.dto.UserDTO;
import com.hb.model.Address;
import com.hb.model.user.User;
import com.hb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;


/**
 * Controller user functions
 */

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping(value = "/users")
    public ModelAndView users(@RequestParam(value = "page", required = false) String page) {

        ModelAndView modelAndView = new ModelAndView("users");

        //Default value
        int pageNumber = 0;

        if (page != null) {
            try {
                pageNumber = Integer.parseInt(page);
            } catch (NumberFormatException e) {
                return new ModelAndView("redirect:/admin/users");
            }
        }
        //Default value
        int pageSize = 10;

        //Get page from db
        Page<User> pageUser = null;
        try {
            pageUser = userService.findAll(new PageRequest(pageNumber, pageSize));
        } catch (Exception e) {
            return new ModelAndView("redirect:/admin/users");
        }

        List<User> users = pageUser.getContent();

        modelAndView.addObject("numberOfPages", pageUser.getTotalPages());
        modelAndView.addObject("actualPage", pageNumber);
        modelAndView.addObject("users", users);
        modelAndView.addObject("mode", Mode.DISPLAY_ALL_USERS.getName());
        return modelAndView;
    }


    /**
     * Search a user by email, nom or firstname
     */
    @RequestMapping(value = "/users-research", method = RequestMethod.GET)
    public ModelAndView searchUsers(@RequestParam(value = "search", required = false) String search, @RequestParam(value = "page", required = false) String page) {
        List<User> users = new LinkedList<>();
        Page pageUser = null;
        ModelAndView modelAndView = new ModelAndView();

        //Check if search content is null
        if (search == null) {
            return new ModelAndView("redirect:/admin/users");
        }

        //Default value
        int pageNumber = 0;

        if (page != null) {
            try {
                pageNumber = Integer.parseInt(page);
            } catch (NumberFormatException e) {
                return new ModelAndView("redirect:/admin/users");
            }
        }
        //Default value
        int pageSize = 10;

        try {
            pageUser = userService.findUsersByResearch(search, new PageRequest(pageNumber, pageSize));
            users = pageUser.getContent();
        } catch (Exception e) {
            log.error("Impossible de récupérer les informations - search = " + search);
        }

        if (!users.isEmpty()) {

            modelAndView.addObject("numberOfPages", pageUser.getTotalPages());
            modelAndView.addObject("actualPage", pageNumber);
            modelAndView.addObject("users", users);

            modelAndView.setViewName("users");
            modelAndView.addObject("search", search);
            modelAndView.addObject("mode", Mode.DISPLAY_SEARCH_RESULT.getName());
            return modelAndView;

        } else {
            return new ModelAndView("redirect:/admin/users");
        }


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

        ModelAndView modelAndView = new ModelAndView("userDetail");

        if (uuid == null) {
            return new ModelAndView("redirect:/admin/users");
        }

        User user = null;
        try {
            user = userService.getUserByUuid(uuid);
        } catch (Exception e) {
            log.error(MessageError.ERROR_CONNECTION_DATABASE.getMessage());
        }


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
        User user = null;

        try {
            user = userService.getUserByUuid(uuid);
        } catch (Exception e) {
            log.error(MessageError.ERROR_CONNECTION_DATABASE.getMessage());
        }
        if (user != null) {
            UserDTO userDTO = new UserDTO();
            AddressDTO addressDTO = new AddressDTO();
            BeanUtils.copyProperties(user.getAddress(), addressDTO);
            userDTO.setAddress(addressDTO);
            BeanUtils.copyProperties(user, userDTO);

            modelAndView.addObject("user", userDTO);
            modelAndView.addObject("mode", Mode.UPDATE.getName());
            return modelAndView;
        } else {
            modelAndView.setViewName("redirect:/admin/users");
            return modelAndView;
        }


    }

    /**
     * Update a user
     *
     * @param userDTO
     * @return
     */
    @PostMapping(value = "/userUpdate")
    public ModelAndView userUpdatePost(@Valid @ModelAttribute("user") UserDTO userDTO, BindingResult errors) {

        ModelAndView modelAndView = new ModelAndView();
        //Verifications
        if (errors.hasErrors()) {
            modelAndView.addObject("mode", Mode.UPDATE.getName());
            modelAndView.setViewName("userDetail");
            return modelAndView;
        }

        //Check if email is available
        User userSearch = null;
        try {
            userSearch = userService.getUserByEmail(userDTO.getEmail());
        } catch (Exception e) {
            log.error(MessageError.ERROR_CONNECTION_DATABASE.getMessage());
            modelAndView.setViewName("redirect:/admin/users");
        }

        if (userSearch == null || userSearch.getEmail().equals(userDTO.getEmail())) {
            User user = null;

            try {
                user = userService.getUserByUuid(userDTO.getUuid());
            } catch (Exception e) {
                log.error(MessageError.ERROR_CONNECTION_DATABASE.getMessage());
                modelAndView.setViewName("redirect:/admin/users");
            }

            BeanUtils.copyProperties(userDTO.getAddress(), user.getAddress());
            BeanUtils.copyProperties(userDTO, user);

            //Update user
            userService.updateUser(user);

            modelAndView.setViewName("redirect:/admin/userDetail?uuid=" + user.getUuid());
            return modelAndView;

        } else {
            modelAndView.addObject("mode", Mode.UPDATE.getName());
            modelAndView.setViewName("userDetail");
            errors.rejectValue("email", "error.user", "Cette adresse e-mail n'est pas " +
                    "disponilbe");
            return modelAndView;
        }
    }

    /**
     * Delete a user
     *
     * @param uuid
     * @return
     */
    @ResponseBody
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping(value = "/user-delete")
    public void userDelete(@RequestParam("uuid") String uuid) {
        userService.deleteUser(userService.getUserByUuid(uuid));

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
    public ModelAndView userCreatePost(@Valid User user, BindingResult errors) {
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
