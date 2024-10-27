package com.luxprojects.onlineBook.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.luxprojects.onlineBook.entity.Author;

@Repository
public interface AuthorRepository extends ReactiveCrudRepository<Author, Long> {

}
