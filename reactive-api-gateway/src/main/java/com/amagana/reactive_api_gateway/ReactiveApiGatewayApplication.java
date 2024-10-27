package com.amagana.reactive_api_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReactiveApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReactiveApiGatewayApplication.class, args);
	}

	@Bean
	public RouteLocator bookShopRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route(p -> p
						.path("/api/v1/address/*")
						.uri("lb://SETTINGS-SERVICE"))
				.route(p->p
						.path("/api/v1/library")
						.uri("lb://LIBRARY-SERVICE")
				)
				.build();
	}
}
