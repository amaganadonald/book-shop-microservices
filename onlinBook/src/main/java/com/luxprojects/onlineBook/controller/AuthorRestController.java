package com.luxprojects.onlineBook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luxprojects.onlineBook.dto.AuthorRequestDTO;
import com.luxprojects.onlineBook.dto.AuthorResponseDTO;
import com.luxprojects.onlineBook.service.AddressService;
import com.luxprojects.onlineBook.service.AuthorService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/author")
public class AuthorRestController {

	private AuthorService authorService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<AuthorResponseDTO> getAllAuthors() {
		return authorService.getAllAuthors();
	}
	
	@GetMapping
	public Mono<AuthorResponseDTO> getAuthorById(Long id) {
		return authorService.getAuthorById(id);
	}
	
	@PostMapping
	public Mono<AuthorResponseDTO> addAuthor(@RequestBody Mono<AuthorRequestDTO> authorRequestDTO) {
		return authorService.addAuthor(authorRequestDTO);
	}
	
	@PutMapping("/{id}")
	public Mono<AuthorResponseDTO> updateAuthor(@RequestBody Mono<AuthorRequestDTO> authorRequestDTO,
			@PathVariable Long id) {
		return authorService.updateAuthor(authorRequestDTO, id);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteAuthor(@PathVariable Long id) {
		return authorService.deleteAuthor(id);
	}
	
	@PostMapping("/upload")
	public Mono<String> handleFileUpload(FilePart filePart) {
		return authorService.addAuthorPicture(filePart);
	}
}
