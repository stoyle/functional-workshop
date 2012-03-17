package no.knowit.java_functional.product;

import static ch.lambdaj.Lambda.*;
import static fj.data.List.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;
import static org.junit.matchers.JUnitMatchers.*;

import java.util.*;

import org.apache.commons.collections.ListUtils;
import org.joda.time.LocalDate;
import org.junit.Test;

import fj.F;
import fj.F2;

public class ProductRepositoryTest {

	private static class ProductRepositoryImpl implements ProductRepository {

		private final List<Product> products;

		public ProductRepositoryImpl(Product... products) {
			this.products = Collections.unmodifiableList(Arrays.asList(products));
		}

		public Collection<Product> getAvailableProducts(LocalDate date) {
			// return getAvailableProductsIterator(date);
			return getAvailableProductsForLoop(date);
			// return getAvailableProductsRecursion(date);
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

		private Collection<Product> getAvailableProductsRecursion(final LocalDate date) {
			return getAvailableProductsRecursion(products, date, Collections.<Product> emptyList());
		}

		private List<Product> getAvailableProductsRecursion(final List<Product> products, final LocalDate date,	final List<Product> result) {
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
			return ListUtils.union(Arrays.asList(head), tail);
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
	}

	/*
	 * Test code below here
	 */

	@Test
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
