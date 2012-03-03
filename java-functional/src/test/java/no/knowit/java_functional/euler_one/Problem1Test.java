package no.knowit.java_functional.euler_one;

import static org.junit.Assert.*;

import no.knowit.java_functional.euler_one.*;
import org.junit.Test;

public class Problem1Test {

	private Problem1 plainJava = new Problem1PlainJava();
	private Problem1 lambdaJ = new Problem1LambdaJ();
	private Problem1 guava = new Problem1Guava();
	private Problem1 functionalJava = new Problem1FJ();

	@Test
	public void testPlainJava() {
		testSumMultiplesOf3and5(plainJava, 1000, 233168L);
		// testSumMultiplesOf3and5(plainJava, 100000000, 2333333316666668L);
	}

	@Test
	public void testLambdaJ() {
		testSumMultiplesOf3and5(lambdaJ, 1000, 233168L);
		// testSumMultiplesOf3and5(lambdaJ, 1000000, 233333166668L);
	}

	@Test
	public void testGuava() {
		testSumMultiplesOf3and5(guava, 1000, 233168L);
		// testSumMultiplesOf3and5(guava, 1000000, 233333166668L);
	}
	
	@Test
	public void testFunctionalJ() {
		testSumMultiplesOf3and5(functionalJava, 1000, 233168L);
		// testSumMultiplesOf3and5(functionalJava, 10000, 23331668L);
	}
	
	private void testSumMultiplesOf3and5(Problem1 classUnderTest, int limit, long expected) {
		long sum = classUnderTest.sumMultiplesOf3and5Below(10);
		assertEquals("Wrong sum", 23L, sum);

		long largeSum = classUnderTest.sumMultiplesOf3and5Below(limit);
		assertEquals("Wrong sum", expected, largeSum);
	}

}
