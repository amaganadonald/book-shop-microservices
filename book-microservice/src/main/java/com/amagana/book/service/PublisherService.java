package com.amagana.book.service;

import com.amagana.book.dto.PublisherRequestDTO;
import com.amagana.book.dto.PublisherResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PublisherService {
	
	public Flux<PublisherResponseDTO> getAllPublisher();
	public Mono<PublisherResponseDTO> getPublisherById(String id);
	public Mono<PublisherResponseDTO> publishBook(PublisherRequestDTO publish);
	public Mono<PublisherResponseDTO> updatePublishBook(PublisherRequestDTO publish, String id);

}
