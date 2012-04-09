package no.knowit.scala_functional.product

import org.hamcrest.CoreMatchers._
import org.joda.time.LocalDate
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Test
import org.junit.Assert._
import org.junit.matchers.JUnitMatchers._

import no.knowit.java_functional.product.Product

@RunWith(classOf[JUnit4])
class ProductRepositoryTest {

  class ProductRepository(val products: List[Product]) {
    def getAvailableProducts(date: LocalDate): Seq[Product] = products filter { p => p.isAvailable(date) }
  }

  @Test
  def empty_list_when_no_products {
    val productRepo = new ProductRepository(Nil)
    val available = productRepo.getAvailableProducts(LocalDate.now())
    assertThat(available.size, is(equalTo(0)))
  }

  @Test
  def product_available_when_matching_date() {
    val today = LocalDate.now()
    val coolProduct = new Product("Cool product", today, today)
    val productRepo = new ProductRepository(List(coolProduct))

    val available = productRepo.getAvailableProducts(today)
    assertThat(available.size, is(equalTo(1)))
    assertTrue(available.contains(coolProduct))
  }

  @Test
  def empty_list_when_no_products_matching_date() {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val discontinuedProduct = new Product("Discontinued product", yesterday, yesterday)

    val productRepo = new ProductRepository(List(discontinuedProduct))

    val available = productRepo.getAvailableProducts(today)
    assertThat(available.isEmpty, is(equalTo(true)))
  }

  @Test
  def find_discontinued_products() {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val discontinuedProduct = new Product("Discontinued product", yesterday, yesterday)

    val productRepo = new ProductRepository(List(discontinuedProduct))

    val available = productRepo.getAvailableProducts(yesterday)
    assertTrue(available.contains(discontinuedProduct))
  }

  @Test
  def find_discontinued_then_new_products() {
    val today = LocalDate.now()
    val yesterday = today.minusDays(1)
    val discontinuedProduct = new Product("Discontinued product", yesterday, yesterday)
    val newProduct = new Product("New product", today, null)

    val productRepo = new ProductRepository(List(discontinuedProduct, newProduct))

    val availableYesterday = productRepo.getAvailableProducts(yesterday)
    assertTrue(availableYesterday.contains(discontinuedProduct))

    val availableToday = productRepo.getAvailableProducts(today)
    assertTrue(availableToday.contains(newProduct))
  }

}