package com.library.payload.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GenreDTO{
	private Long id;
	
	@NotBlank(message="Genere Code is Mandatory")
	private String code;

	@NotBlank(message="Genre Name is mandatory")
	private String name;

	@Size(max= 500, message="description must not exceed 500 characters")
	private String description;

	@Min(value=0, message="display order cannot be negative")
	private Integer displayOrder=0;
	
	private Boolean active;
	
	private Long parentGenreId;
	
	private String parentGenreName;
	
	private List<GenreDTO> subGenre;
	
	private Long bookCount;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
}