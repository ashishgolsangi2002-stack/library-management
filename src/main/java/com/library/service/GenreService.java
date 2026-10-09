package com.library.service;

import java.util.List;


import org.springframework.boot.data.autoconfigure.web.DataWebProperties.Pageable;
import org.springframework.data.domain.Page;

import com.library.payload.dto.GenreDTO;

public interface GenreService {
    GenreDTO createGenre(GenreDTO genre);
    List<GenreDTO> getAllGenres();
    GenreDTO getGenreById(Long genreId);
    GenreDTO updateGenre(Long genreId,GenreDTO genre);
    void hardDeleteGenre(Long genreId);
    List<GenreDTO> getAllActiveGenresWithSubGenres();
    List<GenreDTO> getTopLevelGenres();
    //Page<GenreDTO> searchGenres(String searchTerm, Pageable pageable);
    Long getTotalActiveGenres();
    Long getBookCountByGenre(Long genreId);

}