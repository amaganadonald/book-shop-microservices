package com.amagana.book.service;


import com.amagana.book.dto.AuthorRequestDTO;
import com.amagana.book.dto.AuthorResponseDTO;
import com.amagana.book.entity.Author;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


public interface AuthorService {

	public Flux<AuthorResponseDTO> getAllAuthor();
	public Mono<AuthorResponseDTO> getAuthorById(String id);
	public Mono<AuthorResponseDTO> addAuthor(AuthorRequestDTO authorRequest);
	public Mono<AuthorResponseDTO> updateAuthor(AuthorRequestDTO authorRequest, String id);
	public Mono<Void> deleteAuthor(String id);
	public Mono<Author> findAuthorById(String id);
}
