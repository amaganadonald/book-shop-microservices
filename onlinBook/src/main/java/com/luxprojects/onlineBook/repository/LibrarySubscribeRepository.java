package com.luxprojects.onlineBook.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.luxprojects.onlineBook.entity.LibrarySubscribes;


@Repository
public interface LibrarySubscribeRepository extends ReactiveCrudRepository<LibrarySubscribes, Long> {

}
