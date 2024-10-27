package com.luxprojects.onlineBook.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.luxprojects.onlineBook.entity.Category;
import com.luxprojects.onlineBook.entity.Library;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class BookRequestDTO {
	
	private String bookName;
	private String isbn;
	private LocalDate outDate;
	private int authorId;
	private Boolean isAvailable;
	private int libraryId;
	private int categoryId;
	private LocalDateTime createdat;
	private String createdby;
	private String lastupdatedby;
	private LocalDateTime lastupdatedate;
}
