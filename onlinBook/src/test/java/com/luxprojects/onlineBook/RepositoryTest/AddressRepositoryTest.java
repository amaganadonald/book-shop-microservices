package com.luxprojects.onlineBook.RepositoryTest;


import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class AddressRepositoryTest {
	
	/*@Mock
	AddressRepository addressRepository;
	
	Address address;

	@BeforeEach
	void setUp() throws Exception {
		 address = Address.builder()
				.addressName("Jean Pierre Huberty")
				.addressCity("Mulhenbach")
				.adressNumber(37)
				.id(1L)
				.build();
	}

	@Test
	@DisplayName("JUnit test for save Address operation")
	void givenAddressObject_whenSave_ThenReturnSaveAddress() {
		when(addressRepository.save(address)).thenReturn(address);
		Address saveAddress = addressRepository.save(address);
		assertEquals("Jean Pierre Huberty", saveAddress.getAddressName());
		assertEquals(37, saveAddress.getAdressNumber());
	}
	
	@Test
	@DisplayName("JUnit test for get All Address operation")
	void givenAddressObject_whenFindAll_ThenReturnAllAddress() {
		List<Address> add = List.of(address);
		when(addressRepository.findAll()).thenReturn(add);
		List<Address> saveAddress = addressRepository.findAll();
		assertEquals(1, saveAddress.size());
		assertNotNull(saveAddress);;
	}
	
	@Test
	@DisplayName("JUnit test for get All Address by id")
	void givenAddressObject_whenFindById_ThenReturnAddress() {
		when(addressRepository.findById(1L)).thenReturn(Optional.of(address));
		Optional<Address> saveAddress = addressRepository.findById(1L);
		assertEquals("Jean Pierre Huberty", saveAddress.get().getAddressName());
		assertNotNull(saveAddress);;
		assertTrue(saveAddress.isPresent());
	}
  */
}

