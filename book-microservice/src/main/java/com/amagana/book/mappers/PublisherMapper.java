package com.amagana.book.mappers;

import org.modelmapper.ModelMapper;

import com.amagana.book.dto.PublisherRequestDTO;
import com.amagana.book.dto.PublisherResponseDTO;
import com.amagana.book.entity.Publisher;

public class PublisherMapper {

	public static Publisher publisherRequestDTOToBook(PublisherRequestDTO publisherRequestDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(publisherRequestDTO, Publisher.class);
	}
	
	public static PublisherResponseDTO publisherToPublisherResponseDTO(Publisher publisher) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(publisher, PublisherResponseDTO.class);
	}
}
