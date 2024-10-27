package com.amagana.book.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.amagana.book.dto.BookRequestDTO;
import com.amagana.book.dto.BookResponseDTO;
import com.amagana.book.entity.Book;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BookMapper {
	
	public static Book bookRequestDTOToBook(BookRequestDTO bookRequestDTO) {
		log.info("Begin to mappe bookrequestDto to book {} ", bookRequestDTO);
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(bookRequestDTO, Book.class);
	}
	
	public static BookResponseDTO bookToBookResponseDTO(Book book) {
		log.info("Begin to mappe book to book responseDto {} ", book);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(book, BookResponseDTO.class);
	}

}
