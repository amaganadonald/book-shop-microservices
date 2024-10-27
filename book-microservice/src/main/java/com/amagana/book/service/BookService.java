package com.amagana.book.service;

import com.amagana.book.dto.BookRequestDTO;
import com.amagana.book.dto.BookResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BookService {

	public Flux<BookResponseDTO> getAllBook();
	public Mono<BookResponseDTO> getBookById(String id);
	public Mono<BookResponseDTO> addBook(BookRequestDTO bookRequestDTO);
	public Mono<BookResponseDTO> updateBook(BookRequestDTO bookRequestDTO, String id);
	public Mono<Void> deleteBook(String id);
}
