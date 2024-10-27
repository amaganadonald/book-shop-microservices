package com.luxprojects.onlineBook.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "OnlineReader")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@EqualsAndHashCode(callSuper = false)
@ToString
public class OnlineReader {
	@Id
	private Long id;
	@Column(value="reader_name")
	private String readerName;
    @Column(value="reader_email")
	private String readerEmail;
    @Column(value="reader_phone")
	private String readerPhone;
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
    @MappedCollection
    @Transient
	private Set<Book> books;
    @Transient
    Set<LibrarySubscribes> librarySubscribes;
}
