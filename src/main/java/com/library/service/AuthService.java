package com.library.service;

import com.library.payload.dto.UserDTO;
import com.library.payload.response.AuthResponse;

public interface AuthService {
AuthResponse login(String username, String password);
AuthResponse signup(UserDTO req);

void createPasswordResetToken(String email);
void resetPassword(String token, String newPassword);

}
