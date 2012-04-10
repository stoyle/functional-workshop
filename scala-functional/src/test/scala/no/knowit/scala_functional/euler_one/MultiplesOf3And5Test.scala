package no.knowit.scala_functional.euler_one

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Test
import org.junit.Assert._

@RunWith(classOf[JUnit4])
class MultiplesOf3And5Test {

  @Test
  def find_sum_of_all_multiples_of_3_and_5_below_10 {
    val res = (1 until 10).filter(i => i % 3 == 0 || i % 5 == 0).sum
    assertEquals("Wrong sum", 23, res)
  }

  @Test
  def find_sum_of_all_multiples_of_3_and_5_below_1000 {
    val res = (1 until 1000).filter(i => i % 3 == 0 || i % 5 == 0).sum
    assertEquals("Wrong sum", 233168L, res)
  }

  @Test
  def find_sum_of_all_multiples_of_3_and_5_below_10_000_000 {
    val res = (1L until 10000000L).view.filter (i => i % 3 == 0 || i % 5 == 0).sum
    assertEquals("Wrong sum", 23333331666668L, res)
  }

}