package no.knowit.java_functional.product;

import static ch.lambdaj.Lambda.*;
import static fj.data.List.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.*;

import org.joda.time.LocalDate;
import org.junit.Test;

import fj.F;
import fj.F2;

public class ProductRepositoryFunctionalJavaTest {
	
	private static class ProductRepositoryImpl implements ProductRepository {

		private final List<Product> products;

		public ProductRepositoryImpl(Product... products) {
			this.products = Collections.unmodifiableList(Arrays.asList(products));
		}

		/**
		 * Exercise 2: Return the list of products that are available on the
		 * given date. Implement this method using FunctionalJava. You may
		 * create helper functions and classes.
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

		/**
		 * LambdaJ solution, for reference.
		 * 
		 * @param date
		 * @return
		 */
		private Collection<Product> getAvailableProductsLambaJ(LocalDate date) {
			return filter(having(on(Product.class).isAvailable(date)), products);
		}

	}

	/*
	 * Test code below here
	 */

	@Test()
	public void empty_list_when_no_products() {
		ProductRepository productRepo = new ProductRepositoryImpl();
		Collection<Product> available = productRepo.getAvailableProducts(new LocalDate());
		assertThat(available.isEmpty(), is(equalTo(true)));
	}

	@Test
	public void product_available_when_matching_date() {
		LocalDate today = new LocalDate();
		Product coolProduct = new Product("Cool product", today, today);
		ProductRepository productRepo = new ProductRepositoryImpl(coolProduct);

		Collection<Product> available = productRepo.getAvailableProducts(today);
		assertThat(available.size(), is(equalTo(1)));
		assertThat(available, hasItems(coolProduct));
	}

	@Test
	public void empty_list_when_no_products_matching_date() {
		LocalDate today = new LocalDate();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product", yesterday, yesterday);

		ProductRepository productRepo = new ProductRepositoryImpl(discontinuedProduct);

		Collection<Product> available = productRepo.getAvailableProducts(today);
		assertThat(available.isEmpty(), is(equalTo(true)));
	}

	@Test
	public void find_discontinued_products() {
		LocalDate today = new LocalDate();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product", yesterday, yesterday);

		ProductRepository productRepo = new ProductRepositoryImpl(discontinuedProduct);

		Collection<Product> available = productRepo.getAvailableProducts(yesterday);
		assertThat(available, hasItems(discontinuedProduct));
	}

	@Test
	public void find_discontinued_then_new_products() {
		LocalDate today = new LocalDate();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product", yesterday, yesterday);
		Product newProduct = new Product("New product", today, null);

		ProductRepository productRepo = new ProductRepositoryImpl(discontinuedProduct, newProduct);

		Collection<Product> availableYesterday = productRepo.getAvailableProducts(yesterday);
		assertThat(availableYesterday, hasItems(discontinuedProduct));

		Collection<Product> availableToday = productRepo.getAvailableProducts(today);
		assertThat(availableToday, hasItems(newProduct));
	}
}
