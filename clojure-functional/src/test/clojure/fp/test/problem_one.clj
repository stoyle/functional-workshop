(ns fp.test.problem-one
  (:use [fp.problem-one] :reload-all)
  (:use [clojure.test]))

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