package com.hb.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class CustomBean {

    @Bean
    public CustombCryptPasswordEncoder passwordEncoder() {
        CustombCryptPasswordEncoder custombCryptPasswordEncoder = new CustombCryptPasswordEncoder();
        return custombCryptPasswordEncoder;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoderBcrypt(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        return bCryptPasswordEncoder;
    }
}
