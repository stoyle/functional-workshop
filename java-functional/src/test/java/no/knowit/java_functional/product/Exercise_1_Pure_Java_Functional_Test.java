package no.knowit.java_functional.product;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.*;
import org.apache.commons.collections.ListUtils;
import org.joda.time.LocalDate;
import org.junit.Test;

public class Exercise_1_Pure_Java_Functional_Test {

	private static class ProductRepository {

		private final List<Product> products;

		public ProductRepository(Product... products) {
			this.products = Collections.unmodifiableList(Arrays.asList(products));
		}

		/**
		 * Return the products that are available on the given date.
		 * 
		 * Exercise 1: Implement this method using only immutable data
		 * structures (unmodifiable collections, final variables/fields) so that
		 * all tests pass. You are allowed to use the _cons_ method below to add
		 * an element to a list, creating a new unmodifiable list.
		 * 
		 * The other two _getAvailableProducts..._ methods are provided for
		 * illustration of common Java solutions.
		 */
		public Collection<Product> getAvailableProducts(LocalDate date) {
			List<Product> emptyList = Collections.emptyList();
			return getAvailableProductsRecursion(products, date, emptyList);
		}

		private Collection<Product> getAvailableProductsRecursion(final List<Product> products, final LocalDate date,
				final List<Product> result) {
			if (products.isEmpty()) {
				return result;
			}
			Product first = products.get(0);
			List<Product> rest = products.subList(1, products.size());
			if (first.isAvailable(date)) {
				return getAvailableProductsRecursion(rest, date, cons(first, result));
			} else {
				return getAvailableProductsRecursion(rest, date, result);
			}
		}

		@SuppressWarnings("unchecked")
		private List<Product> cons(Product head, List<Product> tail) {
			return Collections.unmodifiableList(ListUtils.union(Arrays.asList(head), tail));
		}

		/**
		 * Common Java pattern using Iterator to remove unwanted products before
		 * returning the resulting collection.
		 * 
		 * This will fail because we are trying to remove elements from an
		 * immutable collection.
		 * 
		 * Modifying collections should be avoided, as they may also be
		 * referenced by others and we may this destroy the basis for other
		 * computations.
		 */
		public Collection<Product> getAvailableProductsIterator(LocalDate date) {
			for (Iterator<Product> i = products.iterator(); i.hasNext();) {
				Product p = (Product) i.next();
				if (!p.isAvailable(date)) {
					i.remove();
				}
			}
			return products;
		}

		/**
		 * Common Java pattern creating a new collection to hold the available
		 * products.
		 * 
		 * Improvement on the above, as this does not change the original
		 * collection but instead creates a new. However it still creates and
		 * works on a mutable list, even if only internally in the function.
		 */
		public Collection<Product> getAvailableProductsForLoop(LocalDate date) {
			List<Product> result = new LinkedList<Product>();
			for (Product product : products) {
				if (product.isAvailable(date)) {
					result.add(product);
				}
			}
			return result;
		}

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

	/*
	 * This is expected to fail since it tries to remove an element from an
	 * immutable list
	 */
	@Test(expected = java.lang.UnsupportedOperationException.class)
	public void empty_list_when_no_products_matching_date_iterator() {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product", yesterday, yesterday);

		ProductRepository productRepo = new ProductRepository(discontinuedProduct);

		Collection<Product> available = productRepo.getAvailableProductsIterator(today);
		fail("Should not be allowed to remove elements from an immutable list");
	}

	@Test()
	public void find_discontinued_then_new_products_for_loop() {
		LocalDate today = LocalDate.now();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product", yesterday, yesterday);
		Product newProduct = new Product("New product", today, null);

		ProductRepository productRepo = new ProductRepository(discontinuedProduct, newProduct);

		Collection<Product> availableYesterday = productRepo.getAvailableProductsForLoop(yesterday);
		assertThat(availableYesterday, hasItems(discontinuedProduct));

		Collection<Product> availableToday = productRepo.getAvailableProducts(today);
		assertThat(availableToday, hasItems(newProduct));
	}

}
