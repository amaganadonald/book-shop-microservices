package com.luxprojects.onlineBook.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
@ToString
public class Author {
	@Id
	private Long id;
	private String firstName;
	private String lastName;
	private LocalDate bornDate;
	private String picture;
	@CreatedDate
	@Column(value = "createdat")
	private LocalDateTime createdAt;
	@CreatedBy
	@Column(value="createdby")
	private String createdBy;
	@LastModifiedBy
	@Column(value ="lastupdatedby")
	private String lastUpdatedBy;
	@LastModifiedDate
	@Column(value = "lastupdatedate")
	private LocalDateTime lastUpdateDate;

}
