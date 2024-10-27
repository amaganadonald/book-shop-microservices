package com.amagana.librarymicroservice.dto;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.amagana.librarymicroservice.models.Address;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class LibraryResponseDTO {

	private Long id;
	private String libraryName;
	private String libraryDescription;
	private String libraryEmail;
	private String libraryPhone;
	@CreatedDate
	private LocalDateTime createdAt;
	@CreatedBy
	private String createdBy;
	@LastModifiedBy
	private String lastUpdatedBy;
	@LastModifiedDate
	private LocalDateTime lastUpdateDate;
	private Address address;
}
