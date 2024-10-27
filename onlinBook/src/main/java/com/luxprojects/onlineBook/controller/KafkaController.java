package com.luxprojects.onlineBook.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.luxprojects.onlineBook.config.KafkaProducer;
import com.luxprojects.onlineBook.dto.AddressRequestDTO;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/kafka")
@AllArgsConstructor
public class KafkaController {

	private KafkaProducer producer;
	
	@GetMapping("/publish/{message}")
	public ResponseEntity<?> sendMessage(@PathVariable String message) {
		try {
			producer.sendMessageProducer(message);
			return ResponseEntity.ok().body("message send successfully");
		} catch (Exception e) {
		    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
		    		.body(e.getMessage());
		}
	}
	
	@PostMapping
	public void sendAdress(@RequestBody AddressRequestDTO addressRequestDTO) {
	
		producer.publishBook(addressRequestDTO);
	}
}
