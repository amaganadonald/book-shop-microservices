package com.amagana.librarymicroservice.entity;

import com.amagana.librarymicroservice.models.Address;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Library implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	private Long addressId;
	private Long bookId;
	
	@Transient
	private Address address;
}
