package com.luxprojects.onlineBook.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class BaseEntity {

	@CreatedDate
	protected LocalDateTime createdAt;
	@CreatedBy
	protected String createdBy;
	@LastModifiedBy
	protected String lastUpdatedBy;
	@LastModifiedDate
	protected LocalDateTime lastUpdateDate;

}
