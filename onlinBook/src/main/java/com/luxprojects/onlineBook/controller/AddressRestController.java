package com.luxprojects.onlineBook.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.luxprojects.onlineBook.dto.AddressRequestDTO;
import com.luxprojects.onlineBook.dto.AddressResponseDTO;
import com.luxprojects.onlineBook.service.AddressService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/address")
@Slf4j
public class AddressRestController {
	
	private AddressService addressService;
	

	@GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Flux<AddressResponseDTO> getAllAddress() {
		log.info("AddressController::getAllAddress send info to service to retrieves all address");
		return addressService.getAllAddress();
	}
	
	@GetMapping("/{id}")
	public Mono<ResponseEntity<AddressResponseDTO>> getAddressById(@PathVariable Long id) {
		return addressService.getAddressById(id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Mono<AddressResponseDTO> addAddress(@RequestBody @Valid Mono<AddressRequestDTO> addressRequestDTO) {
		log.info("AddressController::addAddress begin to add address");
		return addressService.addAddress(addressRequestDTO);
	}
	
	@PutMapping("/{id}")
	public Mono<ResponseEntity<AddressResponseDTO>> updateAddress(@PathVariable Long id, @RequestBody @Valid Mono<AddressRequestDTO> addressRequestDTO) {
		return addressService.updateAddress(addressRequestDTO, id)
				.map(ResponseEntity::ok)
				.defaultIfEmpty(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public Mono<Void> deleteAddress(@PathVariable Long id) {
		return addressService.deleteAddress(id);
	}
	/*@GetMapping
	public ResponseEntity<ApiResponse> getAllAddress() {
		log.info("AddressRestController:getAllAddress starting call service for retrieving all Address");
		List<AddressResponseDTO> addressResponseDTO = iAddress.getAllAddress();
		log.info("AddressRestController:getAllAddress start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusError.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:getAllAddress end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}
	
	@GetMapping("/page")
	public ResponseEntity<ApiResponse<?>> getAllAddressByPage(@RequestParam(defaultValue="0") int page, @RequestParam(defaultValue = "10") int size) {
		log.info("AddressRestController:getAllAddressByPage starting call service for retrieving Address by page size");
		Page<AddressResponseDTO> addressResponseDTO = iAddress.getAllAddressPage(page, size);
		log.info("AddressRestController:getAllAddressByPage start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusError.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:getAllAddressByPage end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponse> getAddressById(@PathVariable Long id) {
		log.info("AddressRestController:getAddressById starting call service for retrieving addresse with id {}", id);
		AddressResponseDTO addressResponseDTO = iAddress.getAddressById(id);
		log.info("AddressRestController:getAddressById start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusError.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:getAddressById end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<ApiResponse> addAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO) {
		log.info("AddressRestController:addAddress starting call service for add  Address");
		AddressResponseDTO addressResponseDTO = iAddress.addAddress(addressRequestDTO);
		log.info("AddressRestController:addAddress start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusError.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:addAddress end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponse> updateAddress(@RequestBody @Valid AddressRequestDTO addressRequestDTO ,@PathVariable Long id) {
		log.info("AddressRestController:updateAddress starting call service for updated  Address with id {}", id);
		AddressResponseDTO addressResponseDTO = iAddress.updateAddress(addressRequestDTO, id);
		log.info("AddressRestController:updateAddress start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusError.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:updateAddress end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteAddress(@PathVariable Long id) {
		log.info("AddressRestController:deleteAddress starting call service for delete Address with id {}", id);
		String addressResponseDTO = iAddress.deleteAddress(id);
		log.info("AddressRestController:deleteAddress start contruction response");
		ApiResponse<?> apiResponse = ApiResponse.builder()
				.status(StatusError.SUCCESS)
				.results(addressResponseDTO)
				.build();
		log.info("AddressRestController:deleteAddress end contruction response");
		return new ResponseEntity<>(apiResponse, HttpStatus.OK);
	}*/

	
	

}
