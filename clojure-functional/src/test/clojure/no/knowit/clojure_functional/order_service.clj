(ns no.knowit.clojure-functional.order-service
  (:use [clojure.test])
  (:import no.knowit.java_functional.product.Product)
  (:import [no.knowit.java_functional.order TrainJourney Order])
  (:import [org.joda.time DateTime]))

; Production code

(defn create-order [train-journey]
  (do (Thread/sleep 200)
    (new Order train-journey 123.0)))

; Map over collection to create a new collection of new types.
(defn create-order-coll [itinerary-coll]
  itinerary-coll)

; How would you do that in parallel? First version is not fast enough!
(defn create-order-coll-par [itinerary-coll]
  itinerary-coll)


; Test code

; Helper function to create itineraries
(defn create-itineraries []
  (let [from "OSLO S"
        to "BERGEN"
        departure-time (new DateTime)]
  (repeatedly 5
    #(new TrainJourney from to departure-time
       (.plusHours departure-time 2)))))


; Generalise test expression, takes a create-order function
(defn test-expr [create-order-fn]
  (let [itineraries (create-itineraries)
        result (doall (create-order-fn itineraries))]
    (is (= 5 (count result)))
    (is (every? #(= (class %) Order) result) true)))

#_
(deftest test-creating-orders
  (test-expr create-order-coll))

#_
(deftest test-creating-orders-within-time-limit
  (let [start (System/currentTimeMillis)
        _ (test-expr create-order-coll-par)
        stop (- (System/currentTimeMillis) start)]
  (is (< stop 400))))

(run-tests)