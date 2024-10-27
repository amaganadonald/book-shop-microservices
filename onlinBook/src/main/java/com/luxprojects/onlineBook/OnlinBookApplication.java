package com.luxprojects.onlineBook;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import com.luxprojects.onlineBook.dto.AddressRequestDTO;
import com.luxprojects.onlineBook.entity.Address;
import com.luxprojects.onlineBook.entity.BaseEntity;
import com.luxprojects.onlineBook.repository.AddressRepository;
import com.luxprojects.onlineBook.service.Impl.AddressServiceImpl;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableR2dbcAuditing
@OpenAPIDefinition(info = @Info(title="Onine Book API", version="1.0", description="Documentation APIs v1.0"))
public class OnlinBookApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlinBookApplication.class, args);
	}
	
	/*@Bean
	CommandLineRunner commandLineRunner(AddressRepository addressServiceImpl) {
		return args -> {
			for (int i = 1; i <= 100; i++) {
				addressServiceImpl.save(Address.builder()
						  .addressName("Muleh" + i)
					      .addressCity("hubertyr" + i)
					      .addressNumber(i)
					      .id((long) i)
					      .createdBy("Don")
					      .createdAt(LocalDateTime.now())
					      .lastUpdateDate(LocalDateTime.now())
					      .lastUpdatedBy("Don")
						.build()).subscribe();
			}
		};
	}*/

}
