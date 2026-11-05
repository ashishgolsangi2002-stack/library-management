package com.library.service.impl;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.library.domain.UserRole;
import com.library.modal.User;
import com.library.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DataInititalizationComponent  implements CommandLineRunner{
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public void run(String... args) {
		initializeAdminUser();
	}

	private void initializeAdminUser() {
		String adminEmail="ashishgolsangi2002@gmail.com";
		String adminPassword=" ";
		if(userRepository.findByEmail(adminEmail==null)) {
			User user=User.builder()
					.password(passwordEncoder.encode(adminPassword))
					.email(adminEmail)
					.fullName("Ashish Golsangi")
					.role(UserRole.ROLE_ADMIN)
					.build();
			
			User admin=userRepository.save(user);
			
		}
	}
}
