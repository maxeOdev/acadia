package com.hb.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class Utils {

	@Value("${saltkey}")
	public static String saltKey;
	
	public static String encryptAndSalt(String password) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.encode(saltKey+password+saltKey);
	}
	public static boolean verifyPassword(String password, String encodedPassword) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder.matches(saltKey+password+saltKey, encodedPassword);
	}
	
}
