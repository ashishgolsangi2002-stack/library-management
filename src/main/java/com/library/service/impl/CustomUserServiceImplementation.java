package com.library.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.modal.User;
import com.library.repository.UserRepository;

@Service
public class CustomUserServiceImplementation implements UserDetailsService {
private final UserRepository userRepository;
	
	public CustomUserServiceImplementation(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	/
	
	@Override
public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
	User user=userRepository.findByEmail(username);
	
	if(user==null) {
		throw new UsernameNotFoundException("user not exists with username" + username);
	}
	
	GrantedAuthority authority=new SimpleGrantedAuthority(user.getRole().toString()); //later create enum for role
	
	Collection<? extends GrantedAuthority> authorities= Collections.singletonList(authority);
	
	return new  org.springframework.security.core.userdetails.User(
			user.getEmail(), user.getPassword(), authorities
			);
			
	
}
}
	
	
	
