(ns no.knowit.clojure-functional.euler-one
  (:use [clojure.test]))

;; http://projecteuler.net/problem=1
;
;; If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
;
;; Find the sum of all the multiples of 3 or 5 below 1000.

; Production code

(defn sum
  "Sums up numbers in a collection"
  [coll] (apply + coll))

(defn multiples-of-3-or-5 [num]
  (let [mod-of #(zero? (mod num %1))]
    (or (mod-of 3) (mod-of 5))))

;; Use standard filter, sum and range.
(defn euler-one-sum-beneath [limit]
  (sum (filter multiples-of-3-or-5 (range 1 limit))))

(defn- sum-modof-coll [coll]
  (reductions +
    (filter multiples-of-3-or-5 coll)))

;; Try implementing it using a lazy collection. Hint reductions.
(defn euler-one-sum-beneath-lazy [limit]
  (last
    (sum-modof-coll (range 1 limit))))


; Test code
(deftest euler-one-sum
  (is (= (euler-one-sum-beneath 10) 23))
  (is (= (euler-one-sum-beneath 1000) 233168)))

(deftest euler-one-lazy
  (is (= (euler-one-sum-beneath-lazy 10) 23))
  (is (= (euler-one-sum-beneath-lazy 1000) 233168)))

(run-tests)