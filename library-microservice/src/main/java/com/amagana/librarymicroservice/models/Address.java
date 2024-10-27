package com.amagana.librarymicroservice.models;

import java.time.LocalDateTime;

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
public class Address {

	private Long id;
	private String addressName;
	private String addressCity;
	private int addressNumber;
	private String addressEmail;
	private String addressPhone;
	private String addressProfessionalPhone;
	private LocalDateTime createdat;
	private String createdby;
	private String lastupdatedby;
	private LocalDateTime lastupdatedate;
}
