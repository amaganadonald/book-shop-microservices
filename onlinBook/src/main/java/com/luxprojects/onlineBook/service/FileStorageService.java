package com.luxprojects.onlineBook.service;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface FileStorageService {
	
	void init();
	Mono<String> save(Mono<FilePart> filePart);
	Flux<DataBuffer> load(String filename);
	Stream<Path> loadAll();

}
