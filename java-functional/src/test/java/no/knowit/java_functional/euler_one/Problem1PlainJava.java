package no.knowit.java_functional.euler_one;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class Problem1PlainJava implements Problem1 {

	@Test
	public void testSumMultiplesOf3and5Below() {
		long sum = sumMultiplesOf3and5Below(10);
		assertEquals("Wrong sum", 23L, sum);

		long largeSum = sumMultiplesOf3and5Below(1000);
		assertEquals("Wrong sum", 233168L, largeSum);
		
		// long reallyLargeSum = sumMultiplesOf3and5Below(100000000);
		// assertEquals("Wrong sum", 2333333316666668L, reallyLargeSum);
	}

	@Override
	public long sumMultiplesOf3and5Below(int limit) {
		long sum = 0;
		for (int i = 0; i < limit; i++) {
			if (isMultipleOf3or5(i)) {
				sum += i;
			}
		}
		return sum;
	}

	private boolean isMultipleOf3or5(int i) {
		return i % 3 == 0 || i % 5 == 0;
	}

}
