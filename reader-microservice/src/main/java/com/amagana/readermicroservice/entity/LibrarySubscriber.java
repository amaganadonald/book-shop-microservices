package com.amagana.readermicroservice.entity;

import java.time.LocalDateTime;

import com.amagana.readermicroservice.model.Library;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LibrarySubscriber {

	@Id
	private Long id;
	private Long libraryId;
	@Transient
	private Library library;
	@OneToOne
	private OnlineReader onlineReader;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	private String observation;
}
