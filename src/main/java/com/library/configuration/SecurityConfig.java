package com.library.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig{
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		return http
				.sessionManagement(management->management.sessionCreationPolicy(
						SessionCreationPolicy.STATELESS
						))
				.authorizeHttpRequests(Authorize->Authorize
				.requestMatchers("/api/**").authenticated()
				.requestMatchers("/api/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
						)
				.addFilterBefore(new JwtValidator(),BasicAuthenticationFilter.class)
				.csrf(AbstractHttpConfigurer::disable)
				.build();
	}
}