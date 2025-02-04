package com.cts.LibraryManagementSystem.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService(DaoAuthenticationProvider auth) {
		return new MyUserDetailsService();
	}
	
	@Bean
	public SecurityFilterChain securityfilterChain(HttpSecurity http) throws Exception{
		return http.csrf().disable()
						.authorizeHttpRequests(auth -> auth
						.requestMatchers("/users/**").permitAll()
						.requestMatchers("/admin/**").hasRole("ADMIN")
						.requestMatchers("/user/**").hasRole("USER")
						.requestMatchers("/books/**","/borrow-records/**").hasAnyRole("USER","ADMIN")
						.anyRequest().authenticated())
						.formLogin(login -> login
								.usernameParameter("userName")
								.passwordParameter("password")
								.loginPage("/users/login")
								.defaultSuccessUrl("/dashboard",true)
								.permitAll())
						.logout(logout -> logout
								.logoutUrl("/users/logout")
								.clearAuthentication(true)
								.invalidateHttpSession(true))
						.sessionManagement()
						.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
						.and()
						.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService) {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(userDetailsService);;
		auth.setPasswordEncoder(passwordEncoder());
		return auth;
	}
	
	@Bean
	public AuthenticationManager authManager(AuthenticationConfiguration auth) throws Exception {
		return auth.getAuthenticationManager();
	}
}
