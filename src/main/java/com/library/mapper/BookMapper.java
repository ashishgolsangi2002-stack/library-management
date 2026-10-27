package com.library.mapper;

import org.springframework.stereotype.Component;

import com.library.exception.BookException;
import com.library.modal.Book;
import com.library.modal.Genre;
import com.library.payload.dto.BookDTO;
import com.library.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final GenreRepository genreRepository;

    public BookDTO toDTO(Book book) {
        if (book == null) {
            return null;
        }

        BookDTO.BookDTOBuilder builder = BookDTO.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .isbn(book.getIsbn())
                .Publisher(book.getPublisher())
                .publicationDate(book.getPublishedDate())
                .language(book.getLanguage())
                .description(book.getDescription())
                .totalCopies(book.getTotalCopies())
                .availableCopies(book.getAvailableCopies())
                .price(book.getPrice())
                .coverImageUrl(book.getCoverImageUrl())
                .active(book.getActive())
                .createdAt(book.getCreatedAt())
                .updatedAt(book.getUpdatedAt());

        if (book.getGenre() != null) {
            builder.genreId(book.getGenre().getId())
                    .genreName(book.getGenre().getName())
                    .genreCode(book.getGenre().getCode());
        }

        return builder.build();
    }

    public Book toEntity(BookDTO dto) throws BookException {
        if (dto == null) {
            return null;
        }

        Book book = new Book();
        book.setId(dto.getId());
        book.setIsbn(dto.getIsbn());
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setPublisher(dto.getPublisher());
        book.setPublishedDate(dto.getPublicationDate());
        book.setLanguage(dto.getLanguage());
        book.setDescription(dto.getDescription());
        book.setTotalCopies(dto.getTotalCopies());
        book.setAvailableCopies(dto.getAvailableCopies());
        book.setPrice(dto.getPrice());
        book.setCoverImageUrl(dto.getCoverImageUrl());
        book.setActive(dto.getActive() != null ? dto.getActive() : true);

        if (dto.getGenreId() != null) {
            Genre genre = genreRepository.findById(dto.getGenreId())
                    .orElseThrow(() -> new BookException(
                            "Genre with ID " + dto.getGenreId() + " not found"));
            book.setGenre(genre);
        }

        return book;
    }

    public void updateEntityFromDTO(BookDTO dto, Book existingBook) throws BookException {
        if (dto == null || existingBook == null) {
            return;
        }

        if (dto.getIsbn() != null) {
            existingBook.setIsbn(dto.getIsbn());
        }
        if (dto.getTitle() != null) {
            existingBook.setTitle(dto.getTitle());
        }
        if (dto.getAuthor() != null) {
            existingBook.setAuthor(dto.getAuthor());
        }
        if (dto.getPublisher() != null) {
            existingBook.setPublisher(dto.getPublisher());
        }
        if (dto.getPublicationDate() != null) {
            existingBook.setPublishedDate(dto.getPublicationDate());
        }
        if (dto.getLanguage() != null) {
            existingBook.setLanguage(dto.getLanguage());
        }
        if (dto.getDescription() != null) {
            existingBook.setDescription(dto.getDescription());
        }
        if (dto.getTotalCopies() != null) {
            existingBook.setTotalCopies(dto.getTotalCopies());
        }
        if (dto.getAvailableCopies() != null) {
            existingBook.setAvailableCopies(dto.getAvailableCopies());
        }
        if (dto.getPrice() != null) {
            existingBook.setPrice(dto.getPrice());
        }
        if (dto.getCoverImageUrl() != null) {
            existingBook.setCoverImageUrl(dto.getCoverImageUrl());
        }
        if (dto.getActive() != null) {
            existingBook.setActive(dto.getActive());
        }

        if (dto.getGenreId() != null) {
            Genre genre = genreRepository.findById(dto.getGenreId())
                    .orElseThrow(() -> new BookException(
                            "Genre with ID " + dto.getGenreId() + " not found"));
            existingBook.setGenre(genre);
        }
    }
}