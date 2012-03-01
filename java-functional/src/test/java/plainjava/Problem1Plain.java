package plainjava;

import static org.junit.Assert.*;

import org.junit.Test;

public class Problem1Plain {
	
	@Test
	public void find_sum_of_all_multiples_of_3_and_5_below_10() {
		int sum = 0;
		for (int i = 0; i < 10; i++) {
			if (isMultipleOf3or5(i)) {
				sum += i;
			}
		}
		assertEquals("Wrong sum", 23, sum);
	}

	@Test
	public void find_sum_of_all_multiples_of_3_and_5_below_100_000_000() {
		long sum = 0;
		for (int i = 0; i < 100000000; i++) {
			if (isMultipleOf3or5(i)) {
				sum += i;
			}
		}
		assertEquals("Wrong sum", 2333333316666668L, sum);
	}

	private boolean isMultipleOf3or5(int i) {
		return i % 3 == 0 || i % 5 == 0;
	}

}
