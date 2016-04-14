(use 'clojure.test)

; Functions

;==========================================================

(def new-nth
  (fn [s n] (first (drop n s))))

(def new-range
  (fn [start end] (let [num-elems (- end start)]
    (take num-elems (iterate inc start)))))

(def max-value
  (fn [& args] (reduce #(if (> %1 %2) %1 %2) args)))

(def comparisons
  (fn [fun a b] (cond (fun a b) :lt
                  (fun b a) :gt
                  :else :eq)))

(def set-intersection
  (fn [set1 set2] (set (filter #(contains? set2 %) set1))))

(def symmetric-diff
  (fn [set1 set2] (set (concat
    (filter #(not (contains? set2 %)) set1)
    (filter #(not (contains? set1 %)) set2)))))

(def map-defaults
  (fn [def-val key-vals] (loop [key-map {}
                                remaining key-vals]
    (if (empty? remaining) key-map
      (recur (assoc key-map (first remaining) def-val)
                (rest remaining))))))

;==========================================================

; Unit Tests

(deftest test-new-nth
  (is (= (new-nth '(4 5 6 7) 2) 6))
  (is (= (new-nth [:a :b :c] 0) :a))
  (is (= (new-nth [1 2 3 4] 1) 2))
  (= (new-nth '([1 2] [3 4] [5 6]) 2) [5 6]))

(deftest test-new-range
  (is (= (new-range 1 4) '(1 2 3)))
  (is (= (new-range -2 2) '(-2 -1 0 1)))
  (is (= (new-range 5 8) '(5 6 7))))

(deftest test-max-value
  (is (= (max-value 1 8 3 4) 8))
  (is (= (max-value 1 8 3 4) 8))
  (is (= (max-value 45 67 11) 67)))

(deftest test-comparisons
  (is (= :gt (comparisons < 5 1)))
  (is (= :eq (comparisons (fn [x y]
    (< (count x) (count y))) "pear" "plum")))
  (is (= :lt (comparisons (fn [x y]
    (< (mod x 5) (mod y 5))) 21 3)))
  (is (= :gt (comparisons > 0 2))))

(deftest test-set-intersection
  (is (= (set-intersection #{0 1 2 3} #{2 3 4 5}) #{2 3}))
  (is (= (set-intersection #{0 1 2} #{3 4 5}) #{}))
  (is (= (set-intersection #{:a :b :c :d} #{:c :e :a :f :d})
    #{:a :c :d})))

(deftest test-symmetric-diff
  (is (= (symmetric-diff #{1 2 3 4 5 6} #{1 3 5 7}) #{2 4 6 7}))
  (is (= (symmetric-diff #{:a :b :c} #{}) #{:a :b :c}))
  (is (= (symmetric-diff #{} #{4 5 6}) #{4 5 6}))
  (is (= (symmetric-diff #{[1 2] [2 3]} #{[2 3] [3 4]})
    #{[1 2] [3 4]})))

(deftest test-map-defaults
  (is (= (map-defaults 0 [:a :b :c]) {:a 0 :b 0 :c 0}))
  (is (= (map-defaults "x" [1 2 3]) {1 "x" 2 "x" 3 "x"}))
  (is (= (map-defaults [:a :b] [:foo :bar])
    {:foo [:a :b] :bar [:a :b]})))

(run-tests)
