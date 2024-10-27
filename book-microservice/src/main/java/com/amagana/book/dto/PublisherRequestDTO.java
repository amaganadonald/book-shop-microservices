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
public class PublisherRequestDTO {

	private String[] booksId;
	private Long libraryId;
	private LocalDateTime publishDate;
	private String observation;
}
