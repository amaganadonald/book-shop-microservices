package com.amagana.book.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.amagana.book.entity.Book;

import reactor.core.publisher.Flux;

@Repository
public interface BookRepository extends ReactiveMongoRepository<Book, String> {

	@Query("{'_id' : {$in: ?0}}")
	Flux<Book> getAllBookInArray(String[] books);
}
