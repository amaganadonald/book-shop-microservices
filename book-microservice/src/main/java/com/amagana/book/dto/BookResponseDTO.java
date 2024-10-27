package com.amagana.book.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.amagana.book.entity.Author;
import com.amagana.book.models.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BookResponseDTO {
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
	private List<Author> author;
	
}
