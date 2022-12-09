package com.co.andresfot.libreria;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Bean //autorización
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/libros/lista-libros", "/css/**", "/js/**", "/images/**")
			.permitAll()
		.antMatchers("/libros/crear-libro").hasAnyRole("ADMIN")
		.antMatchers("/libros/editar-libro/**").hasAnyRole("ADMIN")
		.antMatchers("/libros/eliminar-libro/**").hasAnyRole("ADMIN")
		.antMatchers("/autores/**").hasAnyRole("ADMIN")
		.antMatchers("/prestamos/**").hasAnyRole("ADMIN")
		.antMatchers("/usuarios/**").hasAnyRole("ADMIN")
		.anyRequest().authenticated()
		.and()
			.formLogin().loginPage("/login").
			permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
        	
        return http.build();
    }

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

