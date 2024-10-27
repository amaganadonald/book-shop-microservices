package com.amagana.librarymicroservice.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.amagana.librarymicroservice.clients.SettingsService;
import com.amagana.librarymicroservice.dto.LibraryRequestDTO;
import com.amagana.librarymicroservice.dto.LibraryResponseDTO;
import com.amagana.librarymicroservice.entity.Library;
import com.amagana.librarymicroservice.exceptions.EntityNotFoundException;
import com.amagana.librarymicroservice.mappers.LibraryMapper;
import com.amagana.librarymicroservice.models.Address;
import com.amagana.librarymicroservice.repository.LibraryRepository;
import com.amagana.librarymicroservice.services.LibraryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class LibraryServiceImpl implements LibraryService {

	private final LibraryRepository libraryRepository;
	private final SettingsService settingsService;
	private static final String FAILEDMESSAGE = "Failed to fetch Address from the API with message";
	
	@Override
	@Cacheable("library")
	public List<LibraryResponseDTO> getAllLibrary() {
		List<Library> library = libraryRepository.findAll();
		library.forEach(lb->{
			try {
				lb.setAddress(settingsService.findAddressById(lb.getAddressId()));
			} catch (Exception e) {
				log.error(FAILEDMESSAGE +" {}", e.getMessage());
			}
		});
		return library.stream()
				.map(LibraryMapper::libraryToBookResponseDTO)
				.toList();
	}
	
	
	public Library findLibraryById(Long id) {
		Library library = libraryRepository.findById(id).orElseThrow(()->
		   new EntityNotFoundException("Library with id {} not found "+ id));
		try {
			library.setAddress(settingsService.findAddressById(library.getAddressId()));
		} catch (Exception e) {
			log.error(FAILEDMESSAGE +" {}", e.getMessage());
		}
		return library;
	}

	@Override
	public LibraryResponseDTO getLibraryById(Long id) {
		return LibraryMapper.libraryToBookResponseDTO(findLibraryById(id));
	}

	@Override
	@Caching(cacheable = {
			@Cacheable("library")
	})
	public LibraryResponseDTO addLibrary(LibraryRequestDTO libraryRequestDTO) {
		Address address = new Address();
		try {
			address = settingsService.findAddressById(libraryRequestDTO.getAddressId());
		} catch (Exception e) {
			log.error(FAILEDMESSAGE +" {}", e.getMessage());
		}
			Library library = LibraryMapper.libraryRequestDTOToLibrary(libraryRequestDTO);
			library.setAddress(address);
			return LibraryMapper.libraryToBookResponseDTO(
					libraryRepository.save(library));
	}

	@Override
	@CachePut("library")
	public LibraryResponseDTO updateLibrary(LibraryRequestDTO libraryRequestDTO, Long id) {
		Optional<Library> library = libraryRepository.findById(id);
		Address address = new Address();
		if(library.isPresent()) {
			try {
				address = settingsService.findAddressById(libraryRequestDTO.getAddressId());
			} catch (Exception e) {
				log.error(FAILEDMESSAGE +" {}", e.getMessage());
			}
			if(address != null) {
				library.get().setAddress(address);
				library.get().setLibraryName(libraryRequestDTO.getLibraryName());
				library.get().setLibraryDescription(libraryRequestDTO.getLibraryDescription());
				library.get().setLibraryEmail(libraryRequestDTO.getLibraryEmail());
				library.get().setAddressId(libraryRequestDTO.getAddressId());
				return LibraryMapper.libraryToBookResponseDTO(
						libraryRepository.save(library.get())
				);
			} else {
				throw new EntityNotFoundException("Address not found with number " +libraryRequestDTO.getAddressId());
			}
			
		} else {
			throw new EntityNotFoundException("Library not found with id " + id );
		}
	}

	@Override
	@CacheEvict("library")
	public void deleteLibrary(Long id) {
		libraryRepository.delete(findLibraryById(id));
	}

}
