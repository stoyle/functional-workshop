package functionaljava;

import static org.junit.Assert.*;

import org.junit.Test;

import fj.F;
import fj.Monoid;
import fj.data.Array;

public class Problem1FJ {

	F<Integer, Boolean> isMultipleOf3or5 = new F<Integer, Boolean>() {

		@Override
		public Boolean f(Integer i) {
			return i % 3 == 0 || i % 5 == 0;
		}
		
	};

	F<Integer, F<Integer, Integer>> sum = Monoid.intAdditionMonoid.sum();

	@Test
	public void find_sum_of_all_multiples_of_3_and_5_below_10() {
		int res = Array.range(1, 10).filter(isMultipleOf3or5).foldLeft(sum , 0);
		assertEquals("Wrong sum", 23, res);
	}

	@Test
	public void find_sum_of_all_multiples_of_3_and_5_below_10_000() {
		int res = Array.range(1, 10000).filter(isMultipleOf3or5).foldLeft(sum, 0);
		assertEquals("Wrong sum", 23331668, res);
	}

}
