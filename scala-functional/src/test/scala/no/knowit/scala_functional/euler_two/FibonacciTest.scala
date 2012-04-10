package no.knowit.scala_functional.euler_two

import annotation.tailrec
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.is
import org.junit.Assert.assertThat
import org.junit.{Ignore, Test}


// http://projecteuler.net/problem=2
// Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
//
// 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
//
// By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

class FibonacciTest {

  // Implement classical fibonnacci, the slow one!
  // f(x) = f(x - 1) + f(x - 2) where f(1) = 1 and f(0) = 0
  def fibSlow(n: Long): Long =
    n

  // Create a tail call optimized version of fiboncacci
  // f(n) = fr(n, 0, 1)
  // fr(n, b, a) = fr(n-1, a + b, b) where fr(1) = 1 and fr(0) = 0
  def fibFastRecursive(num: Long) = {
    // @tailrec
    def fib(num: Long, b: Long, a: Long): Long =
      Long.MaxValue;
    fib(num, 1, 1)
  }

  // Create a lazy sequence of fibonacci numbers.
  // Create a function which calls itself lazily (Stream.cons).
  lazy val lazyFib: Stream[Long] = {
    if (true) throw new Exception("Implement!")
    Stream.continually(0)
  }

  // For illustration, the imperative fibonnacci, which is quite fast.
  def fibIterative(num: Long) = {
    var prev1 = 0;
    var prev2 = 1;
    for (i <- 0 until num.toInt) {
      val savePrev1 = prev1
      prev1 = prev2
      prev2 = savePrev1 + prev2
    }
    prev2
  }

  // This is the Euler two solution, but implemented imperatively with mutable data. Can you do better?
  def eulerTwo(range: Long, fibFun: Long => Long) = {
    var res, current, counter = 0L
    while (current < range) {
      current = fibFun(counter)
      if (current % 2 == 0) {
        res += current
      }
      counter += 1
    }
    res
  }


  @Test
  @Ignore
  def euler_two_fibonacci_sum {
    assertThat(eulerTwo(4000000, fibSlow), is(equalTo(4613732L)));
    assertThat(eulerTwo(4000000, fibFastRecursive), is(equalTo(4613732L)));
    assertThat(eulerTwo(4000000, fibIterative), is(equalTo(4613732L)));
    assertThat(eulerTwo(4000000, x => lazyFib(x.toInt)), is(equalTo(4613732L)));
  }

}
