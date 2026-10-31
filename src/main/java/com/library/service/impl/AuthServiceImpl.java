package com.library.service.impl;

import java.time.LocalDateTime;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.configuration.Authentication;
import com.library.configuration.JwtProvider;
import com.library.configuration.UsernamePasswordAuthenticationToken;
import com.library.domain.UserRole;
import com.library.exception.UserException;
import com.library.mapper.UserMapper;
import com.library.modal.User;
import com.library.payload.dto.UserDTO;
import com.library.payload.response.AuthResponse;
import com.library.repository.UserRepository;
import com.library.service.AuthService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final JwtProvider jwtProvider;
	private final UserMapper userMapper;
	private final CustomUserServiceImplementation customUserServiceImplementation;

	@Override
	public AuthResponse login(String username, String password) throws UserException {
		// TODO Auto-generated method stub
		
			Authentication authentication=authenticate(username,password);
//			org.springframework.security.core.Authentication authentication = authenticate(username, password);
			SecurityContextHolder.getContext().setAuthentication(authentication);
//			Collection<? extends GrantedAuthority> authorities =authentication.getAuthorities();
//			String role=authorities.iterator().next().getAuthority();
			String token=jwtProvider.generateToken(authentication);
		

	
		User user=userRepository.findByEmail(username);
		
		
		
		user.setLastLogin(LocalDateTime.now());
		userRepository.save(user);
		
		AuthResponse response=new AuthResponse();
		response.setJwt(jwt);
		response.setTitle("Welcome" +createdUser.getFullName());
		response.setMessage("");
		response.setUser(UserMapper.toDTO(savedUser));		
		return response;
	}

		private Authentication authentication(String username, String password) {
			UserDetails userDetails=customerUserServiceImplementation.loadByUsername(username);
			if(userDetails==null) {
	 			throw new UserException("User not found with email"+ password);
			}
			if(!passwordEncoder.matches(password,userDetails.getPassword())) {//Checks if entered password is same as one stored in Database
				throw new UserException("Password not match");
			}
			return new UserPasswordAuthenticationToken(email,null, userDetails.getAuthorities());
			
		}
		
	}
	
	@Override
	public AuthResponse signup(UserDTO req) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createPasswordResetToken(String email) {
		// TODO Auto-generated method stub
		User user=userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not found with given email");
	}

	@Override
	public void resetPassword(String token, String newPassword) {
		// TODO Auto-generated method stub
		
	}

}
