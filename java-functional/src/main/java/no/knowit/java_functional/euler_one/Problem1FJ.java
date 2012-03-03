package no.knowit.java_functional.euler_one;

import fj.F;
import static fj.data.List.range;
import static fj.function.Integers.sum;

public class Problem1FJ implements Problem1 {

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
