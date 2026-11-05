package com.library.service;

import java.util.List;

import com.library.modal.User;
import com.library.payload.dto.UserDTO;

//Logged in user can fetch his profile
public interface UserService {

	public User getCurrentUser() throws Exception;
	public List<UserDTO> getAllUsers();
	
}
