package euler.problem1;

import static org.junit.Assert.*;

import org.junit.Test;

public class Problem1Test {

	private Problem1 plainJava = new Problem1PlainJava();
	private Problem1 lambdaJ = new Problem1LambdaJ();
	private Problem1 guava = new Problem1Guava();
	private Problem1 functionalJava = new Problem1FJ();

	@Test
	public void testPlainJava() {
		testSumMultiplesOf3and5Below10(plainJava);

		long largeSum = plainJava.sumMultiplesOf3and5Below(100000000);
		assertEquals("Wrong sum", 2333333316666668L, largeSum);
	}

	@Test
	public void testLambdaJ() {
		testSumMultiplesOf3and5Below10(lambdaJ);

		long largeSum = lambdaJ.sumMultiplesOf3and5Below(1000000);
		assertEquals("Wrong sum", 233333166668L, largeSum);
	}

	@Test
	public void testGuava() {
		testSumMultiplesOf3and5Below10(guava);

		long largeSum = guava.sumMultiplesOf3and5Below(1000000);
		assertEquals("Wrong sum", 233333166668L, largeSum);
	}
	
	@Test
	public void testFunctionalJ() {
		testSumMultiplesOf3and5Below10(functionalJava);

		long largeSum = functionalJava.sumMultiplesOf3and5Below(10000);
		assertEquals("Wrong sum", 23331668L, largeSum);
	}
	
	private void testSumMultiplesOf3and5Below10(Problem1 classUnderTest) {
		long sum = classUnderTest.sumMultiplesOf3and5Below(10);
		assertEquals("Wrong sum", 23L, sum);
	}

}
