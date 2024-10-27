package com.luxprojects.onlineBook.repository;


import org.springframework.data.r2dbc.repository.Modifying;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.luxprojects.onlineBook.entity.Book;

import reactor.core.publisher.Mono;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, Long> {

	@Modifying
	@Query("""
			update book set isAvailable=!isAvailable
			where id = :id   
		  """)
	Mono<Boolean> updateBookAvailability(Long id);
}
