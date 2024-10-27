package com.amagana.book.service.Impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.amagana.book.dto.BookRequestDTO;
import com.amagana.book.dto.BookResponseDTO;
import com.amagana.book.entity.Author;
import com.amagana.book.entity.Book;
import com.amagana.book.mappers.BookMapper;
import com.amagana.book.models.Category;
import com.amagana.book.repository.AuthorRepository;
import com.amagana.book.repository.BookRepository;
import com.amagana.book.service.BookService;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService {
	
	private static final String baseUrl = "http://localhost:9890/SETTINGS-SERVICE/api/v1";
	private final BookRepository bookRepository;
	private AuthorRepository authorRepository;
	private KafkaTemplate<String, Object> template;
	
	@Override
	public Flux<BookResponseDTO> getAllBook() {
		return bookRepository.findAll()
				.map(BookMapper::bookToBookResponseDTO);
	}

	
	@Override
	public Mono<BookResponseDTO> getBookById(String id) {
		return bookById(id)
				.map(BookMapper::bookToBookResponseDTO);
	}

	@CircuitBreaker(name="CategoryService", fallbackMethod = "getDefaultCategory")
	public Mono<Category> getCategory(Long id) {
		return WebClient.create()
				.get()
				.uri(baseUrl + "/Category/fgc/" + id)
				.retrieve()
				.bodyToMono(Category.class);
	}
	
	public Category getDefaultCategory(Exception e) {
		return Category.builder()
				.categoryName("Category name not available please try again")
				.categoryDescription("Category description not available please try again")
				.id(null)
				.build();
	}

	public Mono<List<Author>> listAuthor(String[] authorId) {
		return authorRepository.findAllAuthorByIdInArray(authorId)
				.collectList();
	}


	@Override
	public Mono<BookResponseDTO> addBook(BookRequestDTO bookRequestDTO)  {
		log.info("BookService started to addBook with value {}", bookRequestDTO.toString());
		return Mono.just(BookMapper.bookRequestDTOToBook(bookRequestDTO))
				.zipWith(getCategory(bookRequestDTO.getCategoryId()))
				.flatMap(bookCategory-> {
					Book book = bookCategory.getT1();
					Category categoryBook = bookCategory.getT2();
					book.setCategory(categoryBook);
					return Mono.just(book);
				})
				.zipWith(listAuthor(bookRequestDTO.getAuthorId()))
				.flatMap(bookAuthor-> {
					Book books = bookAuthor.getT1();
					List<Author> authors = bookAuthor.getT2();
					books.setAuthor(authors);
					return Mono.just(books);
				})
				.flatMap(bookRepository::save)
				.map(BookMapper::bookToBookResponseDTO)
				.doOnNext(book->template.send("addBook", book));
	}


	@Override
	public Mono<BookResponseDTO> updateBook(BookRequestDTO bookRequestDTO, String id) {
		return bookById(id)
				  .zipWith(getCategory(bookRequestDTO.getCategoryId()))
				  .flatMap(bookCategory->{
					  Book book = bookCategory.getT1();
					  Category categoryBook = bookCategory.getT2();
					  book.setCategory(categoryBook);					 
					  book.setBookName(bookRequestDTO.getBookName());
					  book.setCreatedAt(bookRequestDTO.getCreatedAt());
					  book.setIsbn(bookRequestDTO.getIsbn());
					  book.setIsAvailable(bookRequestDTO.getIsAvailable());
					  book.setPrice(bookRequestDTO.getPrice());
					  book.setLastUpdateDate(LocalDateTime.now());
					  book.setIsbn(bookRequestDTO.getIsbn());
					  return Mono.just(book);
				  })
				  .zipWith(listAuthor(bookRequestDTO.getAuthorId()))
					.flatMap(bookAuthor-> {
						Book books = bookAuthor.getT1();
						List<Author> authors = bookAuthor.getT2();
						books.setAuthor(authors);
						return Mono.just(books);
					})
					.flatMap(bookRepository::save)
			.map(BookMapper::bookToBookResponseDTO)
			.doOnNext(book->template.send("addBook", book));
	}


	public Mono<Book> bookById(String id) {
		return bookRepository.findById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Book with this id don't exist "+id)));
	}


	@Override
	public Mono<Void> deleteBook(String id) {
		return bookById(id)
				.flatMap(bookRepository::delete);
	}

}
