package com.luxprojects.onlineBook.mapper;

import java.time.LocalDateTime;
import java.util.function.BiFunction;

import com.luxprojects.onlineBook.dto.LibraryResponseDTO;
import com.luxprojects.onlineBook.entity.Address;

import io.r2dbc.spi.Row;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class LibrarySqlMapper implements BiFunction<Row, Object, LibraryResponseDTO>  {

	@Override
	public LibraryResponseDTO apply(Row row, Object u) {
		Long id = row.get("id", Long.class);
		String libraryNmae = row.get("library_name", String.class);
		LocalDateTime createdAt = row.get("lcreatedat", LocalDateTime.class);
		String createdBy = row.get("lcreatedby", String.class);
		String lastUpdatedBy = row.get("llastupdatedby", String.class);
		LocalDateTime lastUpdateDate = row.get("llastupdatedate", LocalDateTime.class);
		if (row.get("addressid") != null) {
			Long addressId = row.get("addressid", Long.class);
			String addressName = row.get("address_name", String.class);
			String addressCity = row.get("address_city", String.class);
			int addressNumber = row.get("address_number", Integer.class);
			LocalDateTime acreatedAt = row.get("acreatedat", LocalDateTime.class);
			String acreatedBy = row.get("acreatedby", String.class);
			String alastUpdatedBy = row.get("alastupdatedby", String.class);
			LocalDateTime alastUpdateDate = row.get("alastupdatedate", LocalDateTime.class);
			Address address = Address.builder()
					.id(addressId)
					.addressName(addressName)
					.addressCity(addressCity)
					.addressNumber(addressNumber)
					.createdAt(acreatedAt)
					.createdBy(acreatedBy)
					.lastUpdatedBy(alastUpdatedBy)
					.lastUpdateDate(alastUpdateDate)
					.build();
			return LibraryResponseDTO.builder()
					.address(address)
					.id(id)
					.libraryNmae(libraryNmae)
					.createdat(createdAt)
					.createdby(createdBy)
					.lastupdatedby(lastUpdatedBy)
					.lastupdatedate(lastUpdateDate)
					.build();
		}
		return LibraryResponseDTO.builder()
				.id(id)
				.libraryNmae(libraryNmae)
				.createdat(createdAt)
				.createdby(createdBy)
				.lastupdatedby(lastUpdatedBy)
				.lastupdatedate(lastUpdateDate)
				.build();
	}

}
