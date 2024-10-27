package com.amagana.librarymicroservice.controllers;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amagana.librarymicroservice.dto.LibraryRequestDTO;
import com.amagana.librarymicroservice.dto.LibraryResponseDTO;
import com.amagana.librarymicroservice.enums.StatusResponse;
import com.amagana.librarymicroservice.models.LibraryApiResponse;
import com.amagana.librarymicroservice.services.LibraryService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/v1/library")
@AllArgsConstructor
public class LibraryRestController {

public  LibraryService libraryService;
	
	@GetMapping
	public ResponseEntity<LibraryApiResponse<List<LibraryResponseDTO>>> getAllLibrary() {
		List<LibraryResponseDTO> libraryResponseDTOS = libraryService.getAllLibrary();
		return ResponseEntity.status(HttpStatus.OK).body(LibraryApiResponse
				.listResponse(StatusResponse.SUCCESS, libraryResponseDTOS));
	}
	
	@PostMapping
	public ResponseEntity<LibraryApiResponse<LibraryResponseDTO>> addLibrary(@RequestBody LibraryRequestDTO libraryRequestDTO) {
		LibraryResponseDTO libraryResponseDTO = libraryService.addLibrary(libraryRequestDTO);
		return new ResponseEntity<>(LibraryApiResponse.singleResponse(StatusResponse.SUCCESS, libraryResponseDTO), HttpStatus.CREATED);

	}
	
	@GetMapping("/{id}")
	public ResponseEntity<LibraryApiResponse<LibraryResponseDTO>> getLibraryById(@PathVariable Long id) {
		LibraryResponseDTO libraryResponseDTO = libraryService.getLibraryById(id);
		return ResponseEntity.status(HttpStatus.FOUND).body(LibraryApiResponse
				.singleResponse(StatusResponse.SUCCESS, libraryResponseDTO));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<LibraryApiResponse<LibraryResponseDTO>> updateLibrary(@RequestBody LibraryRequestDTO libraryRequestDTO,
			                                         @PathVariable Long id) {
		LibraryResponseDTO libraryResponseDTO = libraryService.updateLibrary(libraryRequestDTO, id);
		return ResponseEntity.status(HttpStatus.OK).body(LibraryApiResponse
				.singleResponse(StatusResponse.SUCCESS, libraryResponseDTO));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<LibraryApiResponse<LibraryResponseDTO>> deleteLibrary(@PathVariable Long id) {
		libraryService.deleteLibrary(id);
		return ResponseEntity.status(HttpStatus.OK).body(LibraryApiResponse
				.message(StatusResponse.SUCCESS, "Library deleted successfully with id "+id));
	}
	
	@GetMapping("/fgl/{id}")
	public LibraryResponseDTO findLibraryId(@PathVariable Long id) {
		return libraryService.getLibraryById(id);
	}
}
