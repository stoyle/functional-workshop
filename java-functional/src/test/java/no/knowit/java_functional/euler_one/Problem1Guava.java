package no.knowit.java_functional.euler_one;

import static com.google.common.collect.Iterables.filter;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;

public class Problem1Guava implements Problem1 {

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
		ContiguousSet<Integer> numbers = Ranges.open(0, limit).asSet(
				DiscreteDomains.integers());
		long res = 0;
		for (Integer i : filter(numbers, isMultipleOf3or5)) {
			res += i;
		}
		return res;
	}

	private Predicate<Integer> isMultipleOf3or5 = new Predicate<Integer>() {
		@Override
		public boolean apply(Integer i) {
			return i % 3 == 0 || i % 5 == 0;
		}
	};

}
