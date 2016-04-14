;----------------------------------------------------------
; Problem 83: A Half-Truth
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def half-truth
  (fn [& bools] (if (or (every? #(true? %) bools)
    (every? #(false? %) bools))
      false
      true)))

(deftest test-half-truth
  (is (= false (half-truth false false)))
  (is (= true (half-truth true false)))
  (is (= false (half-truth true)))
  (is (= true (half-truth false true false)))
  (is (= false (half-truth true true true)))
  (is (= true (half-truth true true true false))))

(run-tests)
