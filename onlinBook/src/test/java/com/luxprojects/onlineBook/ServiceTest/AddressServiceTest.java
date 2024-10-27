package com.luxprojects.onlineBook.ServiceTest;


import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.luxprojects.onlineBook.dto.AddressRequestDTO;
import com.luxprojects.onlineBook.dto.AddressResponseDTO;
import com.luxprojects.onlineBook.entity.Address;
import com.luxprojects.onlineBook.mapper.AddressMapper;
import com.luxprojects.onlineBook.repository.AddressRepository;
import com.luxprojects.onlineBook.service.Impl.AddressServiceImpl;

@ExtendWith(MockitoExtension.class)
class AddressServiceTest {

	@Mock
	AddressRepository addressRepository;
	
	@InjectMocks
	AddressServiceImpl addressService;
		
	AddressRequestDTO addressRequestDTO;
	AddressResponseDTO addressResponseDTO;
	Address address;
	
	@BeforeEach
	void setUp() throws Exception {
		mockStatic(AddressMapper.class);
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
		address = Address.builder()
				.addressName("Jean Huberty")
				.addressCity("Mulhenbachh")
				.addressNumber(37)
				.id(1L)
				.build();
	}

	/*@Test
	void givenAddressRequestDTO_whenSave_ThenReturAddressSave() {
		when(AddressMapper.addressDtoToAddress(addressRequestDTO)).thenReturn(address);
		when(AddressMapper.addressToAddressResponseDTO(address)).thenReturn(addressResponseDTO);
		//when(addressRepository.save(address)).thenReturn(address);
		AddressResponseDTO addressResponseDTOSave = addressService.addAddress(addressRequestDTO);
		assertEquals("Mulhenbachh", addressResponseDTOSave.getAddressName());
		assertEquals(37, addressResponseDTOSave.getAdressNumber());
	}*/

}
