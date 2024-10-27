package com.luxprojects.onlineBook.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.luxprojects.onlineBook.enums.StatusError;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErrorsResponse {

	private StatusError status;
	private List<ErrorsDTO> errors;

	
}
