package com.luxprojects.onlineBook.ControllerTest;


import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.luxprojects.onlineBook.controller.AddressRestController;
import com.luxprojects.onlineBook.dto.AddressRequestDTO;
import com.luxprojects.onlineBook.dto.AddressResponseDTO;
import com.luxprojects.onlineBook.service.AddressService;

@WebMvcTest(AddressRestController.class)
@AutoConfigureWebMvc
class AddressRestControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private AddressService iAddress;
	
	AddressRequestDTO addressRequestDTO;
	AddressResponseDTO addressResponseDTO;
	
	@BeforeEach
	void setUp() throws Exception {
		addressRequestDTO =	AddressRequestDTO.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.build();
		addressResponseDTO = AddressResponseDTO.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.id(1L)
				.build();
	}

	/*@Test
	void givenId_whenFindAddressById_thenreturnAddress() throws Exception {
		when(iAddress.getAddressById(1L)).thenReturn(addressResponseDTO);
		mockMvc.perform(get("/id"))
		      .andExpect(status().isOk())
		      .andExpect(jsonPath("$.status").value("SUCCESS"));
	}**/

}
