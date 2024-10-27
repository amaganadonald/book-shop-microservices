package com.luxprojects.onlineBook.entity;


import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "Book")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Book {
	
	@Id
	private Long id;
	@Column(value="book_name")
	private String bookName;
	@Column(value="isbn")
	private String isbn;
	@Column(value="outDate")
	private LocalDateTime outDate;
	@Column(value="authorId")
	private int authorId;
	@Transient
	private Author author;
	@Column(value="is_available")
	private Boolean isAvailable;
	@Column(value="price")
	private Double price;
	@CreatedDate
	@Column(value = "createdat")
	private LocalDateTime createdAt;
	@CreatedBy
	@Column(value="createdby")
	private String createdBy;
	@LastModifiedBy
	@Column(value ="lastupdatedby")
	private String lastUpdatedBy;
	@LastModifiedDate
	@Column(value = "lastupdatedate")
	private LocalDateTime lastUpdateDate;
	@Column(value="libraryId")
	private int libraryId;
	@Transient
	private Library library;
	@Column(value="categoryId")
	private int categoryId;
	@Transient
	private Category category;

}
