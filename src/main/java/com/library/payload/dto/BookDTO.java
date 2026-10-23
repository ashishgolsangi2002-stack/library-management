package com.library.payload.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookDTO {
	private Long id;
	
	@NotBlank(message="ISBN is mandatory")
	private String isbn;
	
	@NotBlank(message="Title is mandatory")
	@Size(min=1, max=255, message="title must be between 1 nad 255 chracter" )
	private String title;
	
	@NotBlank(message="Author is mandatory")
	@Size(min=1, max=255, message="title must be between 1 nad 255 chracter" )
	private String author;
	
	@NotNull(message="Genre is Mandatory")
	private Long genreId;
	
	private String genreName;
	
	private String genreCode;
	
	@Size(max=100, message="Publisher name must not exceed 100 characters")
	private String Publisher;
	
	private LocalDate publicationDate;
	
	@Size(max=20, message="Language must not exceed 20 chracters")
	private String language;
	
	@Min(value=1, message="Pages must be at least 1")
	@Max(value=50000, message="Description must not exceed 2000 characters")
	private Integer pages;
	
	@Size(max=2000, message="Description must not exceed 2000 words")
	private String description;
	
	@Min(value=0, message="total number of copies cannot be negative")
	@NotNull(message="Total copies is mandatory")
	private Integer totalCopies;
	
	@Min(value=0, message="Available copies cannot be negative")
	@NotNull(message="Total copies is mandatory")
	private Integer availableCopies;
	
	@DecimalMin(value="0.0", inclusive=true, message="Price cannot be negative")
	@Digits(integer=8, fraction=2, message="price must have atmost 8 integers and 2 decimal points")
	private BigDecimal price;
	
	@Size(max=500, message="Image URL must not exceed 500characters")
	private String coverImageUrl;
	
	private Boolean alreadyHaveLoan;
	
	private Boolean alreadyHaveReservation;
	
	private Boolean active;
	
	private LocalDateTime createdAt;
	
	private LocalDateTime updatedAt;
	
	
	
}
