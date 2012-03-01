import org.junit.Test
import org.junit.Assert._

class Problem1Scala {

  @Test
  def find_sum_of_all_multiples_of_3_and_5_below_10 {
    val numbers = (1 until 10).view
    val res = numbers filter (i => i % 3 == 0 || i % 5 == 0) reduce (_ + _)
    assertEquals("Wrong sum", 23, res)
  }

  @Test
  def find_sum_of_all_multiples_of_3_and_5_below_10_000_000 {
    val numbers = (1 until 10000000).view
    val res = numbers filter (i => i % 3 == 0 || i % 5 == 0) map (_.longValue) reduce (_ + _)
    assertEquals("Wrong sum", 23333331666668L, res)
  }
}