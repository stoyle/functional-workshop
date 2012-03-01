package lambdaj;

import static ch.lambdaj.Lambda.*;
import static org.junit.Assert.*;

import java.util.Iterator;

import org.hamcrest.Matcher;
import org.junit.BeforeClass;
import org.junit.Test;

import ch.lambdaj.Lambda;
import ch.lambdaj.function.matcher.Predicate;

public class Problem1LambdaJ {

	private Matcher<Long> isMultipleOf3or5 = new Predicate<Long>() {

		@Override
		public boolean apply(Long i) {
			return i % 3 == 0 || i % 5 == 0;
		}
		
	};

	@BeforeClass
	public static void beforeClass() {
		Lambda.enableJitting(true);
	}
	
	@Test
	public void find_sum_of_all_multiples_of_3_and_5_below_10() {
		Iterable<Long> numbers = range(1, 10);
		Number res = sum(filter(isMultipleOf3or5, numbers));
		assertEquals("Wrong sum", 23L, res);
	}

	@Test
	public void find_sum_of_all_multiples_of_3_and_5_below_1_000_000() {
		Iterable<Long> numbers = range(1, 1000000);
		Number res = sum(filter(isMultipleOf3or5, numbers));
		assertEquals("Wrong sum", 233333166668L, res);
	}

	private Iterable<Long> range(final int from, final int to) {
		return new Iterable<Long>() {

			@Override
			public Iterator<Long> iterator() {
				return new Iterator<Long>() {

					long i = from;
					
					@Override
					public boolean hasNext() {
						return i < to;
					}

					@Override
					public Long next() {
						return i++;
					}

					@Override
					public void remove() {
						throw new UnsupportedOperationException();
					}
					
				};
			}
			
		};
	}

}
