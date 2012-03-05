(ns no.knowit.clojure-functional.euler-one.problem-one
  (:use [clojure.test]))

; Running code

(defn- multiples-of-3-or-5 [num]
  (let [mod-of #(zero? (mod num %1))]
    (or (mod-of 3) (mod-of 5))))

(defn- sum [coll]
  (apply + coll))

(defn euler-one-sum-beneath [limit]
  (sum (filter multiples-of-3-or-5 (range 1 limit))))

(defn- sum-modof-coll [coll]
  (reductions +
    (filter multiples-of-3-or-5 coll)))

(defn euler-one-sum-beneath-lazy [limit]
  (last
    (sum-modof-coll (range 1 limit))))

(defn euler-one-sum-beneath-eager [limit]
  (reduce +
    (filter multiples-of-3-or-5 (range 1 limit))))

; Test code

(deftest euler-one-sum
  (is (= (euler-one-sum-beneath 10) 23))
  (is (= (euler-one-sum-beneath 1000) 233168)))

(deftest euler-one-lazy
  (is (= (euler-one-sum-beneath-lazy 10) 23))
  (is (= (euler-one-sum-beneath-lazy 1000) 233168)))

(deftest euler-one-eager
  (is (= (euler-one-sum-beneath-eager 10) 23))
  (is (= (euler-one-sum-beneath-eager 1000) 233168)))


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
    (if (< num 1) a
      (recur (- num 1) (+ a b) b))))

(defn fib-lazy []
  ((fn f [a b]
     (cons a
       (lazy-seq
         (f b (+ a b)))))
    0N 1N))

(defn fib-range [f]
  (map f (range)))

(defn even-nums [coll]
  (filter even? coll))

(defn contents-less-than [limit coll]
  (take-while #(> limit %) coll))


(defn in-side-out [limit f]
  (apply +
    (contents-less-than limit
      (even-nums (f)))))


(defn composed [limit f]
  ((comp #(apply + %)
     #(contents-less-than limit %) even-nums f)))


(defn arrowed [limit f]
  (->> (f) even-nums (contents-less-than limit) (apply +)))


(deftest euler-two-fibonacci-sum
  (is (= (in-side-out 40 #(fib-range fib)) 44))
  (is (= (in-side-out 4000000 #(fib-range fib-mem)) 4613732))
  (is (= (in-side-out 4000000 #(fib-range fib-tail)) 4613732))
  (is (= (in-side-out 4000000 fib-lazy) 4613732)))

(run-tests)