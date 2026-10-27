package com.library.service;

import java.util.List;

import com.library.payload.dto.BookDTO;
import com.library.payload.request.BookSearchRequest;
import com.library.payload.response.PageResponse;

public interface BookService {
BookDTO createBook(BookDTO bookDTO);
List<BookDTO> createBooksBulk();
BookDTO getBookById(Long bookId);
BookDTO getBookByISBN(String isbn);
BookDTO updateBook(Long bookId, BookDTO bookDTO);
void deleteBook(Long bookId);

PageResponse<BookDTO> searchBookWithFilters(
	BookSearchRequest searchRequest
);

long getTotalActiveBooks();

long getTotalAvailableBooks();
List<BookDTO> createBooksBulk(List<BookDTO> bookDTOs);
void hardDeleteBook(Long id);
}
