package guava;

import static org.junit.Assert.*;
import static com.google.common.collect.Iterables.*;

import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.ContiguousSet;
import com.google.common.collect.DiscreteDomains;
import com.google.common.collect.Ranges;

public class Problem1Guava {

	private Predicate<Integer> isMultipleOf3or5 = new Predicate<Integer>() {
		
		@Override
		public boolean apply(Integer i) {
			return i % 3 == 0 || i % 5 == 0;
		}
	};

	@Test
	public void find_sum_of_all_multiples_of_3_and_5_below_10() {
		ContiguousSet<Integer> numbers = Ranges.open(0, 10).asSet(DiscreteDomains.integers());
		int res = 0;
		for (Integer i : filter(numbers, isMultipleOf3or5)) {
			res += i;
		}
		assertEquals("Wrong sum", 23, res);
	}

	@Test
	public void find_sum_of_all_multiples_of_3_and_5_below_1_000_000() {
		ContiguousSet<Integer> numbers = Ranges.open(0, 1000000).asSet(DiscreteDomains.integers());
		long res = 0;
		for (Integer i : filter(numbers, isMultipleOf3or5)) {
			res += i;
		}
		assertEquals("Wrong sum", 233333166668L, res);
	}

}
