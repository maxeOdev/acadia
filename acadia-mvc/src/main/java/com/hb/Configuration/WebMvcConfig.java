package com.hb.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

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