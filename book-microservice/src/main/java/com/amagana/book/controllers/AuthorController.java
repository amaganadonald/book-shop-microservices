package com.amagana.book.controllers;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.book.dto.AuthorRequestDTO;
import com.amagana.book.dto.AuthorResponseDTO;
import com.amagana.book.service.AuthorService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/author")
@Slf4j
public class AuthorController {

	private AuthorService authorService;
	
	@GetMapping
	public Flux<AuthorResponseDTO> getAllAuthor() {
		return authorService.getAllAuthor();
	}
	
	@GetMapping("/{id}")
	public Mono<AuthorResponseDTO> getAuthorById(@PathVariable String id) {
		return authorService.getAuthorById(id);
	}
	
	@PostMapping
	public Mono<AuthorResponseDTO> addAuthor(@RequestBody AuthorRequestDTO authorRequestDTO) {
		log.error("AuthController::addAuthor started to send authorRequest....");
		return authorService.addAuthor(authorRequestDTO);
	}
	
	@PutMapping("/{id}")
	public Mono<AuthorResponseDTO> updateAddress(@RequestBody AuthorRequestDTO authorRequestDTO, 
			@PathVariable String id) {
		return authorService.updateAuthor(authorRequestDTO, id);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteAddress(@PathVariable String id) {
		return authorService.deleteAuthor(id);
	}
}
