package com.luxprojects.onlineBook.entity;



import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Embedded;
import org.springframework.data.relational.core.mapping.Embedded.OnEmpty;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Address implements Persistable<Long>{

	@Id
	private Long id;
	private String addressName;
	private String addressCity;
	private int addressNumber;
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

	@Transient
	private boolean newAddress;
	@Override
	@Transient
	public boolean isNew() {
		return this.newAddress || id == null;
	}
	public Address setAsNew() {
		this.newAddress = true;
		return this;
	}
	
    
}
