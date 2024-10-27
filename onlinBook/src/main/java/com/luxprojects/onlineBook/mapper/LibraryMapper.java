package com.luxprojects.onlineBook.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.luxprojects.onlineBook.dto.LibraryRequestDTO;
import com.luxprojects.onlineBook.dto.LibraryResponseDTO;
import com.luxprojects.onlineBook.entity.Library;

public class LibraryMapper {

	public static Library libraryRequestDTOToLibrary(LibraryRequestDTO libraryRequestDTO) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(libraryRequestDTO, Library.class);
	}
	
	public static LibraryResponseDTO libraryToBookResponseDTO(Library library) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(library, LibraryResponseDTO.class);
	}
}
