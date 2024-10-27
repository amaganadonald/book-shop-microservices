package com.luxprojects.onlineBook.service;


import com.luxprojects.onlineBook.dto.CategoryResponseDTO;
import com.luxprojects.onlineBook.dto.CategoryResquestDTO;
import com.luxprojects.onlineBook.entity.Category;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CategoryService {

	public Flux<CategoryResponseDTO> getAllCategory();
	public Mono<CategoryResponseDTO> getCategoryById(Long id);
	public Mono<CategoryResponseDTO> addCategory(Mono<CategoryResquestDTO> categoryResquestDTO);
	public Mono<CategoryResponseDTO> updateCategory(Mono<CategoryResquestDTO> categoryResquestDTO, Long id);
	public Mono<Void> deleteCategory(Long id);
	public Mono<Category> findCategoryById(Long id);
}
