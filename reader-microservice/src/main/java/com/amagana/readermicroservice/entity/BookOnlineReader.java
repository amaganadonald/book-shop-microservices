package com.amagana.readermicroservice.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.amagana.readermicroservice.model.Book;

import jakarta.persistence.Transient;
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
public class BookOnlineReader {

	private String id;
	private OnlineReader onlineReader;
	private List<String> bookIds;
	@Transient
	private List<Book> book;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
}
