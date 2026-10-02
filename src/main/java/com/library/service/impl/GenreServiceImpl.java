package com.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.modal.Genre;
import com.library.payload.dto.GenreDTO;
import com.library.repository.GenreRepository;
import com.library.service.GenreService;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Builder
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	private final GenreRepository genreRepository;
	
	
	
	
	
@Override
public Genre  createGenre(GenreDTO genreDTO) {
	//return genreRepository.save(genreDTO);
	
	Genre genre = Genre.builder()
	            .code(genreDTO.getCode())
	            .description(genreDTO.getDescription())
	            .displayOrder(genreDTO.getDisplayOrder())
	            .active(true)
	            .build();
	
	if(genreDTO.getParentGenreId()!=null) {
		Genre parentGenre= genreRepository.findById(genreDTO.getParentGenreId()).get();
		genre.setParentGenre(parentGenre);
	}
	Genre savedGenre= genreRepository.save(genre);
	  
	GenreDTO dto=  GenreDTO.builder()
			.id(savedGenre.getId())
			.code(savedGenre.getCode())
			.name(savedGenre.getName())
			.description(savedGenre.getDescription())
			.displayOrder(savedGenre.getDisplayOrder())
			.active(savedGenre.getActive())
			.createdAt(savedGenre.getCreatedAt())
			.updatedAt(savedGenre.getUpdatedAt())
			.build();
	
	if(savedGenre.getParentGenre()!=null) {
	dto.setParentGenreId(savedGenre.getParentGenre().getId());
	dto.setParentGenreName(savedGenre.getParentGenre().getName());
	}
	
//	dto.setSubGenre(saveGenre.getSubGenres().stream().
//			filter(subGenre-> subGenre.getActive())
//			.map(subGenre->));
	
//	dto.setBookCount((long) (savedGenre.getB));
	
	return dto;
}
}
