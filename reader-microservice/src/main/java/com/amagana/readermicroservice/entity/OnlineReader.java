package com.amagana.readermicroservice.entity;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import com.amagana.readermicroservice.model.Book;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OnlineReader {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String readerName;
	private String readerPhone;
	private String readerEmail;
	private String readerAddress;
    @CreatedDate
	private LocalDateTime createdAt;
	@CreatedBy
	private String createdBy;
	@LastModifiedBy
	private String lastUpdatedBy;
	@LastModifiedDate
	private LocalDateTime lastUpdateDate;
    @Transient
	private Set<Book> books;
    @Transient
    Set<LibrarySubscriber> librarySubscribes;
	
}
