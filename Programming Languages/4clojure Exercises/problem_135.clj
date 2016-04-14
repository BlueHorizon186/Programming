;----------------------------------------------------------
; Problem 135: Infix Calculator
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def infix
  (fn [& exprs] (reduce (fn [a [op b]] (op a b))
    (first exprs) (partition 2 (rest exprs)))))

(deftest test-infix
  (is (= 7  (infix 2 + 5)))
  (is (= 42 (infix 38 + 48 - 2 / 2)))
  (is (= 8  (infix 10 / 2 - 1 * 2)))
  (is (= 72 (infix 20 / 2 + 2 + 4 + 8 - 6 - 10 * 9))))

(run-tests)
