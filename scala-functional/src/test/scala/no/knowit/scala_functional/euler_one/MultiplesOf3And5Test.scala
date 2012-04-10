package no.knowit.scala_functional.euler_one

import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.junit.Test
import org.junit.Assert._

@RunWith(classOf[JUnit4])
class MultiplesOf3And5Test {

  @Test
  def find_sum_of_all_multiples_of_3_and_5_below_10 {
    val numbers = (1 until 10).view
    val res = numbers filter (i => i % 3 == 0 || i % 5 == 0) reduce (_ + _)
    assertEquals("Wrong sum", 23, res)
  }

  @Test
  def find_sum_of_all_multiples_of_3_and_5_below_1000 {
    val numbers = (1 until 1000).view
    val res = numbers filter (i => i % 3 == 0 || i % 5 == 0) map (_.longValue) reduce (_ + _)
    assertEquals("Wrong sum", 233168L, res)
  }

  /*
  @Test
  def find_sum_of_all_multiples_of_3_and_5_below_10_000_000 {
    val numbers = (1 until 10000000).view
    val res = numbers filter (i => i % 3 == 0 || i % 5 == 0) map (_.longValue) reduce (_ + _)
    assertEquals("Wrong sum", 23333331666668L, res)
  }
  */
}