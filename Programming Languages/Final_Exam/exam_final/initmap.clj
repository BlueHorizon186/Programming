;==========================================================
; Name: Ivan David Diaz Sanchez
; ID: A01371166
;
; Run your program like this:
;
;     lein exec initmap.clj
;
;==========================================================

(use 'clojure.test)

;==========================================================

(defn initmap
  "Creates a new map containing as keys every item in ks.
  Each key should be mapped to the same value v."
  [ks v]
  (into {} (for [k ks] {k v})))

;==========================================================
(deftest test-initmap
  (is (= {} (initmap [] 0)))
  (is (= {:a 0, :b 0, :c 0}
         (initmap [:a :b :c] 0)))
  (is (= {1 :ok, 2 :ok, 3 :ok, 4 :ok, 5 :ok}
         (initmap [1 2 3 4 5] :ok)))
  (is (= {"one" :little-indians, "two" :little-indians, "three" :little-indians}
         (initmap ["one" "two" "three"] :little-indians)))
  (is (= { 0 Double/POSITIVE_INFINITY,
           1 Double/POSITIVE_INFINITY,
           4 Double/POSITIVE_INFINITY,
           9 Double/POSITIVE_INFINITY,
          16 Double/POSITIVE_INFINITY,
          25 Double/POSITIVE_INFINITY,
          36 Double/POSITIVE_INFINITY,
          49 Double/POSITIVE_INFINITY,
          64 Double/POSITIVE_INFINITY,
          81 Double/POSITIVE_INFINITY}
         (initmap (map #(* % %) (range 10)) (/ 1.0 0)))))

;==========================================================
(run-tests)