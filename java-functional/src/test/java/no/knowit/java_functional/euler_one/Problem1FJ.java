package no.knowit.java_functional.euler_one;

import org.junit.Test;

import fj.F;
import static fj.data.List.range;
import static fj.function.Integers.sum;
import static org.junit.Assert.assertEquals;

public class Problem1FJ implements Problem1 {

	@Test
	public void testSumMultiplesOf3and5Below() {
		long sum = sumMultiplesOf3and5Below(10);
		assertEquals("Wrong sum", 23L, sum);

		long largeSum = sumMultiplesOf3and5Below(1000);
		assertEquals("Wrong sum", 233168L, largeSum);
		
		// long reallyLargeSum = sumMultiplesOf3and5Below(10000);
		// assertEquals("Wrong sum", 23331668L, reallyLargeSum);
	}

	/*
	 * Find all multiples of 3 or 5 in number range between 1 and _limit_, e.g.
	 * using _filter_. Return the _sum_ of these numbers.
	 */
	@Override
	public long sumMultiplesOf3and5Below(int limit) {
		return sum(range(1, limit).filter(isMultipleOf3or5));
	}

	F<Integer, Boolean> isMultipleOf3or5 = new F<Integer, Boolean>() {
		@Override
		public Boolean f(Integer i) {
			return i % 3 == 0 || i % 5 == 0;
		}
		
	};

}
