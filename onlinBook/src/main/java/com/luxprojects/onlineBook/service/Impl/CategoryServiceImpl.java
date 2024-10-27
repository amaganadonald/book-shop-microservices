package com.luxprojects.onlineBook.service.Impl;

import org.springframework.stereotype.Service;

import com.luxprojects.onlineBook.dto.CategoryResponseDTO;
import com.luxprojects.onlineBook.dto.CategoryResquestDTO;
import com.luxprojects.onlineBook.entity.Category;
import com.luxprojects.onlineBook.exception.EntityNotFoundException;
import com.luxprojects.onlineBook.mapper.CategoryMapper;
import com.luxprojects.onlineBook.repository.CategoryRepository;
import com.luxprojects.onlineBook.service.CategoryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Slf4j
@Service
public class CategoryServiceImpl implements CategoryService {
	
	private CategoryRepository categoryRepository;

	@Override
	public Flux<CategoryResponseDTO> getAllCategory() {
		log.info("CategoryServiceImpl::getAllCategory start retrieve all Category in the database");
		return categoryRepository.findAll()
				   .map(CategoryMapper::categoryToCategoryResponseDTO);
	}
	
	@Override
	public Mono<Category> findCategoryById(Long id) {
		return categoryRepository.findById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Category not found with id"+id)));
	}

	@Override
	public Mono<CategoryResponseDTO> getCategoryById(Long id) {
		log.info("CategoryServiceImpl::getCategoryById start retrieve Category with id {}", id);
		return findCategoryById(id)
				.map(CategoryMapper::categoryToCategoryResponseDTO);
	}

	@Override
	public Mono<CategoryResponseDTO> addCategory(Mono<CategoryResquestDTO> categoryResquestDTO) {
		log.info("CategoryServiceImpl::addCategory start to add Category {}", categoryResquestDTO.toString());
		return categoryResquestDTO.map(CategoryMapper::categoryRequestDTOToCategory)
				.flatMap(categoryRepository::save)
				.map(CategoryMapper::categoryToCategoryResponseDTO);
	}

	@Override
	public Mono<CategoryResponseDTO> updateCategory(Mono<CategoryResquestDTO> categoryResquestDTO, Long id) {
		log.info("CategoryServiceImpl::updateCategory start to update Category with id {}", id);
		return findCategoryById(id)
				   .flatMap(c-> categoryResquestDTO.map(CategoryMapper::categoryRequestDTOToCategory)
						   .doOnNext(ca->ca.setId(id)))
				   .flatMap(categoryRepository::save)
				   .map(CategoryMapper::categoryToCategoryResponseDTO);
	}

	@Override
	public Mono<Void> deleteCategory(Long id) {
		log.info("CategoryServiceImpl::deleteCategory start to delete Category with id {}", id);
		return categoryRepository.deleteById(id)
				.then(Mono.error(new EntityNotFoundException("Category id not found"+id)));
	}

}
