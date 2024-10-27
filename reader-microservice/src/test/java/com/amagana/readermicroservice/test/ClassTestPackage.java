package com.amagana.readermicroservice.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.amagana.readermicroservice.model.ClassTestUnit;

public class ClassTestPackage {

	ClassTestUnit classTestUnit = new ClassTestUnit();
	
	@Test
	void shouldReturnMultiplyTwoNumber() {
		assertEquals(4, classTestUnit.multiply(2, 2));
	}
}
