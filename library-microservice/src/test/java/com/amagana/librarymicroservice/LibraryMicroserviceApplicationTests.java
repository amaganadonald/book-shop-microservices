package com.amagana.librarymicroservice;

import com.amagana.librarymicroservice.dto.LibraryRequestDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.PostgreSQLContainer;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class LibraryMicroserviceApplicationTests {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	static final PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres");
	@DynamicPropertySource
	static void configureProperties(DynamicPropertyRegistry registry) {
		registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
		registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
		registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
	}
	@BeforeAll
	static void beforeAll() {
		postgreSQLContainer.start();
	}

	@AfterAll
	static void afterAll() {
		postgreSQLContainer.stop();
	}

	@Test
	@Order(1)
	void addNewLibraryTest() throws Exception {
		LibraryRequestDTO libraryRequestDTO = LibraryRequestDTO.builder()
				.addressId(1L)
				.bookId(2L)
				.createdAt(LocalDateTime.now())
				.lastUpdateDate(LocalDateTime.now())
				.lastUpdatedBy("Evrard")
				.libraryDescription("Papetery nono")
				.libraryEmail("donald9do@gmail.com")
				.libraryName("Library nono")
				.libraryPhone("237680072220")
				.build();
		mockMvc.perform(post("/api/v1/library")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(libraryRequestDTO))
						.accept("application/json"))
				.andExpect(status().isCreated())
				.andDo(print())
				.andExpect(MockMvcResultMatchers.jsonPath("$.results.id").exists());
	}
}
