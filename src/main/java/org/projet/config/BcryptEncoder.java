package org.projet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCrypt;

import lombok.Data;

@Configuration
@Data
public class BcryptEncoder extends BCrypt {

	
	private String password;
	private String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(password, 10));
	private String salt = "Dobby2022";
	
	@Bean
	public void checkPassword(String password) {
		if(BCrypt.checkpw(password, hashedPassword)) {
			System.out.println("passwords match");
		} else {
			System.out.println("passwords do not match");
		}
	}
	
	@Bean
	public String encodePassword(String psw) {
		return hashedPassword = BCrypt.hashpw(psw, BCrypt.gensalt(salt, 10));
	}
	
}
