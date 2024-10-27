package com.amagana.book.exceptions;

public class EntityNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3638559857485820094L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
