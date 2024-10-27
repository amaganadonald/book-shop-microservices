package com.amagana.book.service.Impl;


import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.amagana.book.dto.PublisherRequestDTO;
import com.amagana.book.dto.PublisherResponseDTO;
import com.amagana.book.entity.Book;
import com.amagana.book.entity.Publisher;
import com.amagana.book.exceptions.EntityNotFoundException;
import com.amagana.book.mappers.PublisherMapper;
import com.amagana.book.models.Library;
import com.amagana.book.repository.BookRepository;
import com.amagana.book.repository.PublisherRepository;
import com.amagana.book.service.PublisherService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PublisherServiceImpl implements PublisherService {

	private final PublisherRepository publisherRepository;
	private final BookRepository bookRepository;
	
	@Override
	public Flux<PublisherResponseDTO> getAllPublisher() {
		return publisherRepository.findAll()
				.map(PublisherMapper::publisherToPublisherResponseDTO);
	}
	
	public Mono<Library> getLibrary(Long libraryId) {
		return WebClient.create()
				.get()
				.uri("http://localhost:9890/LIBRARY-SERVICE/api/v1/library/fgl/"+libraryId)
				.retrieve()
				.bodyToMono(Library.class);
	}
	
	public Mono<List<Book>> getBookRange(String[] bookIds) {
		return bookRepository.getAllBookInArray(bookIds)
				.collectList();
	}

	@Override
	public Mono<PublisherResponseDTO> publishBook(PublisherRequestDTO publish) {
		return getBookRange(publish.getBooksId())
				.zipWith(getLibrary(publish.getLibraryId()))
				.flatMap(publishBook->{
					List<Book> books = publishBook.getT1();
					Library library = publishBook.getT2();
					Publisher publisher = Publisher.builder()
							.books(books)
							.library(library)
							.observation(publish.getObservation())
							.publishDate(publish.getPublishDate())
							.build();
					return publisherRepository.save(publisher);
				})
			.map(PublisherMapper::publisherToPublisherResponseDTO);
	}

	@Override
	public Mono<PublisherResponseDTO> updatePublishBook(PublisherRequestDTO publish, String id) {
		return findPublisherById(id)
				.zipWith(getLibrary(publish.getLibraryId()))
				.flatMap(publishLibrary->{
					Publisher publisher = publishLibrary.getT1();
					Library library = publishLibrary.getT2();
					publisher.setLibrary(library);
					publisher.setObservation(publish.getObservation());
					return Mono.just(publisher);
				})
				.zipWith(getBookRange(publish.getBooksId()))
				.flatMap(publishBook->{
					Publisher publisher = publishBook.getT1();
					List<Book> books = publishBook.getT2();
					publisher.setBooks(books);
					return publisherRepository.save(publisher);
				})
				.map(PublisherMapper::publisherToPublisherResponseDTO);
	}
	
	public Mono<Publisher> findPublisherById(String id) {
		return publisherRepository.findById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Publiher not found")));
	}

	@Override
	public Mono<PublisherResponseDTO> getPublisherById(String id) {
		return findPublisherById(id)
				.map(PublisherMapper::publisherToPublisherResponseDTO);
	}

	
}
