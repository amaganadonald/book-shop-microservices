package com.amagana.book.controllers;


import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.book.clients.CategoryService;
import com.amagana.book.dto.BookRequestDTO;
import com.amagana.book.dto.BookResponseDTO;
import com.amagana.book.models.Category;
import com.amagana.book.service.BookService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/book")
public class BookController {

	private BookService bookService;
	private CategoryService categoryService;
	
	@GetMapping
	public Flux<BookResponseDTO> getAllBook() {
		return bookService.getAllBook();
	}
	
	@GetMapping("/{id}")
	public Mono<BookResponseDTO> getBookById(@PathVariable String id) {
		return bookService.getBookById(id);
	}
	
	@PostMapping
	public Mono<BookResponseDTO> addBook(@RequestBody BookRequestDTO bookRequestDTO) {
		return bookService.addBook(bookRequestDTO);
	}
	
	@PutMapping("/{id}")
	public Mono<BookResponseDTO> updateBook(@RequestBody BookRequestDTO bookRequestDTO, 
			@PathVariable String id) {
		return bookService.updateBook(bookRequestDTO, id);
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteBook(@PathVariable String id) {
		return bookService.deleteBook(id);
	}
	
	@GetMapping("/cat/{id}")
	public Category getCategory(@PathVariable Long id) {
		return categoryService.getCategoryById(id);
	}
}
