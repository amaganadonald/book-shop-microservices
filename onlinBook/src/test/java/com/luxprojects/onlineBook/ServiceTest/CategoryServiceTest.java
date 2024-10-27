package com.luxprojects.onlineBook.ServiceTest;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

import com.luxprojects.onlineBook.entity.Category;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

class CategoryServiceTest {
	
	
	@Test
	void givenMono_test() {
		Mono<?> myString = Mono.just("Welcome World")
				.then(Mono.error(new RuntimeException("Exception occured")))
				.log();
		myString.subscribe(System.out::println);
	}

	@Test
	void givenFlux_test() {
		Flux<String> allString = Flux.just("Donald", "Laurice", "Evann").log();
		allString.subscribe(System.out::println);
	}
	
	/*@Test
	void givenCat() {
	 List<Category> categories= IntStream.range(1, 1000)
		.mapToObj(i->new Category((long) i, "Category#"+i, "desc#"))
		.collect(Collectors.toList());
	 Flux<Category> cat = Flux.range(1, 1000)
			 .delayElements(Duration.ofSeconds(1))
			 .map(i->new Category((long) i, "Category#"+i, "desc#"));
	   System.out.println(categories.toString());
	   System.out.println(cat);
	}*/
}
