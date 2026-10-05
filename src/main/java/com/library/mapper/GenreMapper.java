package com.library.mapper;

import java.util.stream.Collectors;

import com.library.modal.Genre;
import com.library.payload.dto.GenreDTO;

public class GenreMapper{
	public GenreDTO toDTO(Genre savedGenre) {
		if(savedGenre==null) {
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

    	dto.setSubGenre(savedGenre.getSubGenres().stream()

		.filter(subGenre-> subGenre.getActive())
		.map(subGenre-> toDTO(subGenre)).collect(Collectors.toList()));



//dto.setBookCount((long) (savedGenre.getB));
        return dto;
	}
}