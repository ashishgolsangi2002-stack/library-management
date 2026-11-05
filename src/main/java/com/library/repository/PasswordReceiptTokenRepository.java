package com.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.modal.PasswordResetToken;

public interface PasswordReceiptTokenRepository extends JpaRepository<PasswordResetToken, Long> {
Optional <PasswordResetToken> findByToken(String token);
}
