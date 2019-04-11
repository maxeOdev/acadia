package com.hb.controller;


import com.hb.Payload.JwtAuthenticationResponse;
import com.hb.Payload.LoginRequest;
import com.hb.Security.JwtTokenProvider;
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


}
