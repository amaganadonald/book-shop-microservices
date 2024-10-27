package com.luxprojects.onlineBook.ServiceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.luxprojects.onlineBook.entity.Author;

public class AuthorServiceTest {
	
	List<Author> authors = new ArrayList<>();
	
	@BeforeEach
	public void setUp() {
		authors = Stream.of(
		  new Author(1L,"amago", "evrard", LocalDate.of(1988, 9, 14), "scan.png",
				  LocalDateTime.now(), "eti", "smt", LocalDateTime.now()),
		  new Author(2L,"boyeba", "donald", LocalDate.of(1788, 5, 03), "pan.png",
				  LocalDateTime.now(), "byb", "gos", LocalDateTime.now()),
		  new Author(3L,"evann", "noe", LocalDate.of(2000, 4, 4), "an.png",
				  LocalDateTime.now(), "jus", "hj", LocalDateTime.now()),
		  new Author(4L,"abo", "laurce", LocalDate.of(2021, 2, 8), "sai.png",
				  LocalDateTime.now(), "belo", "tax", LocalDateTime.now()),
		  new Author(5L,"chloe", "yaelle", LocalDate.of(2012, 5, 2), "dora.png",
				  LocalDateTime.now(), "amag", "pre", LocalDateTime.now()),
		  new Author(6L,"ines", "mbango", LocalDate.of(2005, 10, 4), "dk.png",
				  LocalDateTime.now(), "ev", "noe", LocalDateTime.now())
		).toList();
	}
	
	@Test
	public void filter_list_by_name() {
		List<String> author = authors.stream()
				              .filter(auth -> auth.getBornDate().isAfter(LocalDate.of(2000, 1, 1)))
				              .sorted(Comparator.comparing(Author::getFirstName))
				              .map(Author::getFirstName)
				              .distinct()
				              .toList();
		assertEquals(4, author.size());
		assertEquals("abo", author.get(0));
	}

	@Test
	public void test_revere_string() {
	    String txt = "welcome";
	    StringBuffer txtReverse = new StringBuffer(txt);
	    assertEquals("emoclew", txtReverse.reverse().toString());
	    assertFalse(txt.equals(txtReverse));
	}
	
	@Test
	public void pattern_matching_test() {
		assertEquals("welcome MONDAY", patternMatching(Days.MONDAY));
	}
	
	public String patternMatching(Days day) {
		String s= switch (day) {
		case MONDAY -> "welcome " + day;
		case TUESDAY -> "welcome "  + day;
		case WEDNESDAY -> "welcome " + day;
		case THURSDAY -> "welcome "  + day;
		case FRIDAY -> "welcome " + day;
		case SATURDAY -> "welcome "  + day;
		case SUNDAY -> "welcome " + day;
		default->throw new IllegalArgumentException("Unexpected value: " + day);
		};
		return s;
	}
	public enum Days {
		MONDAY,
		TUESDAY,
		WEDNESDAY,
		THURSDAY,
		FRIDAY,
		SATURDAY,
		SUNDAY,
	}
}


