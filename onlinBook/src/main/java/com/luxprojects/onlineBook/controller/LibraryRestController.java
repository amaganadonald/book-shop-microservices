package com.luxprojects.onlineBook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luxprojects.onlineBook.dto.LibraryRequestDTO;
import com.luxprojects.onlineBook.dto.LibraryResponseDTO;
import com.luxprojects.onlineBook.exception.OnlineBookBusinessException;
import com.luxprojects.onlineBook.service.LibraryService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/library")
@AllArgsConstructor
public class LibraryRestController {

	public LibraryService libraryService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<LibraryResponseDTO> getAllLibrary() {
		return libraryService.getAllLibrary();
	}
	
	@PostMapping
	public Mono<ResponseEntity<LibraryResponseDTO>> addLibrary(@RequestBody Mono<LibraryRequestDTO> ibraryRequestDTO) {
		return libraryService.addLibrary(ibraryRequestDTO)
				.map(ResponseEntity::ok);
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<LibraryResponseDTO>> getLibraryById(@PathVariable Long id) {
		return libraryService.getLibraryById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<LibraryResponseDTO>> updateLibrary(@RequestBody Mono<LibraryRequestDTO> libraryRequestDTO,
			                                         @PathVariable Long id) {
		return libraryService.updateLibrary(libraryRequestDTO, id)
				.map(ResponseEntity::ok)
				.doOnError(e-> new OnlineBookBusinessException("Error occur with message :"+e.getMessage()));
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteLibrary(Long id) {
		return libraryService.deleteLibrary(id)
				.doOnError(e-> new OnlineBookBusinessException("Error occur with message :"+e.getMessage()));
	}
}
