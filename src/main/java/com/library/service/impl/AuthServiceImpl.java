package com.library.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.library.configuration.JwtProvider;
import com.library.exception.UserException;
import com.library.mapper.UserMapper;
import com.library.modal.PasswordResetToken;
import com.library.modal.User;
import com.library.payload.dto.UserDTO;
import com.library.payload.response.AuthResponse;
import com.library.repository.PasswordReceiptTokenRepository;
import com.library.repository.UserRepository;
import com.library.service.AuthService;
import com.library.service.EmailService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final UserMapper userMapper;
    private final CustomUserServiceImplementation customUserServiceImplementation;
    private final PasswordReceiptTokenRepository passwordResetTokenRepository;
    private final EmailService emailService;
    

    @Override
    public AuthResponse login(String username, String password) throws UserException {
    	String frontendUrl=""
        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UserException("User not found with email: " + username);
        }

        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);

        AuthResponse response = new AuthResponse();
        response.setJwt(token);
        response.setTitle("Welcome " + user.getFullName());
        response.setMessage("Login successful");
        response.setUser(UserMapper.toDTO(user));
        return response;
    }

    private Authentication authenticate(String username, String password) throws UserException {
        UserDetails userDetails = customUserServiceImplementation.loadUserByUsername(username);

        if (userDetails == null) {
            throw new UserException("User not found with email: " + username);
        }

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new UserException("Password does not match");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());
    }

    @Override
    public AuthResponse signup(UserDTO req) {
        // TODO: implement signup
        return null;
    }

    @Transactional
    public void createPasswordResetToken(String email) throws UserException {
    	String frontendUrl= "http://localhost:5173/api/books";
        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found with given email");
        }
        String token=UUID.randomUUID().toString(); 

  PasswordResetToken resetToken=PasswordResetToken.builder()
		 .token(token)
		 .user(user)
		 .expiryDate(LocalDateTime.now().plusMinutes(5))
		  .build();
  
  passwordResetTokenRepository.save(resetToken);
  String resetLink=frontendUrl+token;
  String subject="password Reset Request";
  String body="You requested to reset your password. Use this link(Valid 5 minutes)" +resetLink;
  
        // TODO: generate and save reset token
  
  //sent email
  emailService.sendEmail(user.getEmail(),subject,body);
    }

    @Transactional
    public void resetPassword(String token, String newPassword) throws Exception {
        // TODO: implement reset password
    	PasswordResetToken resetToken=passwordResetTokenRepository.findByToken(token)
    			.orElseThrow(
    					()->new Exception("token not valid")
    					);
    	
    	if(resetToken.isExpired()) {
    		passwordResetTokenRepository.delete(resetToken);
    		throw new Exception("token expired");
    	}
    	
    	User user=resetToken.getUser();
    	user.setPassword(passwordEncoder.encode(newPassword));
    	userRepository.save(user);
    	passwordResetTokenRepository.delete(resetToken);
    }
}


