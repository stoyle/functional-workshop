package no.knowit.java_functional.product;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Test;

public class ProductTest {

	@Test
	public void isAvailableToday() {
		LocalDate today = new LocalDate();
		Product p = new Product("", today, today);
		assertThat(p.isAvailable(today), is(equalTo(true)));
	}

	@Test
	public void isNotAvailableBeforeFromDate() {
		LocalDate today = new LocalDate();
		Product p = new Product("", today, today);
		assertThat(p.isAvailable(today.minusDays(1)), is(equalTo(false)));
	}

	@Test
	public void isNotAvailableAfterToDate() {
		LocalDate today = new LocalDate();
		Product p = new Product("", today, today);
		assertThat(p.isAvailable(today.plusDays(1)), is(equalTo(false)));
	}

	@Test
	public void isAvailableAfterFromDateOpenToDate() {
		LocalDate today = new LocalDate();
		Product p = new Product("", today, null);
		assertThat(p.isAvailable(today.plusDays(1)), is(equalTo(true)));
	}

}
