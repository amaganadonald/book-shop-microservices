package com.luxprojects.onlineBook.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.luxprojects.onlineBook.exception.handler.GlobalExceptionHandler;
import com.luxprojects.onlineBook.exception.handler.ValidationException;
import com.luxprojects.onlineBook.handler.AddressHandler;


@Configuration
public class RouterConfig {
	private static final String addressUrl="/api/address";
	
	@Bean
	public RouterFunction<ServerResponse> routerFunction(AddressHandler addressHandler) {
		return RouterFunctions.route()
				.GET(addressUrl, addressHandler::allAddress)
				.GET(addressUrl+"/{id}", addressHandler::getAddressById)
				.POST(addressUrl, addressHandler::addAddress)
				.PUT(addressUrl+"/{id}", addressHandler::updateAddress)
				.DELETE(addressUrl+"/{id}", addressHandler::deleteAddress)
				.build();
	}

}
