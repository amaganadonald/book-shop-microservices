package com.luxprojects.onlineBook.handler;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.luxprojects.onlineBook.dto.AddressRequestDTO;
import com.luxprojects.onlineBook.dto.AddressResponseDTO;
import com.luxprojects.onlineBook.entity.Address;
import com.luxprojects.onlineBook.service.AddressService;
import com.luxprojects.onlineBook.service.Impl.AddressServiceImpl;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class AddressHandler {
	
	private AddressService addressService;
	
	public Mono<ServerResponse> allAddress(ServerRequest serverRequest) {
		Flux<AddressResponseDTO> address = addressService.getAllAddress();
		return ServerResponse.ok()
				.contentType(MediaType.TEXT_EVENT_STREAM)
				.body(address, Address.class);
	}
	
	public Mono<ServerResponse> addAddress(ServerRequest serverRequest) {
	    Mono<AddressRequestDTO> addressRequestDTO =	serverRequest.bodyToMono(AddressRequestDTO.class);
	    Mono<AddressResponseDTO> addressResponseDTO = addressService.addAddress(addressRequestDTO);
	    return ServerResponse.ok()
	    		 .body(addressResponseDTO, AddressResponseDTO.class);
	}
	
	public Mono<ServerResponse> getAddressById(ServerRequest serverRequest) {
		Long id = Long.valueOf(serverRequest.pathVariable("id"));
		Mono<AddressResponseDTO> addressResponse = addressService.getAddressById(id);
		return ServerResponse.ok().body(addressResponse, AddressResponseDTO.class);
	}
	
	public Mono<ServerResponse> updateAddress(ServerRequest serverRequest) {
		Long id = Long.valueOf(serverRequest.pathVariable("id"));
		Mono<AddressRequestDTO> addressRequestDTO = serverRequest.bodyToMono(AddressRequestDTO.class);
		Mono<AddressResponseDTO> addressResponseDTO = addressService.updateAddress(addressRequestDTO, id);
		return ServerResponse.ok().body(addressResponseDTO, AddressResponseDTO.class);
	}
	
	public Mono<ServerResponse> deleteAddress(ServerRequest serverRequest) {
		Long id = Long.valueOf(serverRequest.pathVariable("id"));
		addressService.deleteAddress(id);
		return ServerResponse.ok().bodyValue("Address deleted");
	}

}
