package com.amagana.book.mappers;

import org.modelmapper.ModelMapper;

import com.amagana.book.dto.AuthorRequestDTO;
import com.amagana.book.dto.AuthorResponseDTO;
import com.amagana.book.entity.Author;


public class AuthorMapper {

	public static Author authorRequestDTOToAuthor(AuthorRequestDTO authorRequestDTO) {
		System.out.println(authorRequestDTO);
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(authorRequestDTO, Author.class);
	}
	
	public static AuthorResponseDTO authorToAuthorResponseDTO(Author author) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(author, AuthorResponseDTO.class);
	}

}
