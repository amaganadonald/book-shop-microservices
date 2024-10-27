package com.amagana.librarymicroservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amagana.librarymicroservice.entity.Library;

@Repository
public interface LibraryRepository extends JpaRepository<Library, Long> {

}
