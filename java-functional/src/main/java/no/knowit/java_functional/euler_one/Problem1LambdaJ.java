package no.knowit.java_functional.euler_one;

import static ch.lambdaj.Lambda.filter;
import static ch.lambdaj.Lambda.sum;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matcher;

import ch.lambdaj.function.matcher.Predicate;

public class Problem1LambdaJ implements Problem1 {

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
