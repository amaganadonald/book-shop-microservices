package com.luxprojects.onlineBook.mapper;

import org.modelmapper.ModelMapper;

import com.luxprojects.onlineBook.dto.OnlineReaderRequestDTO;
import com.luxprojects.onlineBook.dto.OnlineReaderResponseDTO;
import com.luxprojects.onlineBook.entity.OnlineReader;

public class OnlineReaderMapper {

	public static OnlineReader onlineReaderRequestDTOToOnlineReader(OnlineReaderRequestDTO onlineReaderRequestDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(onlineReaderRequestDTO, OnlineReader.class);
	}
	
	public static OnlineReaderResponseDTO onlineReaderToOnlineReaderResponseDTO(OnlineReader onlineReader) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(onlineReader, OnlineReaderResponseDTO.class);
	}
}
