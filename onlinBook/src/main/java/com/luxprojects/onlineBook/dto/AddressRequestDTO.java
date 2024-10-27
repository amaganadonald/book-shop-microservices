package com.luxprojects.onlineBook.dto;

import java.time.LocalDateTime;

import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;

import com.luxprojects.onlineBook.entity.BaseEntity;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class AddressRequestDTO {
	
	@NotEmpty(message = "Address name must not be empty")
	@Size(min = 3, message = "Address name must contains at least 3 words")
	private String addressName;
	private String addressCity;
	@NotNull(message = "Address number must no be not null")
	private int addressNumber;
	private LocalDateTime createdat;
	private String createdby;
	private String lastupdatedby;
	private LocalDateTime lastupdatedate;

}
