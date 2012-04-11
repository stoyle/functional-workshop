package no.knowit.scala_functional.euler_one

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Assert._
import org.junit.{Ignore, Test}

@RunWith(classOf[JUnit4])
class MultiplesOf3And5Test {

  @Test
  @Ignore
  def find_sum_of_all_multiples_of_3_and_5_below_10 {
    val res = (1 until 10).sum // Hint needs a filter
    assertEquals("Wrong sum", 23, res)
  }

  @Test
  @Ignore
  def find_sum_of_all_multiples_of_3_and_5_below_1000 {
    val res = (1 until 1000).sum // Hint needs a filter
    assertEquals("Wrong sum", 233168L, res)
  }

  @Test
  @Ignore
  def find_sum_of_all_multiples_of_3_and_5_below_10_000_000 {
    val res = (1L until 10000000L).view.sum // Hint needs a filter. Using non-strict view here or it will use too much memory
    assertEquals("Wrong sum", 23333331666668L, res)
  }

}