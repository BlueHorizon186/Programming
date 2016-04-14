;----------------------------------------------------------
; Problem 46: Reverse Interleave
;----------------------------------------------------------

(use 'clojure.test)

(def rev-interleave
  (fn [coll n] (for [i (range n)] (take-nth n (drop i coll)))))

(deftest test-rev-interleave
  (is (= (rev-interleave [1 2 3 4 5 6] 2) '((1 3 5) (2 4 6))))
  (is (= (rev-interleave (range 9) 3)
    '((0 3 6) (1 4 7) (2 5 8))))
  (is (= (rev-interleave (range 10) 5)
    '((0 5) (1 6) (2 7) (3 8) (4 9)))))

(run-tests)
