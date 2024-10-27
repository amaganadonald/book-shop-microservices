package com.luxprojects.onlineBook.mapper;

import org.modelmapper.ModelMapper;

import com.luxprojects.onlineBook.dto.CategoryResponseDTO;
import com.luxprojects.onlineBook.dto.CategoryResquestDTO;
import com.luxprojects.onlineBook.entity.Category;

public class CategoryMapper {

	public static Category categoryRequestDTOToCategory(CategoryResquestDTO categoryRequestDTO) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(categoryRequestDTO, Category.class);
	}
	
	public static CategoryResponseDTO categoryToCategoryResponseDTO(Category category) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(category, CategoryResponseDTO.class);
	}
}
