package com.luxprojects.onlineBook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

import com.luxprojects.onlineBook.dto.CategoryResponseDTO;
import com.luxprojects.onlineBook.dto.CategoryResquestDTO;
import com.luxprojects.onlineBook.service.CategoryService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/category")
@AllArgsConstructor
public class CategoryRestController {
	
	private CategoryService categoryService;
	
	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Flux<CategoryResponseDTO> getAllCategory() {
		return categoryService.getAllCategory();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<CategoryResponseDTO>> getCategoryById(@PathVariable Long id) {
		return categoryService.getCategoryById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<CategoryResponseDTO> addCategory(@RequestBody Mono<CategoryResquestDTO> categoryResquestDTO) {
		return categoryService.addCategory(categoryResquestDTO);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<CategoryResponseDTO>> updateCatgory(@PathVariable Long id, @RequestBody Mono<CategoryResquestDTO> categoryResquestDTO) {
		return categoryService.updateCategory(categoryResquestDTO, id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public Mono<Void> deleteCatgory(@PathVariable Long id) {
		return categoryService.deleteCategory(id);
	}
}
