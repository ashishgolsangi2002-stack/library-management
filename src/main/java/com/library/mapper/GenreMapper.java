package com.library.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.library.modal.Genre;
import com.library.payload.dto.GenreDTO;
import com.library.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GenreMapper {

    private final GenreRepository genreRepository;

    public GenreDTO toDTO(Genre savedGenre) {
        if (savedGenre == null) {
            return null;
        }

        GenreDTO dto = GenreDTO.builder()
                .id(savedGenre.getId())
                .code(savedGenre.getCode())
                .name(savedGenre.getName())
                .description(savedGenre.getDescription())
                .displayOrder(savedGenre.getDisplayOrder())
                .active(savedGenre.getActive())
                .createdAt(savedGenre.getCreatedAt())
                .updatedAt(savedGenre.getUpdatedAt())
                .build();

        if (savedGenre.getParentGenre() != null) {
            dto.setParentGenreId(savedGenre.getParentGenre().getId());
            dto.setParentGenreName(savedGenre.getParentGenre().getName());
        }

        if (savedGenre.getSubGenres() != null) {
            dto.setSubGenre(savedGenre.getSubGenres().stream()
                    .filter(subGenre -> Boolean.TRUE.equals(subGenre.getActive()))
                    .map(this::toDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }

    public Genre toEntity(GenreDTO genreDTO) {
        if (genreDTO == null) {
            return null;
        }

        Genre genre = Genre.builder()
                .code(genreDTO.getCode())
                .name(genreDTO.getName())
                .description(genreDTO.getDescription())
                .displayOrder(genreDTO.getDisplayOrder() != null ? genreDTO.getDisplayOrder() : 0)
                .active(genreDTO.getActive() != null ? genreDTO.getActive() : true)
                .build();

        if (genreDTO.getParentGenreId() != null) {
            genreRepository.findById(genreDTO.getParentGenreId())
                    .ifPresent(genre::setParentGenre);
        }

        return genre;
    }

    public void updateEntityFromDTO(GenreDTO dto, Genre existingGenre) {
        if (dto == null || existingGenre == null) {
            return;
        }

        if (dto.getCode() != null) {
            existingGenre.setCode(dto.getCode());
        }
        if (dto.getName() != null) {
            existingGenre.setName(dto.getName());
        }
        if (dto.getDescription() != null) {
            existingGenre.setDescription(dto.getDescription());
        }
        if (dto.getDisplayOrder() != null) {
            existingGenre.setDisplayOrder(dto.getDisplayOrder());
        }
        if (dto.getActive() != null) {
            existingGenre.setActive(dto.getActive());
        }

        if (dto.getParentGenreId() != null) {
            genreRepository.findById(dto.getParentGenreId())
                    .ifPresent(existingGenre::setParentGenre);
        }
    }

    public List<GenreDTO> toDTOList(List<Genre> genreList) {
        return genreList.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}