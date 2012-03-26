package no.knowit.java_functional.euler_two;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

// http://projecteuler.net/problem=2
// Each new term in the Fibonacci sequence is generated by adding the previous two terms. By starting with 1 and 2, the first 10 terms will be:
//
// 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, ...
//
// By considering the terms in the Fibonacci sequence whose values do not exceed four million, find the sum of the even-valued terms.

public class FibonacciTest {

    // Implement classical fibonnacci, the slow one!
    // f(x) = f(x - 1) + f(x - 2) where f(1) = 1 and f(0) = 0
    public static long fibSlow(long n) {
        return n;
    }

    // Create a tail call optimized version of fiboncacci
    // f(n) = fr(n, 0, 1)
    // fr(n, b, a) = fr(n-1, a + b, b) where fr(1) = 1 and fr(0) = 0
    public static long fibFastRecursive(long num) {
        return fibFastRecur(num, 1, 0);
    }

    private static long fibFastRecur(long num, long b, long a) {
        return a;
    }

    // For illustration, the imperative fibonnacci, which is quite fast.
    public static int fibIterative(long num) {
        int prev1=0, prev2=1;
        for(int i=0; i<num; i++) {
            int savePrev1 = prev1;
            prev1 = prev2;
            prev2 = savePrev1 + prev2;
        }
        return prev1;
    }
    
    interface Fib {
        long calc(long num);
    }

    // This is the actual solution to Euler two. We plug in selected fibonacci "function" through the Fib interface.
    private long eulerTwo(long range, Fib fib) {
        long res = 0, current = 0, counter = 0;
        do{
            current = fib.calc(counter++);
            if (current % 2 == 0) {
                res += current;
            }
        } while(current < range);
        return res;
    }

    @Test
    @Ignore
    public void euler_two_fibonnaci_sum() throws Exception {
        Fib fibSlow = new Fib() {
            public long calc(long num) {
                return fibSlow(num);
            }
        };
        Fib fibFastRecursive = new Fib() {
            public long calc(long num) {
                return fibFastRecursive(num);
            }
        };
        Fib fibIterative = new Fib() {
            public long calc(long num) {
                return fibIterative(num);
            }
        };
        assertThat(eulerTwo(4000000, fibSlow), is(equalTo(4613732L)));
        assertThat(eulerTwo(4000000, fibFastRecursive), is(equalTo(4613732L)));
        assertThat(eulerTwo(4000000, fibIterative), is(equalTo(4613732L)));
        //assertThat(eulerTwo(Long.MAX_VALUE, fibIterative), is(equalTo(3770056902373173214L)));
        //assertThat(eulerTwo(Long.MAX_VALUE, fibSlow), is(equalTo(3770056902373173214L)));
        //assertThat(eulerTwo(Long.MAX_VALUE, fibFastRecursive), is(equalTo(3770056902373173214L)));

    }


}
