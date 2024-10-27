package com.amagana.book.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.amagana.book.models.Category;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient("SETTINGS-SERVICE")
public interface CategoryService {

	@GetMapping("/api/v1/Category/fgc/{id}")
	@CircuitBreaker(name="CategoryService", fallbackMethod = "getDefaultCategory")
	public Category getCategoryById(Long id);
	
	default Category getDefaultCategory(Exception e) {
		return Category.builder()
				.categoryName("Category name not available please try again")
				.categoryDescription("Category description not available please try again")
				.id(null)
				.build();
	}
}
