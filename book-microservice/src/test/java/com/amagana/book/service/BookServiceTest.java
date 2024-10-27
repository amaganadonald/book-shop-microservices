package com.amagana.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.amagana.book.enums.Type;
import com.amagana.book.models.Dish;

public class BookServiceTest {

	 List<Dish> menu;
	@BeforeEach
	public void setUp() {
		     menu = Arrays.asList(
				 new Dish("pork", false, 800, Type.MEAT),
				 new Dish("beef", false, 700, Type.MEAT),
				 new Dish("chicken", false, 400, Type.MEAT),
				 new Dish("french fries", true, 530, Type.OTHE),
						 new Dish("rice", true, 350, Type.OTHER),
						 new Dish("season fruit", true, 120, Type.OTHE),
						 new Dish("pizza", true, 550, Type.OTHER),
						 new Dish("prawns", false, 300, Type.FISH),
						 new Dish("salmon", false, 450, Type.FISH) 
					);
	}
	@Test
	void  testWithLambda() {
		List<String> extr = menu.stream()
				.filter(men-> men.getPrice() > 500)
				.map(men->men.getName())
				.limit(3)
				.toList();
		assertEquals(List.of("pork", "beef", "french fries"), extr);
	}
	@Test
	void  testWithLambdaLength() {
		List<Integer> extr = menu.stream()
				.filter(men-> men.getPrice() > 500)
				.map(men->men.getName())
				.map(String::length)
				.limit(3)
				.toList();
		assertEquals(List.of(4, 4, 12), extr);
	}
	@Test
	void  testWithLambdaduplicate() {
		List<String> extr = menu.stream()
				.map(men->men.getName())
				.map(s->s.split(""))
				.flatMap(Arrays::stream)
				.distinct()
				.toList();
		System.out.println(extr);
		assertTrue(true);
	}
	@Test
	void  testWithSquaez() {
		List<Integer> ets = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> extr = ets.stream()
				.map(e->e*e)
				.toList();
		assertEquals(List.of(1, 4, 9, 16, 25), extr);
	}
	@Test
	void  testWithJoinTab() {
		List<Integer> tab = Arrays.asList(1, 2, 3, 4, 5);
		List<Integer> tab2 = Arrays.asList(2, 3);
		List<int[]> extr = tab.stream()
				.flatMap(tb->tab2.stream()
						.map(tb2->new int[] {tb, tb2}))
				.toList();
		System.out.println(extr);
		assertTrue(true);
	}
}
