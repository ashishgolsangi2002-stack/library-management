package com.library.modal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private Long id;

@NotBlank(message="Genere Code is Mandatory")
private String code;

@NotBlank(message="Genre Name is mandatory")
private String name;

@Size(max= 500, message="description must not exceed 500 characters")
private String description;

@Min(value=0, message="display order cannot be negative")
private Integer displayOrder=0;

@Column(nullable=false)
private Boolean active=true;

@ManyToOne
private Genre parentGenre;

@OneToMany
private List<Genre> subGenres= new ArrayList<Genre>();  

//@OneToMany(mappedBy = "genre", cascade= CascadeType.PERSIST )
//private List<Book> books= new ArrayList<Book>();

@CreationTimestamp
private LocalDateTime createdAt;

@UpdateTimestamp
private LocalDateTime updatedAt;


}
