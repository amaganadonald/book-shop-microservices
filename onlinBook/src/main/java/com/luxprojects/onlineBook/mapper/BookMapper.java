package com.luxprojects.onlineBook.mapper;

import org.modelmapper.ModelMapper;

import com.luxprojects.onlineBook.dto.BookRequestDTO;
import com.luxprojects.onlineBook.dto.BookResponseDTO;
import com.luxprojects.onlineBook.entity.Book;

public class BookMapper {
	
	public static Book bookRequestToBook(BookRequestDTO bookRequestDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(bookRequestDTO, Book.class);
	}
	
	public static BookResponseDTO bookToBookResponseDTO(Book book) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(book, BookResponseDTO.class);
	}

}
