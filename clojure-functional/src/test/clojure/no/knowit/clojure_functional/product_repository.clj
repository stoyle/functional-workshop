(ns no.knowit.clojure_functional.product-repository
  (:use [clojure.test])
  (:import [no.knowit.java_functional.product Product])
  (:import [org.joda.time LocalDate]))

(defn product-is-available [local-date product-coll]
  (filter
    #(.isAvailable %1 local-date) product-coll))


(deftest empty_list_when_no_products
  (is (= (product-is-available (LocalDate.) []))))

(deftest product_available_when_matching_date
  (let [today (new LocalDate)
        product (new Product "Cool product" today today)]
    (is (not-empty (product-is-available today [product])))
    (is (.contains (product-is-available today [product]) product))))

(deftest empty_list_when_no_products_matching_date
  (let [today (new LocalDate)
        yesterday (.minusDays today 1)
        product (new Product "Cool product" yesterday yesterday)]
    (is (empty (product-is-available today [product])))))

(deftest find_discontinued_products
  (let [yesterday (.minusDays (new LocalDate) 1)
        product (new Product "Cool product" yesterday yesterday)]
    (is (not-empty (product-is-available yesterday [product])))))



(run-tests)