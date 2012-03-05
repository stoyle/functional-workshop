(ns no.knowit.clojure-functional.euler-two
  (:use [clojure.test]))

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