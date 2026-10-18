package com.library.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.library.exception.GenreException;
import com.library.mapper.GenreMapper;
import com.library.modal.Genre;
import com.library.payload.dto.GenreDTO;
import com.library.repository.GenreRepository;
import com.library.service.GenreService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;
    private final GenreMapper genreMapper;

    @Override
    public GenreDTO createGenre(GenreDTO genreDTO) {

        Genre genre = Genre.builder()
                .code(genreDTO.getCode())
                .name(genreDTO.getName())
                .description(genreDTO.getDescription())
                .displayOrder(genreDTO.getDisplayOrder() != null ? genreDTO.getDisplayOrder() : 0)
                .active(genreDTO.getActive() != null ? genreDTO.getActive() : true)
                .build();

        if (genreDTO.getParentGenreId() != null) {
            Genre parentGenre = genreRepository.findById(genreDTO.getParentGenreId())
                    .orElseThrow(() -> new RuntimeException("Parent genre not found with id: " + genreDTO.getParentGenreId()));
            genre.setParentGenre(parentGenre);
        }

        Genre savedGenre = genreRepository.save(genre);

              
        return genreMapper.toDTO(savedGenre);
    }

    @Override
    public List<GenreDTO> getAllGenres() {
        return genreRepository.findAll()
                .stream()
                .map(genreMapper::toDTO)
                .collect(Collectors.toList());
    }

	@Override
	public GenreDTO getGenreById(Long genreId) throws GenreException {
		// TODO Auto-generated method stub
		Genre genre=genreRepository.findById(genreId).orElseThrow(
				()-> new GenreException("genre not found")
				);
		return genreMapper.toDTO(genre);
		}

	@Override
	public GenreDTO updateGenre(Long genreId, GenreDTO genreDTO) throws GenreException {
		// TODO Auto-generated method stub
		Genre existingGenre= genreRepository.findById(genreId).orElseThrow(
				()-> new GenreException("Genre not found")
				);
		genreMapper.updateEntityFromDTO(genreDTO,existingGenre);
		
		 Genre updatedGenre= genreRepository.save(existingGenre);
		 
		 return genreMapper.toDTO(updatedGenre);
		 }

	@Override
	public void deleteGenre(Long genreId) throws GenreException {
		Genre existingGenre= genreRepository.findById(genreId).orElseThrow(
				()-> new GenreException("Genre not found")
				);
		existingGenre.setActive(false);
		genreRepository.save(existingGenre);
	}
	
	
	@Override
	public void hardDeleteGenre(Long genreId) throws GenreException {
		// TODO Auto-generated method stub
		Genre existingGenre= genreRepository.findById(genreId).orElseThrow(
				()-> new GenreException("Genre not found")
				);
		genreRepository.delete(existingGenre);
	}

	@Override
	public List<GenreDTO> getAllActiveGenresWithSubGenres() {
		// TODO Auto-generated method stub
		List<Genre> topLevelGenres =genreRepository
				.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
		return genreMapper.toDTOList(topLevelGenres);
	}

	@Override
	public List<GenreDTO> getTopLevelGenres() {
		// TODO Auto-generated method stub
		List<Genre> topLevelGenres =genreRepository
				.findByParentGenreIsNullAndActiveTrueOrderByDisplayOrderAsc();
		return genreMapper.toDTOList(topLevelGenres);
		
	}

	@Override
	public Long getTotalActiveGenres() {
		// TODO Auto-generated method stub
		return genreRepository.countByActiveTrue();
	}

	@Override
	public Long getBookCountByGenre(Long genreId) {
		// TODO Auto-generated method stub
		return null;
	}
}