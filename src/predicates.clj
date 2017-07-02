(ns predicates)

(defn sum-f [f g x]
  (+ (f x) (g x)))

(defn less-than [n] #(< %1 n))

(defn equal-to [n]
  (fn [x] (== x n)))

(defn set->predicate [a-set]
  (fn [x] (contains? a-set x)))

(filter (set->predicate #{1 2 3 nil}) [2 nil 4 nil 6])

(defn pred-and [pred1 pred2]
  #(and (pred1 %) (pred2 %)))

(filter (pred-and pos? odd?) [1 2 -4 0 6 7 -3])

(defn pred-or [pred1 pred2]
  #(or (pred1 %) (pred2 %)))

(defn whitespace? [character]
  (Character/isWhitespace character))

(defn blank? [string]
    (every? whitespace? string))

(defn has-award? [book award]
  (contains? (:awards book) award))

(defn HAS-ALL-THE-AWARDS? [book awards]
  (let [won-awards (filter awards (:awards book))]
    (= (count won-awards) (count awards))))

(defn my-some [pred a-seq]
  (first (filter identity (map pred a-seq))))

(my-some neg? [1 3 5 0 7 -1 8])
(my-some neg? [1 3 5 0 7 8])
(my-some first [[false] [1]])

(defn my-every? [pred a-seq]
  (=
    (count a-seq)
    (count (filter pred a-seq))))

(defn prime? [n]
  (let [pred #(= 0 (mod n %))]
    (not (some pred (range 2 n)))))

(filter prime? (range 2 50))
