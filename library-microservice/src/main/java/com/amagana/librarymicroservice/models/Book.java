package com.amagana.librarymicroservice.models;

import java.util.function.Consumer;

public class Book {

	public static void main(String[] args) {
		Consumer<String> sc = c -> System.out.println(c);
		sc.accept("hello world");
	}
}
