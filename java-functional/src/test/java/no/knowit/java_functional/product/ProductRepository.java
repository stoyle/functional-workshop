package no.knowit.java_functional.product;

import java.util.Collection;

import org.joda.time.LocalDate;

public interface ProductRepository {

	Collection<Product> getAvailableProducts(LocalDate date);

}
