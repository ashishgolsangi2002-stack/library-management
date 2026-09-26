package com.library.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.modal.Genre;
import com.library.service.GenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

	private final GenreService genreService;
	
	@PostMapping("/create")
	public ResponseEntity<Genre> addGenre(@RequestBody Genre  genre){
		Genre createdGenre= genreService.createGenre(genre);
		return ResponseEntity.ok(createdGenre);
	}
}
