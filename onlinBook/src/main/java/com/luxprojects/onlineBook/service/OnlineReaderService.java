package com.luxprojects.onlineBook.service;


import com.luxprojects.onlineBook.dto.OnlineReaderRequestDTO;
import com.luxprojects.onlineBook.dto.OnlineReaderResponseDTO;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OnlineReaderService {

	public Flux<OnlineReaderResponseDTO> getAllOnlineReader();
	public Mono<OnlineReaderResponseDTO> getOnlineReaderById(Long id);
	public Mono<OnlineReaderResponseDTO> addOnlineReader(Mono<OnlineReaderRequestDTO> onlineReaderRequestDTO);
	public Mono<OnlineReaderResponseDTO> updateOnlineReader(Mono<OnlineReaderRequestDTO> onlineReaderRequestDTO, Long id);
	public Mono<Void> deleteOnlineReader(Long id);
}
