;----------------------------------------------------------
; Problem 137: Digits and Bases
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def to-base
  (fn [n b] (if (zero? n) (list n)
    (reverse (map #(rem % b)
      (take-while pos? (iterate #(quot % b) n)))))))

(deftest test-to-base
  (is (= [1 2 3 4 5 0 1] (to-base 1234501 10)))
  (is (= [0] (to-base 0 11)))
  (is (= [1 0 0 1] (to-base 9 2)))
  (is (= [1 0] (let [n (rand-int 100000)](to-base n n))))
  (is (= [16 18 5 24 15 1] (to-base Integer/MAX_VALUE 42))))

(run-tests)
