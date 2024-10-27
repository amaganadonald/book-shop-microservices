package com.luxprojects.onlineBook.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class Category{
	@Id
	private Long id;
	@Column(value = "category_name")
	private String categoryName;
	@Column(value="category_description")
	private String categoryDescription;
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
