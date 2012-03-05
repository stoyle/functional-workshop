package no.knowit.java_functional.product;

import static ch.lambdaj.Lambda.*;
import static fj.data.List.*;
import static ch.lambdaj.collection.LambdaCollections.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import fj.F;
import fj.F2;

public class ProductRepositoryTest implements ProductRepository {

	private List<Product> products;

	public Collection<Product> getAvailableProducts(LocalDate date) {
		// return getAvailableProductsIterator(date);
		return getAvailableProductsForLoop(date);
		// return getAvailableProductsLambaJ(date);
		// return getAvailableProductsFJ(date);
	}

	/*
	 * Plain Java
	 */

	private Collection<Product> getAvailableProductsIterator(LocalDate date) {
		for (Iterator<Product> i = products.iterator(); i.hasNext();) {
			Product p = (Product) i.next();
			if (!p.isAvailable(date)) {
				i.remove();
			}
		}
		return products;
	}

	private Collection<Product> getAvailableProductsForLoop(LocalDate date) {
		List<Product> result = new LinkedList<Product>();
		for (Product product : products) {
			if (product.isAvailable(date)) {
				result.add(product);
			}
		}
		return result;
	}

	/*
	 * LambdaJ
	 */

	private Collection<Product> getAvailableProductsLambaJ(LocalDate date) {
		return filter(having(on(Product.class).isAvailable(date)), products);
	}

	/*
	 * Functional Java
	 */

	private F2<Product, LocalDate, Boolean> isAvailableOnDate = new F2<Product, LocalDate, Boolean>() {
		@Override
		public Boolean f(Product p, LocalDate date) {
			return p.isAvailable(date);
		}
	};

	private Collection<Product> getAvailableProductsFJ(LocalDate date) {
		F<Product, Boolean> f = isAvailableOnDate.flip().f(date);
		return iterableList(products).filter(f).toCollection();
	}

	/*
	 * Test code below here
	 */

	@Before
	public void populateRepository() {
		products = new LinkedList<Product>();
	}

	@Test
	public void empty_list_when_no_products() {
		Collection<Product> available = getAvailableProducts(new LocalDate());
		assertThat(available.isEmpty(), is(equalTo(true)));
	}

	@Test
	public void product_available_when_matching_date() {
		LocalDate today = new LocalDate();
		Product coolProduct = new Product("Cool product", today, today);
		products.add(coolProduct);

		Collection<Product> available = getAvailableProducts(today);
		assertThat(available.size(), is(equalTo(1)));
		assertThat(available, hasItems(coolProduct));
	}

	@Test
	public void empty_list_when_no_products_matching_date() {
		LocalDate today = new LocalDate();
		LocalDate yesterday = today.minusDays(1);
		products.add(new Product("Discontinued product", yesterday, yesterday));

		Collection<Product> available = getAvailableProducts(today);
		assertThat(available.isEmpty(), is(equalTo(true)));
	}

	@Test
	public void find_discontinued_products() {
		LocalDate today = new LocalDate();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product",
				yesterday, yesterday);
		products.add(discontinuedProduct);

		Collection<Product> available = getAvailableProducts(yesterday);
		assertThat(available, hasItems(discontinuedProduct));
	}

	@Test
	public void find_discontinued_then_new_products() {
		LocalDate today = new LocalDate();
		LocalDate yesterday = today.minusDays(1);
		Product discontinuedProduct = new Product("Discontinued product",
				yesterday, yesterday);
		Product newProduct = new Product("New product", today, null);
		products.add(discontinuedProduct);
		products.add(newProduct);

		Collection<Product> availableYesterday = getAvailableProducts(yesterday);
		assertThat(availableYesterday, hasItems(discontinuedProduct));

		Collection<Product> availableToday = getAvailableProducts(today);
		assertThat(availableToday, hasItems(newProduct));
	}
}
