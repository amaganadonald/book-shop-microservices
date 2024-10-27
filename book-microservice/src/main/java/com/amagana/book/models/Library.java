package com.amagana.book.models;

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
public class Library {

	private Long id;
	private String libraryName;
	private String libraryDescription;
	private String libraryEmail;
	private String libraryPhone;
	private Address address;
}
