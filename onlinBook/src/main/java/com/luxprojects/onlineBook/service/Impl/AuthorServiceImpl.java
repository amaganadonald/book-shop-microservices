package com.luxprojects.onlineBook.service.Impl;

import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import com.luxprojects.onlineBook.dto.AuthorRequestDTO;
import com.luxprojects.onlineBook.dto.AuthorResponseDTO;
import com.luxprojects.onlineBook.exception.EntityNotFoundException;
import com.luxprojects.onlineBook.mapper.AuthorMapper;
import com.luxprojects.onlineBook.repository.AuthorRepository;
import com.luxprojects.onlineBook.service.AuthorService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;
	
	@Override
	public Flux<AuthorResponseDTO> getAllAuthors() {
		return authorRepository.findAll()
				.map(AuthorMapper::authorToAuthorResponseDTO);
	}

	@Override
	public Mono<AuthorResponseDTO> getAuthorById(Long id) {
		return authorRepository.findById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Author not found with id"+id)))
				.map(AuthorMapper::authorToAuthorResponseDTO);
	}

	@Override
	public Mono<AuthorResponseDTO> addAuthor(Mono<AuthorRequestDTO> authorRequestDTO) {
		return authorRequestDTO.map(AuthorMapper::authorRequestDTOToAuthor)
				.flatMap(authorRepository::save)
				.map(AuthorMapper::authorToAuthorResponseDTO);
	}

	@Override
	public Mono<AuthorResponseDTO> updateAuthor(Mono<AuthorRequestDTO> authorRequestDTO, Long id) {
		return authorRepository.findById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Author not found with id"+id)))
				.flatMap(author->
					authorRequestDTO.map(AuthorMapper::authorRequestDTOToAuthor)
					.doOnNext(a->a.setId(id)))
				.flatMap(authorRepository::save)
				.map(AuthorMapper::authorToAuthorResponseDTO);
	}

	@Override
	public Mono<Void> deleteAuthor(Long id) {
		return authorRepository.deleteById(id);
	}

	@Override
	public Mono<String> addAuthorPicture(FilePart filePart) {
		/*return filePart.content().map(dataBuffer->{
			byte[] bytes = new byte[dataBuffer.readableByteCount()];
			dataBuffer.read(bytes);
			return "";
		});*/
		return Mono.just("File");
	}
	

}
