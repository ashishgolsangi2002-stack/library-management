package com.library.service;

import com.library.modal.Genre;
import com.library.payload.dto.GenreDTO;

public interface GenreService {
	

GenreDTO createGenre(GenreDTO genre);
}
