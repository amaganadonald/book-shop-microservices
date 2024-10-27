package com.amagana.book.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Document
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Author {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private LocalDate bornDate;
	private String picture;
	private LocalDateTime createdAt;
	private String createdBy;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdateDate;
	
	private List<Book> bookIds = new ArrayList<>();

	public void addBook(Book book) {
		this.bookIds.add(book);
    }

}
