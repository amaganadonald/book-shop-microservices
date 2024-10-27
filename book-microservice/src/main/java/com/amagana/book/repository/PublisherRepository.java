package com.amagana.book.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.amagana.book.entity.Publisher;

@Repository
public interface PublisherRepository extends ReactiveMongoRepository<Publisher, String> {

}
