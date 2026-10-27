package com.library.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.exception.BookException;
import com.library.payload.dto.BookDTO;
import com.library.payload.request.BookSearchRequest;
import com.library.payload.response.ApiResponse;
import com.library.payload.response.PageResponse;
import com.library.service.BookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
private final BookService bookService;

@PostMapping
public ResponseEntity<BookDTO>createBook(
	@Valid @RequestBody BookDTO bookDTO) throws BookException{
	BookDTO createdBook= bookService.createBook(bookDTO);
	return ResponseEntity.ok(createdBook);
}

@PostMapping("/bulk")
public ResponseEntity<BookDTO>createBookBulk(
	@Valid @RequestBody BookDTO bookDTO) throws BookException{
	List<BookDTO> createdBook= bookService.createBooksBulk(bookDTOS);
	return ResponseEntity.ok(createdBook);
}

@PostMapping("/{id}")
public ResponseEntity<BookDTO> getBookById(@PathVariable Long id)
throws BookException {
	BookDTO book= bookService.getBookById(id);
	return ResponseEntity.ok(book);
	}

@PutMapping("/{id}")
public ResponseEntity<BookDTO> updateBook(
	@PathVariable Long id,
	@Valid @RequestBody BookDTO bookDTO){
	
		BookDTO updatedBook= bookService.updatedBook(id, bookDTO);
		return ResponseEntity.ok(updatedBook);
	
	
}

/**
 * Soft delete a book
 * DELETE /api/books/{id}
 */
@DeleteMapping("/{id}")
public ResponseEntity<ApiResponse> deleteBook(@PathVariable Long id) throws BookException {
    bookService.deleteBook(id);
    return ResponseEntity.ok(new ApiResponse("Book deleted successfully", true));
}

/**
 * Permanently delete a book
 * DELETE /api/books/{id}/permanent
 */
@DeleteMapping("/{id}/permanent")
public ResponseEntity<ApiResponse> hardDeleteBook(@PathVariable Long id) throws BookException {
    bookService.hardDeleteBook(id);
    return ResponseEntity.ok(new ApiResponse("Book permanently deleted", true));
}

@PostMapping("/search")
public ResponseEntity<PageResponse<BookDTO>> advancedSearch(
	@RequestBody BookSearchRequest searchRequest) {
	PageResponse<BookDTO> books= bookService.searchBookWithFilters(searchRequest);
	return ResponseEntity.ok(books);
}
@GetMapping("/stats")
public ResponseEntity<BookStatsResponse> getBookStats(){
	long totalActive=bookService.getTotalActiveBooks();
	long totalAvailable=bookService.getTotalAvailable Books();
	BookStatsResponse stats= new BookStatsResponse(totalActive, totalAvailable);
}

public static class BookStatResponse{
	public long totalActiveBooks;
	public long totalAvailableBooks;
	
	public BookStatsResponse(long totalActiveBooks, long totalAvailableBooks) {
		this.totalActiveBooks= totalActiveBooks;
		this.totalAvailableBooks= totalAvailableBooks; 
	}
}
}
