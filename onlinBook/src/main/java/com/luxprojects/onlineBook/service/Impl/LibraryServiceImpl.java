package com.luxprojects.onlineBook.service.Impl;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luxprojects.onlineBook.dto.LibraryRequestDTO;
import com.luxprojects.onlineBook.dto.LibraryResponseDTO;
import com.luxprojects.onlineBook.entity.Address;
import com.luxprojects.onlineBook.entity.Library;
import com.luxprojects.onlineBook.exception.EntityNotFoundException;
import com.luxprojects.onlineBook.exception.OnlineBookBusinessException;
import com.luxprojects.onlineBook.mapper.AddressMapper;
import com.luxprojects.onlineBook.mapper.LibraryMapper;
import com.luxprojects.onlineBook.mapper.LibrarySqlMapper;
import com.luxprojects.onlineBook.repository.AddressRepository;
import com.luxprojects.onlineBook.repository.LibraryRepository;
import com.luxprojects.onlineBook.service.AddressService;
import com.luxprojects.onlineBook.service.LibraryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
@Transactional
public class LibraryServiceImpl implements LibraryService {

	private LibraryRepository libraryRepository;
	private DatabaseClient databaseClient;
	private AddressRepository addressRepository;
	
	@Override
	public Flux<LibraryResponseDTO> getAllLibrary() {
		log.info("LibraryServiceImpl::getAllLibrary start to fetch all Library");
		String query = """
				SELECT l.id as id, library_name, l.createdat as lcreatedat, l.createdby as lcreatedby,
				 l.lastupdatedby as llastupdatedby, l.lastupdatedate as llastupdatedate, 
				 addressid, address_name, address_city, address_number, a.createdat as acreatedat,
				 a.createdby as acreatedby, a.lastupdatedby as alastupdatedby, 
				 a.lastupdatedate as alastupdatedate from library_book l LEFT JOIN
				address a on a.id=l.addressId
				""";
		LibrarySqlMapper librarySqlMapper = new LibrarySqlMapper();
		return databaseClient.sql(query)
				.map(librarySqlMapper::apply)
				.all();
	}
	
	@Override
	public Mono<Library> findBookById(Long id) {
		 return libraryRepository.findById(id)
			.switchIfEmpty(Mono.error(new EntityNotFoundException("Library with id not found"+id)))
		    .flatMap(library->{
		    	if (library.getAddressId() == null) {
		    		return Mono.just(library);
		    	}
		    	return addressRepository.findById(library.getAddressId())
		    			.map(address->{
		    				library.setAddress(address);
		    				return library;
		    			});
		    });
		 }

	@Override
	public Mono<LibraryResponseDTO> getLibraryById(Long id) {
		return findBookById(id)
			    .map(LibraryMapper::libraryToBookResponseDTO);
	}
	
	public Mono<Library> saveLibrary(Library library) {
		return addressRepository.findById(library.getAddressId())
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Address not found with id "+library.getAddressId())))
				.flatMap(address->{
					library.setAddressId(address.getId());
					library.setAddress(address);
				   return libraryRepository.save(library);
				});
	}

	@Override
	public Mono<LibraryResponseDTO> addLibrary(Mono<LibraryRequestDTO> libraryRequestDTO) {
		return libraryRequestDTO.map(LibraryMapper::libraryRequestDTOToLibrary)
				.flatMap(library->{
					if(library.getAddressId() == null) 
						return libraryRepository.save(library);
					return saveLibrary(library);
				})
				.map(LibraryMapper::libraryToBookResponseDTO);
			}

	@Override
	public Mono<LibraryResponseDTO> updateLibrary(Mono<LibraryRequestDTO> libraryRequestDTO, Long id) {
		return findBookById(id)
				.flatMap(l->libraryRequestDTO.map(LibraryMapper::libraryRequestDTOToLibrary))
				         .doOnNext(lb->lb.setId(id))
				 .flatMap(library->{
				       if(library.getAddressId() == null) 
				    	   return libraryRepository.save(library);
				       return saveLibrary(library);
				   })
				.map(LibraryMapper::libraryToBookResponseDTO);
	}

	@Override
	public Mono<Void> deleteLibrary(Long id) {
		return libraryRepository.deleteById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Library not found with id "+id)));
	}

}
