package com.amagana.securityservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

	private final JwtConverter jwtConverter;
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		  .csrf(AbstractHttpConfigurer::disable)
		  .authorizeHttpRequests(((authorizeHttpRequests)-> 
		       authorizeHttpRequests
		       .anyRequest().authenticated()
				  ))
		  .oauth2ResourceServer((oauth2ResourceServer) ->
 				oauth2ResourceServer.jwt(jwt -> jwt.jwtAuthenticationConverter(jwtConverter)
 			));
		  return http.build();
	}
}
