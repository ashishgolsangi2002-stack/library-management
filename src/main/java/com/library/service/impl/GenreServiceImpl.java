package com.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.library.modal.Genre;
import com.library.repository.GenreRepository;
import com.library.service.GenreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {
	
	@Autowired
	private final GenreRepository genreRepository;
	
	
	
	
	
@Override
public Genre  createGenre(Genre genre) {
	return genreRepository.save(genre);
}
}
