package com.luxprojects.onlineBook.dto;

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
public class FileStorageResdponseDTO {

	private Long id;
	private String name;
	private String url;
	private String type;
	private int size;
}
