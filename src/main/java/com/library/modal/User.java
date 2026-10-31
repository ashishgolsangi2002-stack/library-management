package com.library.modal;


import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.library.domain.AuthProvider;
import com.library.domain.UserRole;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Long id;

private String email;

private String fullName;

private UserRole role;

private String Phone;

private AuthProvider authProvider= AuthProvider.LOCAL; 

private String googleId;

private String profileImage;

private String Password;

@CreationTimestamp
private LocalDateTime lastLogin;

@UpdateTimestamp
private LocalDateTime updatedAt;





}
