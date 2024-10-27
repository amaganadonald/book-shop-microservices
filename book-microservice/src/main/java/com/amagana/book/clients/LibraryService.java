package com.amagana.book.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.amagana.book.models.Library;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name = "LIBRARY-SERVICE")
public interface LibraryService {

	@GetMapping("/api/v1/library/fgl/{id}")
	@CircuitBreaker(name = "LIBRARY-SERVICE", fallbackMethod = "getDefaultLibrary")
	Library getLibraryById(Long id);
	
	default Library getDefaultLibrary() {
		return Library.builder()
				.libraryName("No Content please try again!!!")
				.libraryDescription("No Content please try again!!!")
				.libraryPhone("0000000")
				.address(null)
				.build();
	}
}
