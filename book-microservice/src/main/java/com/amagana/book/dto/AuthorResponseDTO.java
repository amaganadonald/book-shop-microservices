package com.amagana.book.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.amagana.book.entity.Book;

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
public class AuthorResponseDTO {

	private String id;
	private String firstName;
	private String lastName;
	private LocalDate bornDate;
	private String picture;
	private LocalDateTime createdAt;
	private String createdBy;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdateDate;
	private List<Book> bookIds;
}
