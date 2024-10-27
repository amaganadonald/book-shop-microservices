package com.amagana.readermicroservice.model;

import java.time.LocalDateTime;

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
public class Book {
	
	private String bookName;
	
	private String isbn;
	
	private LocalDateTime outDate;
	
	private Boolean isAvailable;
	
	private Double price;
	

}
