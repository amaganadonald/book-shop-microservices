package com.amagana.librarymicroservice.exceptions;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8457836858851376384L;

	public EntityNotFoundException(String message) {
		super(message);
	}
}
