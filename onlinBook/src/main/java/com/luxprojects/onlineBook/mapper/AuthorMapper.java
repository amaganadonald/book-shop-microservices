package com.luxprojects.onlineBook.mapper;

import org.modelmapper.ModelMapper;

import com.luxprojects.onlineBook.dto.AuthorRequestDTO;
import com.luxprojects.onlineBook.dto.AuthorResponseDTO;
import com.luxprojects.onlineBook.entity.Author;

public class AuthorMapper {
	
	
	public static Author authorRequestDTOToAuthor(AuthorRequestDTO authorRequestDTO) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(authorRequestDTO, Author.class);
	}

	public static AuthorResponseDTO authorToAuthorResponseDTO(Author author) {
		ModelMapper mapper = new ModelMapper();
		return mapper.map(author, AuthorResponseDTO.class);
	}
}
