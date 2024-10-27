package com.amagana.book.dto;

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
public class BookRequestDTO {

	private String bookName;
	private String isbn;
	private LocalDateTime outDate;
	private Boolean isAvailable;
	private Double price;
	private LocalDateTime createdAt;
	private String createdBy;
	private String lastUpdatedBy;
	private LocalDateTime lastUpdateDate;
	private Long categoryId;
	private String[] authorId;
}
