package com.amagana.securityservice.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/login")
public class LoginController {

	@GetMapping
	@PreAuthorize("hasRole('client_admin')")
	public String connect() {
		return "connected";
	}
	
	@GetMapping("/log")
	public String connect2() {
		return "connected";
	}
}
