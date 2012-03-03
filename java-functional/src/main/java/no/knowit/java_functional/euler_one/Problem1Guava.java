package no.knowit.java_functional.euler_one;

import static com.google.common.collect.Iterables.filter;

import com.google.common.base.Predicate;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;

public class Problem1Guava implements Problem1 {

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
