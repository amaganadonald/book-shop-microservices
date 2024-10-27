package com.amagana.librarymicroservice.controllerTest;

import com.amagana.librarymicroservice.controllers.LibraryRestController;
import com.amagana.librarymicroservice.dto.LibraryRequestDTO;
import com.amagana.librarymicroservice.dto.LibraryResponseDTO;
import com.amagana.librarymicroservice.models.Address;
import com.amagana.librarymicroservice.services.LibraryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryRestController.class)
class LibraryRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LibraryService libraryService;

    @Autowired
    private ObjectMapper objectMapper;

    LibraryRequestDTO libraryRequestDTO;
    LibraryResponseDTO libraryResponseDTO;
    Address address;

    @BeforeEach
    void setUp() {
        address = Address.builder()
                .addressCity("Mulhenbach")
                .addressEmail("amaganadonald@rocketmail.com")
                .addressName("Rue huberty")
                .addressNumber(30)
                .addressPhone("237676987671")
                .id(1L)
                .build();
        libraryRequestDTO = LibraryRequestDTO.builder()
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
        libraryResponseDTO = LibraryResponseDTO.builder()
                .id(1L)
                .address(address)
                .createdAt(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .lastUpdatedBy("Evrard")
                .libraryDescription("Papetery nono")
                .libraryEmail("donald9do@gmail.com")
                .libraryName("Library nono")
                .libraryPhone("237680072220")
                .build();
    }

    @Test
    void shouldCreateLbrairyReturnNewLibrairy() throws Exception {
        when(libraryService.addLibrary(libraryRequestDTO)).thenReturn(libraryResponseDTO);
        this.mockMvc.perform(post("/api/v1/library")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(libraryRequestDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @AfterEach
    void destroy() {
        address = null;
        libraryResponseDTO = null;
        libraryRequestDTO = null;
    }
}
