package com.amagana.librarymicroservice.models;

import java.util.List;

import com.amagana.librarymicroservice.dto.ErrorsDTO;
import com.amagana.librarymicroservice.enums.StatusResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@Getter
@Setter
@ToString
public class LibraryApiResponse<T> {

	private StatusResponse status;
	private String message;
	private List<ErrorsDTO> errors;
	private T results;
	
	public static <T> LibraryApiResponse<T> errors(StatusResponse status, List<ErrorsDTO> errors) {
		return LibraryApiResponse.<T>builder()
				.errors(errors)
				.status(status)
				.build();
	}
	
	public static <T> LibraryApiResponse<T> singleResponse(StatusResponse status, T result) {
		return LibraryApiResponse.<T>builder()
				.status(status)
				.results(result)
				.build();
	}
	
	public static <T> LibraryApiResponse<T> message(StatusResponse status, String message) {
		return LibraryApiResponse.<T>builder()
				.message(message)
				.status(status)
				.build();
	}
	
	public static <T> LibraryApiResponse<List<T>> listResponse(StatusResponse status, List<T> results) {
		return LibraryApiResponse.<List<T>>builder()
				.results(results)
				.status(status)
				.build();
	}
	
}
