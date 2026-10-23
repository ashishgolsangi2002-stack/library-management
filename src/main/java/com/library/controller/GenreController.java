package com.library.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.exception.GenreException;
import com.library.payload.dto.GenreDTO;
import com.library.payload.response.ApiResponse;
import com.library.service.GenreService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/genres")
public class GenreController {

    private final GenreService genreService;

    // CREATE
    @PostMapping("/create")
    public ResponseEntity<GenreDTO> addGenre(@RequestBody GenreDTO genre) {
        GenreDTO createdGenre = genreService.createGenre(genre);
        return ResponseEntity.ok(createdGenre);
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAllGenre() {
        List<GenreDTO> genres = genreService.getAllGenres();
        return ResponseEntity.ok(genres);
    }

    // GET BY ID
    @GetMapping("/{genreId}")
    public ResponseEntity<GenreDTO> getGenreById(@PathVariable("genreId") Long genreId) throws GenreException {
        GenreDTO genre = genreService.getGenreById(genreId);
        return ResponseEntity.ok(genre);
    }

    // UPDATE
    @PutMapping("/{genreId}")
    public ResponseEntity<GenreDTO> updateGenre(
            @PathVariable("genreId") Long genreId,
            @RequestBody GenreDTO genreDTO) throws GenreException {
        GenreDTO updatedGenre = genreService.updateGenre(genreId, genreDTO);
        return ResponseEntity.ok(updatedGenre);
    }

    // SOFT DELETE
    @DeleteMapping("/{genreId}")
    public ResponseEntity<ApiResponse> deleteGenre(@PathVariable("genreId") Long genreId) throws GenreException {
        genreService.deleteGenre(genreId);
        ApiResponse response = new ApiResponse("Genre Deleted - soft delete", true);
        return ResponseEntity.ok(response);
    }

    // HARD DELETE
    @DeleteMapping("/{genreId}/hard")
    public ResponseEntity<ApiResponse> hardDeleteGenre(@PathVariable("genreId") Long genreId) throws GenreException {
        genreService.hardDeleteGenre(genreId);
        ApiResponse response = new ApiResponse("Genre Deleted - hard delete", true);
        return ResponseEntity.ok(response);
    }

    // GET TOP LEVEL GENRES
    @GetMapping("/top-level")
    public ResponseEntity<List<GenreDTO>> getTopLevelGenre() {
        List<GenreDTO> genres = genreService.getTopLevelGenres();
        return ResponseEntity.ok(genres);
    } 

    // GET TOTAL ACTIVE COUNT
    @GetMapping("/count")
    public ResponseEntity<Long> getTotalActiveGenre() {
        Long count = genreService.getTotalActiveGenres();
        return ResponseEntity.ok(count);
    }

    // GET BOOK COUNT BY GENRE
    @GetMapping("/{id}/book-count")
    public ResponseEntity<Long> getBookCountByGenre(@PathVariable("id") Long id) {
        Long count = genreService.getBookCountByGenre(id);
        return ResponseEntity.ok(count);
    }
}