package com.hb.acadia.configuration;

import org.aspectj.weaver.bcel.BcelAccessForInlineMunger;
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
	public BCryptPasswordEncoder passwrodEncoderBcrypt(){
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}



}