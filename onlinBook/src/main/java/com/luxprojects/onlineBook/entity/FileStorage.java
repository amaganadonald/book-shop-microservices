package com.luxprojects.onlineBook.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table("fileStorage")
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FileStorage {

	@Id
	private Long id;
	private String name;
	private String url;
	private String type;
	private int size;
	
}
