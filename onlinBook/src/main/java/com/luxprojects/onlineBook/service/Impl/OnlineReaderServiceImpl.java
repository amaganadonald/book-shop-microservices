package com.luxprojects.onlineBook.service.Impl;

import org.springframework.stereotype.Service;

import com.luxprojects.onlineBook.dto.OnlineReaderRequestDTO;
import com.luxprojects.onlineBook.dto.OnlineReaderResponseDTO;
import com.luxprojects.onlineBook.exception.EntityNotFoundException;
import com.luxprojects.onlineBook.exception.OnlineBookBusinessException;
import com.luxprojects.onlineBook.mapper.OnlineReaderMapper;
import com.luxprojects.onlineBook.repository.OnlineReaderRepository;
import com.luxprojects.onlineBook.service.OnlineReaderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class OnlineReaderServiceImpl implements OnlineReaderService {
	
	private OnlineReaderRepository onlineReaderRepository;

	@Override
	public Flux<OnlineReaderResponseDTO> getAllOnlineReader() {
		log.info("OnlineReaderServiceImpl::getAllOnlineReader start to retrieve Online Reader");
		return onlineReaderRepository.findAll()
				.map(OnlineReaderMapper::onlineReaderToOnlineReaderResponseDTO);
	}

	@Override
	public Mono<OnlineReaderResponseDTO> getOnlineReaderById(Long id) {
		return onlineReaderRepository.findById(id)
				.map(OnlineReaderMapper::onlineReaderToOnlineReaderResponseDTO)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Online Reader with id not found "+id)));
	}

	@Override
	public Mono<OnlineReaderResponseDTO> addOnlineReader(Mono<OnlineReaderRequestDTO> onlineReaderRequestDTO) {
		return onlineReaderRequestDTO.map(OnlineReaderMapper::onlineReaderRequestDTOToOnlineReader)
				.flatMap(onlineReaderRepository::save)
				.doOnError(e->new OnlineBookBusinessException("Exception occur when Online Reader try to save "+e.getMessage()))
				.map(OnlineReaderMapper::onlineReaderToOnlineReaderResponseDTO);
	}

	@Override
	public Mono<OnlineReaderResponseDTO> updateOnlineReader(Mono<OnlineReaderRequestDTO> onlineReaderRequestDTO,
			Long id) {
		return getOnlineReaderById(id)
				.flatMap(e->onlineReaderRequestDTO.map(OnlineReaderMapper::onlineReaderRequestDTOToOnlineReader))
				                              .doOnNext(ol->ol.setId(id))
				 .flatMap(onlineReaderRepository::save)
				 .doOnError(e->new OnlineBookBusinessException("Exception occur when Online Reader try to save "+e.getMessage()))
				 .map(OnlineReaderMapper::onlineReaderToOnlineReaderResponseDTO);
	}

	@Override
	public Mono<Void> deleteOnlineReader(Long id) {
		return onlineReaderRepository.deleteById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Error occur when deleted onlineReader with id "+id)));
	}

}
