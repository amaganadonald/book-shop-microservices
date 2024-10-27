package com.amagana.book.models;

import com.amagana.book.enums.Type;

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
public class Dish {

	private String name;
	private Boolean isAvailable;
	private int price;
	private Type type;
}
