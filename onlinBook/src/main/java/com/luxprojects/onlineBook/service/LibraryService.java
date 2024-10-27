package com.luxprojects.onlineBook.service;



import com.luxprojects.onlineBook.dto.LibraryRequestDTO;
import com.luxprojects.onlineBook.dto.LibraryResponseDTO;
import com.luxprojects.onlineBook.entity.Library;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface LibraryService {

	public Flux<LibraryResponseDTO> getAllLibrary();
	public Mono<LibraryResponseDTO> getLibraryById(Long id);
	public Mono<LibraryResponseDTO> addLibrary(Mono<LibraryRequestDTO> libraryRequestDTO);
	public Mono<LibraryResponseDTO> updateLibrary(Mono<LibraryRequestDTO> libraryRequestDTO, Long id);
	public Mono<Void> deleteLibrary(Long id);
	public Mono<Library> findBookById(Long id);
}
