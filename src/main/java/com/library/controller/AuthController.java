package com.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.exception.UserException;
import com.library.payload.dto.UserDTO;
import com.library.payload.request.ForgotPasswordRequest;
import com.library.payload.request.LoginRequest;
import com.library.payload.request.ResetPasswordRequest;
import com.library.payload.response.ApiResponse;
import com.library.payload.response.AuthResponse;
import com.library.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> signupHandler(
			@RequestBody @Valid UserDTO req
			) throws UserException {
		AuthResponse res=authService.signup(req);
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthResponse> loginHandler(
			@RequestBody @Valid LoginRequest req
			) throws UserException {
		AuthResponse res=authService.login(req.getUsername(),req.getPassword());,
		return ResponseEntity.ok(res);
	}
	
	@PostMapping("/forgot-password")
	public ResponseEntity<AuthResponse> forgotPassword(
			@RequestBody ForgotPasswordRequest request
			) throws UserException {
		authService.createPasswordResetToken(request.getUsername(),req.getPassword());
		return ResponseEntity.ok(request);
	}
	


    @PostMapping("/reset-password")
public ResponseEntity<AuthResponse> resetPassword(
		@RequestBody ResetPasswordRequest request) 
		 throws UserException {
	ApiResponse res= new ApiResponse("Password reset successful",true);

return ResponseEntity.ok(res);
}


}
