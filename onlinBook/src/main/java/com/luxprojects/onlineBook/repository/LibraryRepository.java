package com.luxprojects.onlineBook.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.luxprojects.onlineBook.entity.Library;

@Repository
public interface LibraryRepository extends ReactiveCrudRepository<Library, Long> {

}
