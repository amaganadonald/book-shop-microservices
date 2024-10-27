package com.amagana.readermicroservice.model;


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
public class Library {
	private Long id;
	private String libraryNmae;
	private String libraryDescription;
	private String libraryEmail;
	private String libraryPhone;

	
}
