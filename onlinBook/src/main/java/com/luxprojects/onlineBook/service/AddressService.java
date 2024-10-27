package com.luxprojects.onlineBook.service;


import com.luxprojects.onlineBook.dto.AddressRequestDTO;
import com.luxprojects.onlineBook.dto.AddressResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AddressService {
	
	public Flux<AddressResponseDTO> getAllAddress();
	public Mono<AddressResponseDTO> getAddressById(final Long id);
	public Mono<AddressResponseDTO> addAddress(Mono<AddressRequestDTO> addressRequestDTO);
	public Mono<AddressResponseDTO> updateAddress(Mono<AddressRequestDTO> addressRequestDTO, final Long id);
	public Mono<Void> deleteAddress(final Long id);

}
