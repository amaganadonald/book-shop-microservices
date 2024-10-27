package com.amagana.book.service.Impl;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.amagana.book.dto.AuthorRequestDTO;
import com.amagana.book.dto.AuthorResponseDTO;
import com.amagana.book.entity.Author;
import com.amagana.book.exceptions.EntityNotFoundException;
import com.amagana.book.mappers.AuthorMapper;
import com.amagana.book.repository.AuthorRepository;
import com.amagana.book.service.AuthorService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService{
	
	private  AuthorRepository authorRepository;


	@Override
	public Flux<AuthorResponseDTO> getAllAuthor() {
		return authorRepository.findAll()
				.map(AuthorMapper::authorToAuthorResponseDTO);
	}
	
	@Override
	public Mono<Author> findAuthorById(String id) {
		return authorRepository.findById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Author not found with id"+id)));
	}

	@Override
	public Mono<AuthorResponseDTO> getAuthorById(String id) {
		return findAuthorById(id)
				.map(AuthorMapper::authorToAuthorResponseDTO);
	}

	
	@Override
	public Mono<AuthorResponseDTO> addAuthor(AuthorRequestDTO authorRequest) {
		log.info("Starting add autor.....");
		return authorRepository.save(AuthorMapper.authorRequestDTOToAuthor(authorRequest))
				.map(AuthorMapper::authorToAuthorResponseDTO);
	}

	@Override
	public Mono<AuthorResponseDTO> updateAuthor(AuthorRequestDTO authorRequest, String id) {
		return findAuthorById(id)
				.flatMap(author-> {
					author.setBornDate(authorRequest.getBornDate());
					author.setCreatedAt(authorRequest.getCreatedAt());
					author.setCreatedBy(authorRequest.getCreatedBy());
					author.setFirstName(authorRequest.getFirstName());
					author.setLastName(authorRequest.getLastName());
					author.setLastUpdateDate(LocalDateTime.now());
					return authorRepository.save(author)
							.map(AuthorMapper::authorToAuthorResponseDTO);
				});
				
				
	}

	@Override
	public Mono<Void> deleteAuthor(String id) {
		return authorRepository.deleteById(id)
				.doOnError((e)-> new EntityNotFoundException("Error deleted author"+id));
	}
	

}
