package com.hb.controller;


import com.hb.constant.MessageError;
import com.hb.dto.AddressDTO;
import com.hb.dto.UserDTO;
import com.hb.dto.UserUpdateRequest;
import com.hb.exception.ResourceNotFoundException;
import com.hb.model.Address;
import com.hb.model.user.User;
import com.hb.payload.ApiResponse;
import com.hb.repository.UserRepository;
import com.hb.security.CurrentUser;
import com.hb.security.UserPrincipal;
import com.hb.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/users/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findById(currentUser.getId()).get();
        return user;
    }

    /**
     * Get a user by uuid
     *
     * @param userUuid
     * @return return a userDto
     */
    @GetMapping("/users/{userUuid}")
    public UserDTO getUserProfile(@PathVariable(value = "userUuid") String userUuid) {

        User user = userRepository.findByUuid(userUuid);
        if (!(user == null)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setAddress(new AddressDTO());
            BeanUtils.copyProperties(user, userDTO);
            BeanUtils.copyProperties(user.getAddress(), userDTO.getAddress());
            return userDTO;
        }
        throw new ResourceNotFoundException("User", "uuid", userUuid);
    }

    /**
     * Get all users
     *
     * @return a list of userDto
     */
    @GetMapping("/users")
    //@PreAuthorize("hasRole('CUSTOMER')")
    public List<UserDTO> getUsersProfile() {

        List<User> users = userService.findAll();
        List<UserDTO> usersDTO = new LinkedList<>();

        if (!users.isEmpty()) {
            users.forEach(user -> {
                UserDTO userDTO = new UserDTO();
                userDTO.setAddress(new AddressDTO());
                BeanUtils.copyProperties(user, userDTO);
                if (!(user.getAddress() == null)) {
                    BeanUtils.copyProperties(user.getAddress(), userDTO.getAddress());
                }
                usersDTO.add(userDTO);
            });
        }
        return usersDTO;
    }

//    /**
//     * Update a user
//     *
//     * @param userUpdateRequest
//     * @return a response entity
//     */
//    @PutMapping("/users")
//    @PreAuthorize("hasRole('CUSTOMER')")
//    public ResponseEntity<?> updateUser(@Valid @RequestBody UserUpdateRequest userUpdateRequest) {
//
//        //The connected user
//        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        //Check if email is available
//        User userVerification = null;
//        try {
//            userVerification = userService.getUserByEmail(userUpdateRequest.getEmail());
//        } catch (Exception e) {
//            log.error(MessageError.ERROR_CONNECTION_DATABASE.getMessage());
//            return new ResponseEntity(new ApiResponse(false, "DB connection error"),
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//
//
//        if (userVerification == null || userPrincipal.getEmail().equals(userUpdateRequest.getEmail())) {
//            User user = null;
//
//            try {
//                user = userService.getUserByEmail(userPrincipal.getEmail());
//            } catch (Exception e) {
//                log.error(MessageError.ERROR_CONNECTION_DATABASE.getMessage());
//                return new ResponseEntity(new ApiResponse(false, "DB connection error"),
//                        HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//            //Get address
//            Address address = user.getAddress();
//            BeanUtils.copyProperties(userUpdateRequest, address);
//            BeanUtils.copyProperties(userUpdateRequest, user);
//            //Set address into user
//            user.setAddress(address);
//
//            //Update user
//            userService.updateUser(user);
//            return new ResponseEntity(new ApiResponse(true, "User update success"), HttpStatus.CREATED);
//        } else {
//            return new ResponseEntity(new ApiResponse(false, "User already exist"),
//                    HttpStatus.NOT_ACCEPTABLE);
//        }
//
//    }

    /**
     * Update a user méthode provisoire
     *
     * @param userDto
     * @return a response entity
     */
    @PutMapping("/users")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UserDTO userDTO) {



        //Check if email is available
        User userVerification = null;
        try {
            userVerification = userService.getUserByEmail(userDTO.getEmail());
        } catch (Exception e) {
            log.error(MessageError.ERROR_CONNECTION_DATABASE.getMessage());
            return new ResponseEntity(new ApiResponse(false, "DB connection error"),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }



            User user = null;

            try {
                user = userService.getUserByEmail(userDTO.getEmail());
            } catch (Exception e) {
                log.error(MessageError.ERROR_CONNECTION_DATABASE.getMessage());
                return new ResponseEntity(new ApiResponse(false, "DB connection error"),
                        HttpStatus.INTERNAL_SERVER_ERROR);
            }

            //Get address
            Address address = user.getAddress();
            BeanUtils.copyProperties(userDTO.getAddress(), address);
            BeanUtils.copyProperties(userDTO, user);
            //Set address into user
            user.setAddress(address);

            //Update user
            userService.updateUser(user);
            return new ResponseEntity(new ApiResponse(true, "User update success"), HttpStatus.CREATED);


    }


    /**
     * Delete User
     *
     * @param userUuid
     * @return EntityResponse
     */
    @DeleteMapping("/users/{userUuid}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "userUuid") String userUuid) {
        
        //The connected user
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.getUserByEmail(userPrincipal.getEmail());
        if (user.getUuid().equals(userUuid)) {
            userService.deleteUser(userService.getUserByUuid(userUuid));
            return new ResponseEntity(new ApiResponse(true, "User delete success"), HttpStatus.ACCEPTED);
        } else{
            return  new ResponseEntity(new ApiResponse(false, "User delete forbidden. Wrong token"), HttpStatus.FORBIDDEN);
        }

    }
}


