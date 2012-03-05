(ns no.knowit.clojure-functional.euler-one
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

(run-tests)