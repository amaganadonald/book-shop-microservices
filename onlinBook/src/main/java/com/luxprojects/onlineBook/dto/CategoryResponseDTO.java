package com.luxprojects.onlineBook.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Builder
public class CategoryResponseDTO {

	private Long id;
	private String categoryName;
	private String categoryDescription;
	private LocalDateTime createdat;
	private String createdby;
	private String lastupdatedby;
	private LocalDateTime lastupdatedate;
}
