package no.knowit.java_functional;

import static fj.data.List.iterableList;
import fj.F;
import fj.F2;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.*;

import no.knowit.java_functional.product.Product;
import org.joda.time.LocalDate;
import org.junit.Test;

public class Exercise_2_Filter_Test {
	
	private static class ProductRepository {

		private final List<Product> products;

		public ProductRepository(Product... products) {
			this.products = Collections.unmodifiableList(Arrays.asList(products));
		}

		/**
		 * Exercise 2: Return the list of products that are available on the
		 * given date. Implement this method using FunctionalJava. You may
		 * create helper functions and classes.
		 * 
		 * Hint: Check Functional Java imports at the top of the source file.
		 */
		public Collection<Product> getAvailableProducts(LocalDate date) {
			F<Product, Boolean> f = isAvailableOnDate.flip().f(date);
			return iterableList(products).filter(f).toCollection();
		}

		/**
		 * Implement a function which takes a product and date as input and
		 * returns a boolean indicating whether or not the product is available
		 * on the given date.
		 */
		private F2<Product, LocalDate, Boolean> isAvailableOnDate = new F2<Product, LocalDate, Boolean>() {
			@Override
			public Boolean f(Product p, LocalDate date) {
				return p.isAvailable(date);
			}
		};

	}

	/*
	 * Test code below here
	 */

	@Test()
	public void empty_list_when_no_products() {
		ProductRepository productRepo = new ProductRepository();
		Collection<Product> available = productRepo.getAvailableProducts(LocalDate.now());
		assertThat(available.isEmpty(), is(equalTo(true)));
	}

	@Test
	public void product_available_when_matching_date() {
		LocalDate today = LocalDate.now();
		Product coolProduct = new Product("Cool product", today, today);
		ProductRepository productRepo = new ProductRepository(coolProduct);

		Collection<Product> available = productRepo.getAvailableProducts(today);
		assertThat(available.size(), is(equalTo(1)));
		assertThat(available, hasItems(coolProduct));
	}

	/*
	 * This is expected to fail since it tries to remove an element from an
	 * immutable list
	 */
	@Test(expected = java.lang.UnsupportedOperationException.class)
	public void cannot_remove_elements_from_available_products() {
		LocalDate today = LocalDate.now();
		Product coolProduct = new Product("Cool product", today, today);
		ProductRepository productRepo = new ProductRepository(coolProduct);

		Collection<Product> available = productRepo.getAvailableProducts(today);
		Iterator<Product> i = available.iterator();
		i.next();
		i.remove();
		fail("Should not be allowed to remove elements from an immutable list");
	}

	@Test
	public void empty_list_when_no_products_matching_date() {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product", yesterday, yesterday);

		ProductRepository productRepo = new ProductRepository(discontinuedProduct);

		Collection<Product> available = productRepo.getAvailableProducts(today);
		assertThat(available.isEmpty(), is(equalTo(true)));
	}

	@Test
	public void find_discontinued_products() {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product", yesterday, yesterday);

		ProductRepository productRepo = new ProductRepository(discontinuedProduct);

		Collection<Product> available = productRepo.getAvailableProducts(yesterday);
		assertThat(available, hasItems(discontinuedProduct));
	}

	@Test
	public void find_discontinued_then_new_products() {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product", yesterday, yesterday);
		Product newProduct = new Product("New product", today, null);

		ProductRepository productRepo = new ProductRepository(discontinuedProduct, newProduct);

		Collection<Product> availableYesterday = productRepo.getAvailableProducts(yesterday);
		assertThat(availableYesterday, hasItems(discontinuedProduct));

		Collection<Product> availableToday = productRepo.getAvailableProducts(today);
		assertThat(availableToday, hasItems(newProduct));
	}

}