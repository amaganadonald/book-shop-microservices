package com.amagana.book.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.book.dto.PublisherRequestDTO;
import com.amagana.book.dto.PublisherResponseDTO;
import com.amagana.book.service.PublisherService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/publish")
@AllArgsConstructor
public class PublisherController {

	private PublisherService publisherService;
	
	@GetMapping
	public Flux<PublisherResponseDTO> getAllPublisher() {
		return publisherService.getAllPublisher();
	}
	
	@PostMapping
	public Mono<PublisherResponseDTO> addPublisher(@RequestBody PublisherRequestDTO publisherRequestDTO) {
		return publisherService.publishBook(publisherRequestDTO);
	}
}
