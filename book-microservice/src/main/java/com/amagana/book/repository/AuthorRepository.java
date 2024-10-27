package com.amagana.book.repository;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.amagana.book.entity.Author;

import reactor.core.publisher.Flux;

@Repository
public interface AuthorRepository extends ReactiveMongoRepository<Author, String> {
	
	@Query("{'_id' : {$in: ?0 }}")
	Flux<Author> findAllAuthorByIdInArray(String[] authorId);

}
