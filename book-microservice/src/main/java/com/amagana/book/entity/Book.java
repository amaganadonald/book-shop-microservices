package com.amagana.book.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.amagana.book.models.Category;

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
public class Book {

	@Id
	private String id;
	
	private String bookName;
	
	private String isbn;
	
	private LocalDateTime outDate;
	
	private Boolean isAvailable;
	
	private Double price;
	
	private LocalDateTime createdAt;
	
	private String createdBy;
	
	private String lastUpdatedBy;
	
	private LocalDateTime lastUpdateDate;
	
	private Category category;
	
	private List<Author> author = new ArrayList<>();
}
