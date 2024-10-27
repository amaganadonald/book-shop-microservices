package com.luxprojects.onlineBook.service.Impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.luxprojects.onlineBook.dto.BookRequestDTO;
import com.luxprojects.onlineBook.dto.BookResponseDTO;
import com.luxprojects.onlineBook.entity.Book;
import com.luxprojects.onlineBook.entity.Category;
import com.luxprojects.onlineBook.entity.Library;
import com.luxprojects.onlineBook.exception.EntityNotFoundException;
import com.luxprojects.onlineBook.mapper.BookMapper;
import com.luxprojects.onlineBook.repository.BookRepository;
import com.luxprojects.onlineBook.service.BookService;
import com.luxprojects.onlineBook.service.CategoryService;
import com.luxprojects.onlineBook.service.LibraryService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
@Slf4j
public class BookServiceImpl implements BookService{

	private BookRepository bookRepository;
	private LibraryService libraryService;
	private CategoryService categoryService;

	@Override
	public Flux<BookResponseDTO> getAllBook() {
		log.info("BookServiceImpl::getAllBook start to fetch all Books");
		return bookRepository.findAll()
				.map(BookMapper::bookToBookResponseDTO);
	}

	public Mono<Book> findBookById(Long id) {
		return bookRepository.findById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Book not found with id "+id)));
	}
	@Override
	public Mono<BookResponseDTO> getBookById(Long id) {
		return findBookById(id)
				.map(BookMapper::bookToBookResponseDTO);
	}

	@Override
	public Mono<BookResponseDTO> addBook(Mono<BookRequestDTO> bookRequestDTO) {
		return bookRequestDTO.map(BookMapper::bookRequestToBook)
				.flatMap(bookRepository::save)
				.map(BookMapper::bookToBookResponseDTO);
	}

	@Override
	public Mono<BookResponseDTO> updateBook(Mono<BookRequestDTO> bookRequestDTO, Long id) {
		return findBookById(id)
				.flatMap(b->bookRequestDTO.map(BookMapper::bookRequestToBook)
						.doOnNext(bk->bk.setId(id)))
				.flatMap(bookRepository::save)
				.map(BookMapper::bookToBookResponseDTO);
	}

	@Override
	public Mono<Void> deleteBook(Long id) {
		return bookRepository.deleteById(id)
				.switchIfEmpty(Mono.error(new EntityNotFoundException("Book with id not found "+id)));
	}
	
	@Override
	public Mono<BookResponseDTO> bookLibrary(Long bookId, Long libraryId) {
		Mono<Book> bookMono = findBookById(bookId);
		Mono<Library> libraryMono = libraryService.findBookById(libraryId);
		Library library = (Library) libraryMono.subscribe();
		Book book = (Book) bookMono.subscribe(); 
		//book.setLibraryId(library);
		return bookRepository.save(book)
				.map(BookMapper::bookToBookResponseDTO);
	}
	
	@Override
	public Mono<BookResponseDTO> bookCategory(Long bookId, Long categoryId) {
		Mono<Category> categoryMono = categoryService.findCategoryById(categoryId);
		Mono<Book> bookMono = findBookById(bookId);
		Book book = (Book) bookMono.subscribe();
		Category category = (Category) categoryMono.subscribe();
		//book.setCategoryId(category);
		return bookRepository.save(book)
				.map(BookMapper::bookToBookResponseDTO);
	}
	
	public static Boolean isPalindrome(String str) {
		String st = str.toLowerCase().replaceAll("[^a-zA-Z0-9]", "");
		return st.equals(new StringBuilder(st).reverse().toString());
	}
	
	public static String browseHashMap(HashMap<Integer, String> map)  {
		map.put(1, "donald");
		map.put(2, "evann");
		map.put(3, "lauricee");
		var str = "";
		for (Map.Entry<Integer, String> entry : map.entrySet()) {
			Integer key = entry.getKey();
			String val = entry.getValue();
			str = str + entry.getValue();
		}
		return str;
	}

}
