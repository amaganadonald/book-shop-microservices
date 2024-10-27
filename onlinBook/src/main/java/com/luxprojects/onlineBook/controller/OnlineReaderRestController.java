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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luxprojects.onlineBook.dto.OnlineReaderRequestDTO;
import com.luxprojects.onlineBook.dto.OnlineReaderResponseDTO;
import com.luxprojects.onlineBook.exception.OnlineBookBusinessException;
import com.luxprojects.onlineBook.service.OnlineReaderService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/onlineReader")
@AllArgsConstructor
public class OnlineReaderRestController {

	private OnlineReaderService onlineReaderService;
	
	@GetMapping
	@ResponseStatus(HttpStatus.OK)
	public Flux<OnlineReaderResponseDTO> getAllOnlineReader() {
		return onlineReaderService.getAllOnlineReader();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<OnlineReaderResponseDTO>> getOnlineReaderById(@PathVariable Long id) {
		return onlineReaderService.getOnlineReaderById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.ok().build());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<OnlineReaderResponseDTO> addOnlineRedaer(@RequestBody Mono<OnlineReaderRequestDTO>
	                                                     onlineReaderRequestDTO) {
		return onlineReaderService.addOnlineReader(onlineReaderRequestDTO);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<OnlineReaderResponseDTO>> updateOnlineReader(@PathVariable Long id,
			                                         Mono<OnlineReaderRequestDTO> onlineReaderRequestDTO) {
		return onlineReaderService.updateOnlineReader(onlineReaderRequestDTO, id)
				.map(ResponseEntity::ok)
				.doOnError(e-> new OnlineBookBusinessException("Error occur "+e.getMessage()));
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteOnlineReader(@PathVariable Long id) {
		return onlineReaderService.deleteOnlineReader(id);
	}
}
