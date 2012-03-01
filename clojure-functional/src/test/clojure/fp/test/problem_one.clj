(ns fp.test.problem-one
  (:use [fp.problem-one])
  (:use [clojure.test]))

(deftest euler-one
  (is (= (euler-one-sum-beneath 10) 23))
  (is (= (euler-one-sum-beneath 1000) 233168)))

(run-tests)