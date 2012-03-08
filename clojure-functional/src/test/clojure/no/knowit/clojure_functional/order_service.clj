(ns no.knowit.clojure_functional.order-service
  (:use [clojure.test])
  (:import no.knowit.java_functional.product.Product)
  (:import [no.knowit.java_functional.order TrainJourney Order])
  (:import [org.joda.time DateTime]))


(defn create-order [train-journey]
  (do (Thread/sleep 200)
    (new Order train-journey 123.0)))

(defn create-order-coll [itinerary-coll]
  (map create-order itinerary-coll))

(defn create-order-coll-par [itinerary-coll]
  (pmap create-order itinerary-coll))

(defn create-itineraries []
  (let [from "OSLO S"
        to "BERGEN"
        departure-time (new DateTime)]
  (repeatedly 5
    #(new TrainJourney from to departure-time
       (.plusHours departure-time 2)))))

(deftest test-creating-orders
  (let [itineraries (create-itineraries)
        start (System/currentTimeMillis)
        result (doall (create-order-coll-par itineraries))
        stop (- (System/currentTimeMillis) start)]
    (is (= 5 (count result)))
    (is (< stop 400))))

(run-tests)