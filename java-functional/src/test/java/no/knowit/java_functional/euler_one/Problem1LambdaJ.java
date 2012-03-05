package no.knowit.java_functional.euler_one;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.sum;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;
import org.junit.Test;

import ch.lambdaj.function.matcher.Predicate;

public class Problem1LambdaJ implements Problem1 {

	@Test
	public void testSumMultiplesOf3and5Below() {
		long sum = sumMultiplesOf3and5Below(10);
		assertEquals("Wrong sum", 23L, sum);

		long largeSum = sumMultiplesOf3and5Below(1000);
		assertEquals("Wrong sum", 233168L, largeSum);
		
		// long reallyLargeSum = sumMultiplesOf3and5Below(1000000);
		// assertEquals("Wrong sum", 233333166668L, reallyLargeSum);
	}

	/*
	 * Find all multiples of 3 or 5 in number range between 1 and _limit_, e.g.
	 * using _filter_. Return the _sum_ of these numbers.
	 */
	@Override
	public long sumMultiplesOf3and5Below(int limit) {
		return sum(filter(isMultipleOf3or5, range(1, limit))).longValue();
	}
	
	private Matcher<Long> isMultipleOf3or5 = new Predicate<Long>() {
		@Override
		public boolean apply(Long i) {
			return i % 3 == 0 || i % 5 == 0;
		}
	};

	private Iterable<Long> range(int from, int to) {
		List<Long> numbers = new ArrayList<Long>(to - from);
		for (long i = from; i < to; i++) {
			numbers.add(i);
		}
		return numbers;
	}

}
