package com.luxprojects.onlineBook.service;


import com.luxprojects.onlineBook.dto.BookRequestDTO;
import com.luxprojects.onlineBook.dto.BookResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService  {

	public Flux<BookResponseDTO> getAllBook();
	public Mono<BookResponseDTO> getBookById(Long id);
	public Mono<BookResponseDTO> addBook(Mono<BookRequestDTO> bookRequestDTO);
	public Mono<BookResponseDTO> updateBook(Mono<BookRequestDTO> bookRequestDTO, Long id);
	public Mono<Void> deleteBook(Long id);
	public Mono<BookResponseDTO> bookLibrary(Long bookId, Long libraryId);
	public Mono<BookResponseDTO> bookCategory(Long bookId, Long categoryId);
}
