package com.luxprojects.onlineBook.service;

import org.springframework.http.codec.multipart.FilePart;

import com.luxprojects.onlineBook.dto.AuthorRequestDTO;
import com.luxprojects.onlineBook.dto.AuthorResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public  interface AuthorService {

	Flux<AuthorResponseDTO> getAllAuthors();
	Mono<AuthorResponseDTO> getAuthorById(Long id);
	Mono<AuthorResponseDTO> addAuthor(Mono<AuthorRequestDTO> authorRequetDTO);
	Mono<AuthorResponseDTO> updateAuthor(Mono<AuthorRequestDTO> authorResponseDTO, Long id);
	Mono<Void> deleteAuthor(Long id);
	Mono<String> addAuthorPicture(FilePart filePart);
}
