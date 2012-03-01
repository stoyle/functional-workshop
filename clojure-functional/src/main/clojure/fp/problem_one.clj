(ns fp.problem-one)

(defn- multiples-of-3-or-5 [num]
  (let [mod-of #(zero? (mod num %1))]
    (or (mod-of 3) (mod-of 5))))

(defn- sum-modof-coll [coll]
  (reductions +
    (filter multiples-of-3-or-5 coll)))

(defn euler-one-sum-beneath [limit]
 (last (sum-modof-coll (range limit))))
