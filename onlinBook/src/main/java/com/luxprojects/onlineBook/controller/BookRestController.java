package com.luxprojects.onlineBook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luxprojects.onlineBook.dto.BookRequestDTO;
import com.luxprojects.onlineBook.dto.BookResponseDTO;
import com.luxprojects.onlineBook.exception.OnlineBookBusinessException;
import com.luxprojects.onlineBook.service.BookService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/book")
@AllArgsConstructor
public class BookRestController {

	private BookService bookService;
	private static final String errorText = "Error occur with message ";
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<BookResponseDTO> getAllBook() {
		return bookService.getAllBook();
	}
	
	@GetMapping("/{id}")
	@ResponseStatus(HttpStatus.FOUND)
	public Mono<BookResponseDTO> getBookById(@PathVariable Long id) {
		return bookService.getBookById(id)
				.doOnError(e-> new OnlineBookBusinessException(errorText +e.getMessage()));
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<BookResponseDTO>> updateBook(@RequestBody Mono<BookRequestDTO> bookRequestDTO,
			                                             @PathVariable Long id) {
		return bookService.updateBook(bookRequestDTO, id)
				.map(ResponseEntity::ok)
				.doOnError(e-> new OnlineBookBusinessException(errorText +e.getMessage()));
	}
	
	@PostMapping
	public Mono<ResponseEntity<BookResponseDTO>> addBook(@RequestBody Mono<BookRequestDTO> bookRequestDTO) {
		return bookService.addBook(bookRequestDTO)
				.map(ResponseEntity::ok)
				.doOnError(e-> new OnlineBookBusinessException(errorText +e.getMessage()));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Mono<Void> deleteBook(@PathVariable Long id) {
		return bookService.deleteBook(id)
				.doOnError(e-> new OnlineBookBusinessException(errorText +e.getMessage()));

	}
	
	@PutMapping("/{bookId}/{libraryId}")
	public Mono<ResponseEntity<BookResponseDTO>> addBookLibrary(@PathVariable Long bookId, @PathVariable Long libraryId) {
		return bookService.bookLibrary(bookId, libraryId)
				.map(ResponseEntity::ok)
				.doOnError(e-> new OnlineBookBusinessException(errorText +e.getMessage()));

	}
	
	@PutMapping("/{bookId}/{categoryId}")
	public Mono<ResponseEntity<BookResponseDTO>> addBookCategory(@PathVariable Long bookId, @PathVariable Long categoryId) {
		return bookService.bookCategory(bookId, categoryId)
				.map(ResponseEntity::ok)
				.doOnError(e-> new OnlineBookBusinessException(errorText+e.getMessage()));

	}
}
