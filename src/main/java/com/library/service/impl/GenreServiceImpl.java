package com.library.service.impl;

import org.springframework.stereotype.Service;

import com.library.modal.Genre;
import com.library.repository.GenreRepository;
import com.library.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {
	
	private final GenreRepository genreRepository;
	
	public GenreServiceImpl(GenreRepository genreRepository) {
		this.genreRepository=genreRepository;
	}
	
@Override
public Genre  createGenre(Genre genre) {
	return genreRepository.save(genre);
}
}
