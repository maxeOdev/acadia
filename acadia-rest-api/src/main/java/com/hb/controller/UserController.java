package com.hb.controller;


import com.hb.dto.UserDTO;
import com.hb.exception.ResourceNotFoundException;
import com.hb.model.user.User;
import com.hb.repository.UserRepository;
import com.hb.security.CurrentUser;
import com.hb.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal currentUser) {
        User user = userRepository.findById(currentUser.getId()).get();
        return user;
    }

    /**
     *
     * @param userUuid
     * @return return a userDto from the uuid given
     */
    @GetMapping("/users/{userUuid}")
    public UserDTO getUserProfile(@PathVariable(value = "userUuid") String userUuid) {
        User user = userRepository.findByUuid(userUuid);
        if (!(user == null)) {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(user, userDTO);
            return userDTO;
        }
        throw new ResourceNotFoundException("User", "uuid", userUuid);
    }

}
