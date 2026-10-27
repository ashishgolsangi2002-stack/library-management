package com.library.mapper;

import org.springframework.stereotype.Component;

import com.library.modal.Book;
import com.library.modal.Genre;
import com.library.payload.dto.BookDTO;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookMapper {

	public BookDTO toDTO(Book book) {
		if(book==null) {
			return null;
		}
		return BookDTO.builder()
				.id(book.getId())
				.title(book.getTitle())
				.author(book.getAuthor())
				.isbn(book.getIsbn())
				.genreId(book.getGenre().getId())
				.genreName(book.getGenre().getName())
				.genreCode(book.getGenre().getCode())
				.Publisher(book.getPublisher())
				.publicationDate(book.getPublishedDate())
				.language(book.getLanguage())
				.pages(book.getPages())
				.description(book.getDescription())
				.totalCopies(book.getTotalCopies())
				.availableCopies(book.getAvailableCopies())
				.price(book.getPrice())
				.coverImageUrl(book.getCoverImageUrl())
				.active(book.getActive())
				.createdAt(book.getCreatedAt())
				.updatedAt(book.getUpdatedAt())
				.build();	
	}
	
	public Book toEntity(BookDTO bookDTO) throws BookException {
	if(dto==null) {
		return null;
	}
	
	Book book= new Book();
	book.setId(dto.getId();
	book.setIsbn(dto.getIsbn());
	book.setTitle(dto.getIsbn());
	book.setAuthor(dto.getAuthor());
	
   

    // Map genre - fetch from database using genreId
    if (dto.getGenreId() != null) {
        Genre genre = genreRepository.findById(dto.getGenreId())
            .orElseThrow(() -> new BookException("Genre with ID " + dto.getGenreId() + "not found"));
        book.setGenre(genre);
    }

    book.setPublisher(dto.getPublisher());
    book.setPublicationDate(dto.getPublicationDate());
    book.setLanguage(dto.getLanguage());
    book.setPages(dto.getPages());
    book.setDescription(dto.getDescription());
    book.setTotalCopies(dto.getTotalCopies());
    book.setAvailableCopies(dto.getAvailableCopies());
    book.setPrice(dto.getPrice());
    book.setCoverImageUrl(dto.getCoverImageUrl());
    book.setActive(true); // Default to active

    return book;
	}
}