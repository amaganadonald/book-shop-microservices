package com.amagana.librarymicroservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.amagana.librarymicroservice.models.Address;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@FeignClient(name="SETTINGS-SERVICE")
public interface SettingsService {

	@GetMapping("/api/v1/address/ofc/{id}")
	@CircuitBreaker(name = "AddressService", fallbackMethod = "getDefaultAddress")
	Address findAddressById(@PathVariable Long id) throws Exception;
	
	default Address getDefaultAddress(Exception e) {
		return Address.builder()
				.addressName("No Content try again")
				.addressNumber(0)
				.addressEmail("No Content try again")
				.addressCity("No Content try again")
				.build();
	}
}
