package com.hb.controller;


import com.hb.configuration.CustombCryptPasswordEncoder;
import com.hb.payload.JwtAuthenticationResponse;
import com.hb.payload.LoginRequest;
import com.hb.repository.RoleRepository;
import com.hb.repository.UserRepository;
import com.hb.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
@Validated
@Slf4j
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private CustombCryptPasswordEncoder custombCryptPasswordEncoder;

    @RequestMapping(value = "/signin", method = RequestMethod.POST)
    public ResponseEntity<?> signin(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authenticate = null;

        try {
            authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                    (loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            log.info("***********************************************");
            log.info("Authentication error : " + e);
            log.info("***********************************************");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Erreur lors de l'authentification, login/password inconnu(s)");

        }

        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String jwt = jwtTokenProvider.generateToken(authenticate);
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
//        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
//            return new ResponseEntity(new ApiResponse(false, "Username is already taken!"),
//                    HttpStatus.BAD_REQUEST);
//        }
//
//        // Creating user's account
//        User user = new User(signUpRequest.getFirstName(), signUpRequest.getLastName(),
//                signUpRequest.getEmail(), signUpRequest.getPassword());
//
//        user.setPassword(custombCryptPasswordEncoder.encode(user.getPassword()));
//
//        Role userRole = roleRepository.findByRole("ROLE_USER")
//                .orElseThrow(() -> new AppException("User Role not set."));
//
//        user.setRoles(Collections.singleton(userRole));
//
//        User result = userRepository.save(user);
//
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/users/{userId}")
//                .buildAndExpand(result.getId()).toUri();
//
//        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
//    }
}
