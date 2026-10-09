package com.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.library.modal.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{
List<Genre>findByActiveTrueOrderByDisplayOrderAsc();
List<Genre>findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
List<Genre>findByParentGenreIdAndActiveTrueOrderByDisplayOrderAsc(
	Long parentGenreId
);
Long countByActiveTrue();

//@Query("select count(b) from book b where")
//Long countBooksByGenre(@Param("genreId")Long genreId);
}
