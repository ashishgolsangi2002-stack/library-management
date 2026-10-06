package com.library.service;

import java.util.List;
import com.library.payload.dto.GenreDTO;

public interface GenreService {
    GenreDTO createGenre(GenreDTO genre);
    List<GenreDTO> getAllGenres();
}