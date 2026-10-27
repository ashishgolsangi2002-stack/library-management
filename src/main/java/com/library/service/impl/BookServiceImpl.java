package com.library.service.impl;

import java.awt.print.Pageable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.library.exception.BookException;
import com.library.modal.Book;
import com.library.payload.dto.BookDTO;
import com.library.payload.request.BookSearchRequest;
import com.library.payload.response.PageResponse;
import com.library.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	@Override
	public BookDTO createBook(BookDTO bookDTO) {
		// TODO Auto-generated method stub
		if(bookRepository.existsByIsbn(bookDTO.getIsbn())) {
			throw new BookException("book with isbn" + bookDTO.getIsbn() +"already exisits");
		}
		Book book= bookMapper.toEntity(bookDTO);
		
		return bookMapper.toDTO(savedBook);
	}

	@Override
	public List<BookDTO> createBooksBulk(List<BookDTO> bookDTOs) throws BookException {
		// TODO Auto-generated method stub
		List<Book> createdBooks= new ArrayList<>();
		for(BookDTO bookDTO:bookDTOs) {
		Book book =	createBook(bookDTO);
		createdBooks.add(book);
		}
		return List.of();
	}

	@Override
	public BookDTO getBookById(Long bookId) throws BookException {
		// TODO Auto-generated method stub
		Book book= bookRepository.findById(bookId)
				.orElseThrow(()-> new BookException("book not found"));
		return bookMapper.toDTO(book);
	}

	@Override
	public BookDTO getBookByISBN(String isbn) {
		// TODO Auto-generated method stub
		Book book= bookRepository.findByIsbn(isnb)
				.orElseThrow(()-> new BookException("book not found!"));
		return bookMapper.toDTO(book);
		
	}

	@Override
	public BookDTO updateBook(Long bookId, BookDTO bookDTO) throws BookException {
		// TODO Auto-generated method stub
		Book exisitingBook= bookRepository.findById(bookId).orElseThrow(()-> new BookException("book not found"));
		bookMapper.updateEntityFromDTO(bookDTO, existingBook);
		exisitingBook.isAvailableCopesValid();
		Book savedBook=bookRepository.save(existingBook);
		return bookMapper.toDTO(savedBook);
	}

	@Override
	public void deleteBook(Long bookId) {
		Book exisitingBook= bookRepository.findById(bookId).orElseThrow(()-> new BookException("book not found"));
		// TODO Auto-generated method stub
		exisitingBook.setActive(false);
		bookRepository.delete(exisitingBook);
	}
	
	
	@Override
	public void hardDeleteBook(Long bookId) {
		Book exisitingBook= bookRepository.findById(bookId).orElseThrow(()-> new BookException("book not found"));
		// TODO Auto-generated method stub
		
		bookRepository.delete(exisitingBook);
	} 
	@Override
	public PageResponse<BookDTO> searchBookWithFilters(BookSearchRequest searchRequest) {
		// TODO Auto-generated method stub
		Pageable pageable= createPageable(searchRequest.getPage(),)
	Page<book> bookPage bookRepository.searchBooksWithFilters(
				searchRequest.getSearchTerm(),
				searchRequest.getGenreId(),
				searchRequest.getAvailableOnly()
				);
		return null;
	}

	@Override
	public long getTotalActiveBooks() {
		// TODO Auto-generated method stub
		return bookRepository.countByActiveTrue();
	}

	@Override
	public long getTotalAvailableBooks() {
		// TODO Auto-generated method stub
		return bookRepository.countAvailableBooks();
	}

	@Override
	public List<BookDTO> createBooksBulk() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Pageable createPageable(int page, int size, String sortBy, String sortDirection) {
		size=Math.min(size, 10);
		size=Math.max(size, 1);
		
		Sort sort = sortDirection.equalsIgnoreCase("ASC")
				?Sort.by(sortBy).ascending():Sort.by(sortBy).descending();
	return PageRequest.of(page,size,sort);
	}

	private PageResponse<BookDTO> convertToPageResponse(Page<Book> books){
		List<BookDTO> bookDTOs= books.getContent()
				.stream()
				.map(bookMapper::toDTO)
				.collect(Collectors.toList());
		
		return new PageResponse PageResponse<>(bookDTOS,
				books.getNumber(),
				books.getSize(),
				books.getTotalElements(),
				books.getTotalPages(),
				books.isLast(),
				books.isFirst(),
				books.isEmpty
				);
	}
}
