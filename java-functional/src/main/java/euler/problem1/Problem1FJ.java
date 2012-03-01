package euler.problem1;

import fj.F;
import fj.Monoid;
import fj.data.Array;

public class Problem1FJ implements Problem1 {

	@Override
	public long sumMultiplesOf3and5Below(int limit) {
		return Array.range(1, limit).filter(isMultipleOf3or5).foldLeft(sum, 0);
	}

	F<Integer, Boolean> isMultipleOf3or5 = new F<Integer, Boolean>() {
		@Override
		public Boolean f(Integer i) {
			return i % 3 == 0 || i % 5 == 0;
		}
		
	};

	F<Integer, F<Integer, Integer>> sum = Monoid.intAdditionMonoid.sum();

}
