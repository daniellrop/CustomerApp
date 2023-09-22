package com.version1.CustomerApp;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
public class SecConfig {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(4);
	}
	 
	@Bean
	@CrossOrigin(origins = "http://127.0.0.1:5173")
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> auth
				.requestMatchers("/customers").hasAuthority("ADMIN")
				.requestMatchers("/customers/**").hasAuthority("ADMIN")
//				.requestMatchers("/css/**").authenticated()
				
		)
		.httpBasic(Customizer.withDefaults());
//		.formLogin((formLog) ->
//			formLog.loginPage("/login")
//		);
		
		return http.build();
	}
	
}
