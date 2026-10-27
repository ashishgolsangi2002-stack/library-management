package com.library.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.library.exception.BookException;
import com.library.mapper.BookMapper;
import com.library.modal.Book;
import com.library.payload.dto.BookDTO;
import com.library.payload.request.BookSearchRequest;
import com.library.payload.response.PageResponse;
import com.library.repository.BookRepository;
import com.library.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDTO createBook(BookDTO bookDTO) {
            throw new BookException(
                    "Book with ISBN " + bookDTO.getIsbn() + " already exists");
        }

        Book book = bookMapper.toEntity(bookDTO);
        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public List<BookDTO> createBooksBulk(List<BookDTO> bookDTOs) throws BookException {
        List<BookDTO> createdBooks = new ArrayList<>();
        for (BookDTO bookDTO : bookDTOs) {
            createdBooks.add(createBook(bookDTO));
        }
        return createdBooks;
    }

    @Override
    public BookDTO getBookById(Long bookId) throws BookException {
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found with id: " + bookId));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO getBookByISBN(String isbn) throws BookException {
        Book book = bookRepository.findByIsbn(isbn)
                .orElseThrow(() -> new BookException("Book not found with ISBN: " + isbn));
        return bookMapper.toDTO(book);
    }

    @Override
    public BookDTO updateBook(Long bookId, BookDTO bookDTO) throws BookException {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found with id: " + bookId));

        // Optional: prevent changing ISBN to one that already exists on another book
        if (bookDTO.getIsbn() != null
                && !bookDTO.getIsbn().equals(existingBook.getIsbn())
                && bookRepository.existsByIsbn(bookDTO.getIsbn())) {
            throw new BookException(
                    "Book with ISBN " + bookDTO.getIsbn() + " already exists");
        }

        bookMapper.updateEntityFromDTO(bookDTO, existingBook);

        if (!existingBook.isAvailableCopesValid()) {
            throw new BookException("Available copies cannot exceed total copies");
        }

        Book savedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public void deleteBook(Long bookId) throws BookException {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found with id: " + bookId));
        existingBook.setActive(false);
        bookRepository.save(existingBook);
    }

    @Override
    public void hardDeleteBook(Long bookId) throws BookException {
        Book existingBook = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookException("Book not found with id: " + bookId));
        bookRepository.delete(existingBook);
    }

    @Override
    @Transactional(readOnly = true)
    public PageResponse<BookDTO> searchBookWithFilters(BookSearchRequest searchRequest) {
        Pageable pageable = createPageable(
                searchRequest.getPage() != null ? searchRequest.getPage() : 0,
                searchRequest.getSize() != null ? searchRequest.getSize() : 20,
                searchRequest.getSortBy() != null ? searchRequest.getSortBy() : "createdAt",
                searchRequest.getSortDirection() != null ? searchRequest.getSortDirection() : "DESC");

        boolean availableOnly = Boolean.TRUE.equals(searchRequest.getAvailableOnly());

        Page<Book> bookPage = bookRepository.searchBooksWithFilters(
                searchRequest.getSearchTerm(),
                searchRequest.getGenreId(),
                availableOnly,
                pageable);

        return convertToPageResponse(bookPage);
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalActiveBooks() {
        return bookRepository.countByActiveTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public long getTotalAvailableBooks() {
        return bookRepository.countAvailablebooks();
    }

    // --- helpers ---

    private Pageable createPageable(int page, int size, String sortBy, String sortDirection) {
        size = Math.min(Math.max(size, 1), 100);
        page = Math.max(page, 0);

        Sort sort = "ASC".equalsIgnoreCase(sortDirection)
                ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        return PageRequest.of(page, size, sort);
    }

    private PageResponse<BookDTO> convertToPageResponse(Page<Book> books) {
        List<BookDTO> bookDTOs = books.getContent().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());

        return new PageResponse<>(
                bookDTOs,
                books.getNumber(),
                books.getSize(),
                books.getTotalElements(),
                books.getTotalPages(),
                books.isLast(),
                books.isFirst(),
                books.isEmpty());
    }

	@Override
	public List<BookDTO> createBooksBulk() {
		// TODO Auto-generated method stub
		return null;
	}
}
}