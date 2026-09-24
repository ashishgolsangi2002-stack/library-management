package com.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.library.modal.Genre;

public interface GenreRepository extends JpaRepository<Genre, Long>{

}
