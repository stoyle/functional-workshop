package no.knowit.java_functional.euler_two;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class FibonacciTest {

    public static long fibSlow(long n) {
        if (n <= 1) return n;
        else return fibSlow(n - 1) + fibSlow(n - 2);
    }

    public static long fibFastRecursive(long num) {
        return fibFastRecur(num, 1, 0);
    }

    private static long fibFastRecur(long num, long b, long a) {
        if (num < 1) return a;
        return fibFastRecur(num - 1, a + b, b);
    }

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
