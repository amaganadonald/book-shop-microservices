package com.amagana.librarymicroservice.services;

import java.util.List;

import com.amagana.librarymicroservice.dto.LibraryRequestDTO;
import com.amagana.librarymicroservice.dto.LibraryResponseDTO;

public interface LibraryService {

	public List<LibraryResponseDTO> getAllLibrary();
	public LibraryResponseDTO getLibraryById(Long id);
	public LibraryResponseDTO addLibrary(LibraryRequestDTO libraryRequestDTO);
	public LibraryResponseDTO updateLibrary(LibraryRequestDTO libraryRequestDTO, Long id);
	public void deleteLibrary(Long id);
}
