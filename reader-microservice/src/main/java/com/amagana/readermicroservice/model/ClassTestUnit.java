package com.amagana.readermicroservice.model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ClassTestUnit implements Runnable {

	public int multiply(int a, int b) {
		return a * b;
	}
	
	 
	
	@Scheduled(fixedDelay = 10000, cron = "30 06 1 * MON-SAT")
	public void myTask() {
		System.out.println("task id # "+ LocalDate.now());
	}
	
	public static void main(String[] args) {
		ClassTestUnit cl = new ClassTestUnit();
		cl.myTask();
		cl.run();
		BiFunction<Double, Integer, Double> f = (x,y) -> x*y;
		System.out.println(f.apply(4.4, 5));
		Consumer<String> cs = (a) -> System.out.println(a);
		cs.accept("hello world");
		Predicate<Integer> p = (pval) -> pval > 10;
		System.out.println(p.test(20));
		Supplier<String> sp = () ->  "Hello God please I need help";
		System.out.println(sp.get());
		Function<Integer, String> fp = pf -> "Hello mes gens" + pf;
		System.out.println(fp.apply(4));
		String[] str = {"dnnald", "laurice", "evann", "Aouadezpa", "riz", "sysli"};
		Arrays.stream(str)
		    .dropWhile(e->e.charAt(1)=='o')
		    .sorted(Comparator.comparing(String::length).reversed())
		    .forEach(System.out::println);
	}



	@Override
	public void run() {
		try {
			Thread.sleep(5000);
			System.out.println("Thread exceuted");
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
			System.out.println("task interrupted");
		}
		
	}
}
