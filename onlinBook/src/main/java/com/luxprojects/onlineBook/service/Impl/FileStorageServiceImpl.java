package com.luxprojects.onlineBook.service.Impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;

import com.luxprojects.onlineBook.entity.FileStorage;
import com.luxprojects.onlineBook.repository.FileStorageRepository;
import com.luxprojects.onlineBook.service.FileStorageService;

import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class FileStorageServiceImpl implements FileStorageService{

	private final Path root = Paths.get("uplads");
	private FileStorageRepository fileStorageRepository;
	@Override
	public void init() {
       try {
		  if(!(Files.exists(root) && Files.isDirectory(root)))
				  Files.createDirectories(root);
		} catch (IOException e) {
			throw new RuntimeException("Error open folder");
		}
		
	}

	@Override
	public Mono<String> save(Mono<FilePart> filePart) {
		/*return filePart.doOnNext(fp->System.out.println("Receiving File:"+fp.filename()))
				.flatMap(filePart -> {
					return filePart.transferTo(root.resolve(filePart.filename()))
							.then(Mono.just(filePart.filename()));
				});*/
		return Mono.just("File");
	}

	@Override
	public Flux<DataBuffer> load(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Stream<Path> loadAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
