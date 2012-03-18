(ns no.knowit.clojure-functional.product-repository
  (:use [clojure.test])
  (:import no.knowit.java_functional.product.Product)
  (:import [org.joda.time LocalDate]))

; Production code

; Use filter and provide your own function. Remember how you call a native Java method?
(defn product-is-available [local-date product-coll]
  (filter
    #(.isAvailable %1 local-date) product-coll))

; Write a recursive version not using filter. This is a bit advanced. Possible solutions include
; loop recur, conj and if. Remember to always terminate the recurring, or you will get infinite recursion.
(defn product-is-available-recur [local-date product-coll]
  (loop [cl product-coll result []]
    (if-let [h (first cl)]
      (recur (rest cl)
        (conj result
          (if (.isAvailable h local-date) h [])))
      result)))

; Define which function you want to test here
(def product-func product-is-available)

; Test code
(deftest empty_list_when_no_products
  (is (= (product-func (LocalDate.) []))))

(deftest product_available_when_matching_date
  (let [today (new LocalDate)
        product (new Product "Cool product" today today)]
    (is (not-empty (product-func today [product])))
    (is (.contains (product-func today [product]) product))))

(deftest empty_list_when_no_products_matching_date
  (let [today (new LocalDate)
        yesterday (.minusDays today 1)
        product (new Product "Cool product" yesterday yesterday)]
    (is (empty? (product-func today [product])))))

(deftest filter-one-products-matching-date
  (let [today (new LocalDate)
        yesterday (.minusDays today 1)
        product-one (new Product "Cool product" yesterday yesterday)
        product-two (new Product "Todays product" today today)]
    (is (= 1 (count (product-func today [product-one product-two]))))))

(deftest find_discontinued_products
  (let [yesterday (.minusDays (new LocalDate) 1)
        product (new Product "Cool product" yesterday yesterday)]
    (is (not-empty (product-func yesterday [product])))))

(run-tests)