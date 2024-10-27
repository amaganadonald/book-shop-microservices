package com.amagana.book.dto;

import java.time.LocalDateTime;
import java.util.List;
import com.amagana.book.entity.Book;
import com.amagana.book.models.Library;

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
public class PublisherResponseDTO {

	private String id;
	private List<Book> books;
	private Library library;
	private LocalDateTime publishDate;
	private String observation;
}
