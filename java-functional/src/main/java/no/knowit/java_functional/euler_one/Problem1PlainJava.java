package no.knowit.java_functional.euler_one;

public class Problem1PlainJava implements Problem1 {

	@Override
	public long sumMultiplesOf3and5Below(int limit) {
		long sum = 0;
		for (int i = 0; i < limit; i++) {
			if (isMultipleOf3or5(i)) {
				sum += i;
			}
		}
		return sum;
	}

	private boolean isMultipleOf3or5(int i) {
		return i % 3 == 0 || i % 5 == 0;
	}

}
