package com.version1.CustomerApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DBInitialiser {
//	@Autowired
//	private UserRepository userRepository;
//	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
//	
//	@Bean
//	public CommandLineRunner initialise() {
//		return (args)->{
//			User user = new User();
//			user.setUsername("user3");
//			user.setPassword(passwordEncoder.encode("password"));
//			user.setActive(true);
//			user.setRole("ADMIN");
//			userRepository.save(user);
//		};
//	}
}
