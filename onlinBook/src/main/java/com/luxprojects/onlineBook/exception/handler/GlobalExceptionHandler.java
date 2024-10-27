package com.luxprojects.onlineBook.exception.handler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.luxprojects.onlineBook.dto.ApiErrorsResponse;
import com.luxprojects.onlineBook.dto.ErrorsDTO;
import com.luxprojects.onlineBook.enums.StatusError;
import com.luxprojects.onlineBook.exception.EntityNotFoundException;
import com.luxprojects.onlineBook.exception.OnlineBookBusinessException;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ValidationException.class)
	public BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
		return (err, res) -> {
			ValidationException validationException = (ValidationException) err;
			ApiErrorsResponse apiErrorsResponse = new ApiErrorsResponse();
			apiErrorsResponse.setStatus(StatusError.FAILED);
			List<ErrorsDTO> errorsDTO = Arrays.asList(new ErrorsDTO("error", validationException.getMessage()));
			apiErrorsResponse.setErrors(errorsDTO);
			return ServerResponse.badRequest().bodyValue(apiErrorsResponse);
		};
	}
	
	@ExceptionHandler(WebExchangeBindException.class)
	public ResponseEntity<ApiErrorsResponse> entitiesValidationFailed(WebExchangeBindException exception) {
		ApiErrorsResponse apiResponse =  new ApiErrorsResponse();		
		List<ErrorsDTO> errors = exception.getBindingResult().getFieldErrors().stream()
		         .map(error->new ErrorsDTO(error.getField(), error.getDefaultMessage()))
		         .toList();
		apiResponse.setStatus(StatusError.FAILED);
		apiResponse.setErrors(errors);
		log.error("Error message {} ",apiResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiErrorsResponse> entitiesValidationFailed(MethodArgumentNotValidException exception) {
		ApiErrorsResponse apiResponse =  new ApiErrorsResponse();		
		List<ErrorsDTO> errors = exception.getBindingResult().getFieldErrors().stream()
		         .map(error->new ErrorsDTO(error.getField(), error.getDefaultMessage()))
		         .toList();
		apiResponse.setStatus(StatusError.FAILED);
		apiResponse.setErrors(errors);
		log.error("Error message {} ",apiResponse);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
	}
	

	@ExceptionHandler(OnlineBookBusinessException.class)
	public ApiErrorsResponse onlineBookBusinessException(OnlineBookBusinessException exception) {
		ApiErrorsResponse serviceResponse = new ApiErrorsResponse();
		serviceResponse.setStatus(StatusError.FAILED);
		serviceResponse.setErrors(Collections.singletonList(new ErrorsDTO("", exception.getMessage())));
		return serviceResponse;
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ApiErrorsResponse entityNotFoundException(EntityNotFoundException exception) {
		ApiErrorsResponse serviceResponse = new ApiErrorsResponse();
		serviceResponse.setStatus(StatusError.FAILED);
		serviceResponse.setErrors(Collections.singletonList(new ErrorsDTO("", exception.getMessage())));
		return serviceResponse;
	}
}
