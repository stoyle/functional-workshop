(ns no.knowit.clojure-functional.exercise-1-intro-tests
  (:use [clojure.test]))

; Placeholders do not touch.
(defn __ [& args] false)
(def ___ '())

#_
(deftest very-basic-types
  (is (= 1 1))
  ;; Only one thing is really true...  
  (is (true? ___))
  ;; Now what is the concatenation of those Characters
  (is (= (str \a \b \c) ___))
  ;; Use vector literal
  (is (= '(1 2 3) ___))
  ;; Use list literal
  (is (= [1 2 3] ___))
  ;; Use list function
  (is (= [1 2 3] ___))
  ;; Use vector function
  (is (= [1 2 3] ___)))

#_
(deftest use-conjoin
  ;; conjoin is a special function that works with most datastructures.
  ;; It nows where the right place is to append an element.
  ;; Use (doc conj) for info
  ;; Oh, and by the way. 'are' creates a template (using a macro),
  ;; for alle the following test form, which need to be in pairs in this case.
  (are [x y] (= x y)
    (conj [1] 2) ___
    (conj [1 2] 3 4) ___
    (conj '(2 1) 3 4) ___
    (conj #{2 1} 3 4) ___))

#_
(deftest how-to-count-stuff
  (are [x y] (= x y)
       ___ (count '(1 2 3 4 5))
       ___ (count [1 2 3 4])
       ___ (count (range 10))
       ___ (count {:a 1, :b 2, :c 3, :d 4})
       ___ (count "En banan")))

#_
(deftest how-to-find-length-of-something
  ;; First class functions are so cool. Put in the right function in let
  ;; and all assertions should be correct.
  (let [f __]
    (are [x y] (= x y)
      (f '(1 2 3 3 1)) 5
      (f "Hello World") 11
      (f [[1 2] [3 4] [5 6]]) 3
      (f '(13)) 1
      (f '(:a :b :c)) 3)))

#_
(deftest using-if
  ;; Lots of things are truthy, i.e. is handled as true in e.g. if expressions.
  (are [x y] (= x y)
    (if (> 1 0)
      true
      false)
    ___

    (if (> 3 2 1)
      true
      false)
    ___

    (if (> 3 2 3 1)
      true
      false)
    ___

    (if nil
      "nil is truthy"
      "nil is falsey"
      ) ___

    (if true
      "true is truthy"
      "true is falsey"
      ) ___

    (if false
      "false is truthy"
      "false is falsey"
      ) ___

    (if '(1)
      "list is truthy"
      "list is falsey"
      ) ___

    (if '()
      "empty list is truthy"
      "empty list is falsey"
      ) ___

    (if (Object.)
      "Object is truthy"
      "Object is falsey"
      ) ___))

#_
(deftest dealing-with-lists
  (are [x y] (= x y)
  ;; Should be able to use one simple function call to get result
       (__ '(1 2 3 4))  1
       (__ '(1 2 3 4 5)) 5
       ;; this can be solved with a function that takes an extra param and the list.
       (__ '(1 2 3 4)) '(1 2)))

#_
(deftest operating-on-lists
  (are [x y] (= x y)
  ;; Here you should try to apply the correct function to get the expected result
       (apply __ [1 2 3 4]) 10
       (apply __ [4 3 2 1]) -2
       (apply __ [1 2 3 4]) 24
       ))

#_
(deftest how-to-filter-out-the-stuff-you-want
  (are [x y] (= x y)
  ;; Filter is cool. Can you write or use a cool function to get correct result?
    (filter __ '(1 2 3 4 5)) '(1 3 5)
    (filter __ '(1 2 3 4 5)) '(2 4)))

#_
(deftest use-map-to-double-all-numbers-in-a-sequence
  ;; Write a function in the let form that doubles its input
  (let [double __]
    (are [x y] (= x y)
      (map double '(1 2 3)) '(2 4 6)
      (map double '(5 10 15)) '(10 20 30))))

#_
(deftest define-a-function-that-checks-string-longer-than
  ;; Write a function in the let form that that checks that the input String is longer than number input.
  (let [longer-than? __]
    (are [x y] (= x y)
         (longer-than? "long string" 5) true
         (longer-than? "short" 5) false
         (longer-than? nil 2) false)))


(run-tests)
