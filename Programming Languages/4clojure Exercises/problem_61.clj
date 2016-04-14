;----------------------------------------------------------
; Problem 61: Map Construction
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def make-map
  (fn [vec1 vec2] (into {} (map #(assoc {} %1 %2) vec1 vec2))))

(deftest test-make-map
  (is (= (make-map [:a :b :c] [1 2 3]) {:a 1, :b 2, :c 3}))
  (is (= (make-map [1 2 3 4] ["one" "two" "three"])
    {1 "one", 2 "two", 3 "three"}))
  (is (= (make-map [:foo :bar] ["foo" "bar" "baz"])
    {:foo "foo", :bar "bar"})))

(run-tests)
