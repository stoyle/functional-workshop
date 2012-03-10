(ns no.knowit.clojure-functional.euler-two
  (:use [clojure.test]))

(def fib
  (fn [num]
    (if (< num 2) num
      (+ (fib (- num 1))
        (fib (- num 2))))))

(def fib-mem
  (memoize (fn [num]
             (if (< num 2) num
               (+ (fib-mem (- num 1))
                 (fib-mem (- num 2)))))))

(defn fib-tail [num]
  (loop [num num, b 1N a 0N]
    (if (< num 2) a
      (recur (dec num) (+ a b) b))))

(defn fib-lazy []
  ((fn f [a b]
     (cons a
       (lazy-seq
         (f b (+ a b)))))
    0N 1N))

(defn unchunk [s]
  (when (seq s)
    (lazy-seq
      (cons (first s)
        (unchunk (next s))))))

(defn fib-range [f]
  (map f (unchunk (range))))

(defn even-nums [coll]
  (filter even? coll))

(defn contents-less-than [limit coll]
  (take-while #(> limit %) coll))

(defn euler-two [limit f]
  (apply +
    (contents-less-than limit
      (even-nums (f)))))

(deftest euler-two-fibonacci-sum
  (is (= (euler-two 4000000 #(fib-range fib)) 4613732))
  (is (= (euler-two 4000000 #(fib-range fib-mem)) 4613732))
  (is (= (euler-two 4000000 #(fib-range fib-tail)) 4613732))
  (is (= (euler-two 4000000 fib-lazy) 4613732))
  (is (= (euler-two Long/MAX_VALUE fib-lazy) 3770056902373173214)))

(run-tests)