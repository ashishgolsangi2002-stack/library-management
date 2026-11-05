package com.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.modal.User;
import com.library.payload.dto.UserDTO;
import com.library.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
private final UserService userService;

@GetMapping("/list")
public ResponseEntity<List<UserDTO>> getAllUsers(){
	return new ResponseEntity.ok(
			userService.getAllUsers()
			);
}

@GetMapping("/list")
public ResponseEntity <User> getAllUsers() throw Exception{
	return new ResponseEntity.ok(
			userService.getAllUsers()
			);
}

}
