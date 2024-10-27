package com.amagana.book.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class AuthorRequestDTO {

	private String firstName;
	private String lastName;
	private LocalDate bornDate;
	private String picture;
	private LocalDateTime createdAt;
	private String createdBy;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdateDate;

}
