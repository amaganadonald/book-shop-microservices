package com.amagana.librarymicroservice.exceptions.handlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.amagana.librarymicroservice.dto.ErrorsDTO;
import com.amagana.librarymicroservice.enums.StatusResponse;
import com.amagana.librarymicroservice.exceptions.EntityNotFoundException;
import com.amagana.librarymicroservice.exceptions.LibraryServiceBusnnessException;
import com.amagana.librarymicroservice.models.LibraryApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<LibraryApiResponse<?>> entitiesValidationFailed(MethodArgumentNotValidException exception) {		
		List<ErrorsDTO> errors = exception.getBindingResult().getFieldErrors().stream()
		         .map(error->new ErrorsDTO(error.getField(), error.getDefaultMessage()))
		         .toList();
		LibraryApiResponse<?> apiResponse = LibraryApiResponse.builder()
				.errors(errors)
				.status(StatusResponse.FAILED)
				.message("Error occur when validation field")
				.build();
		log.error("Error message {} ",apiResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}
	

	@ExceptionHandler(LibraryServiceBusnnessException.class)
	public ResponseEntity<LibraryApiResponse<?>> onlineBookBusinessException(LibraryServiceBusnnessException exception) {
		LibraryApiResponse<?> apiResponse = LibraryApiResponse.builder()
				.status(StatusResponse.FAILED)
				.message(exception.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(apiResponse);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<LibraryApiResponse<?>> entityNotFoundException(EntityNotFoundException exception) {
		LibraryApiResponse<?> apiResponse = LibraryApiResponse.builder()
				.status(StatusResponse.FAILED)
				.message(exception.getMessage())
				.build();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
	}

}
