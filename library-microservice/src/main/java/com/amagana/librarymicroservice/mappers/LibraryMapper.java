package com.amagana.librarymicroservice.mappers;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import com.amagana.librarymicroservice.dto.LibraryRequestDTO;
import com.amagana.librarymicroservice.dto.LibraryResponseDTO;
import com.amagana.librarymicroservice.entity.Library;

public class LibraryMapper {

	private static final ModelMapper modelMapper = new ModelMapper();
	
	private LibraryMapper() {
		
	}
	public static Library libraryRequestDTOToLibrary(LibraryRequestDTO libraryRequestDTO) {
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper.map(libraryRequestDTO, Library.class);
	}
	
	public static LibraryResponseDTO libraryToBookResponseDTO(Library library) {
		return modelMapper.map(library, LibraryResponseDTO.class);
	}
}
