(ns no.knowit.clojure_functional.product-repository
  (:use [clojure.test])
  (:import [no.knowit.java_functional.product Product])
  (:import [org.joda.time LocalDate]))

(def product-list (atom []))

(defn product-is-available [local-date]
  (filter
    #(.isAvailable %1 local-date) @product-list))


(defn clear-state-fn [test-func]
  (reset! product-list [])
  (test-func))

(use-fixtures :each clear-state-fn)


(deftest empty_list_when_no_products
  (is (= (product-is-available (LocalDate.)) [])))

(deftest product_available_when_matching_date
  (let [today (new LocalDate)
        product (new Product "Cool product" today today)]
    (swap! product-list
      #(conj % product))
    (is (not-empty (product-is-available today)))
    (is (.contains (product-is-available today) product))))

(deftest empty_list_when_no_products_matching_date
  (let [today (new LocalDate)
        yesterday (.minusDays today 1)
        product (new Product "Cool product" yesterday yesterday)]
    (swap! product-list
      #(conj % product))
    (is (empty (product-is-available today)))))

(run-tests)