package com.co.andresfot.libreria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Bean //configuration global
	public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
		
		List<UserDetails> userDetailsList = new ArrayList<>();
		userDetailsList.add(User.withUsername("admin").password(this.passwordEncoder.encode("password"))
				.roles("ADMIN", "USER").build());
		userDetailsList.add(User.withUsername("andres").password(this.passwordEncoder.encode("password"))
				.roles("USER").build());

		return new InMemoryUserDetailsManager(userDetailsList);
	}
	
}
