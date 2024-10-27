package com.luxprojects.onlineBook.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AuthorRequestDTO {

	@NotNull(message = "First name cannot be null")
	@Size(min = 3, message = "First name must be containt 3 words min")
	private String firstName;
	private String lastName;
	private LocalDate bornDate;
	private String picture;
}
