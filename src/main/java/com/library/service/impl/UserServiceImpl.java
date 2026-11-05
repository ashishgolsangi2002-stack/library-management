package com.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.library.mapper.UserMapper;
import com.library.modal.User;
import com.library.payload.dto.UserDTO;
import com.library.repository.UserRepository;
import com.library.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@RequestMapping
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	@Override
	public User getCurrentUser() throws Exception {
		// TODO Auto-generated method stub
		String email=SecurityContextHolder.getContext().getAuthentication().getName();
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new Exception("user not found");
		}
		return user;
	}

	@Override
	public List<UserDTO> getAllUsers() {
		// TODO Auto-generated method stub
		List<User> users= userRepository.findAll();
		
		return users.stream().map (
				UserMapper::DTO
				).collect(Collectors.toList());

}
}