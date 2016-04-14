;----------------------------------------------------------
; Problem 60: Sequence Reductions
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

; Function

(def sequence-reduction
  (fn red ([fun coll] (red fun (first coll) (rest coll)))
          ([fun coll1 coll2] (lazy-seq
            (if (empty? coll2) (list coll1)
              (cons coll1
                (red fun (fun coll1 (first coll2)) (rest coll2))))))))

; Unit Tests

(deftest test-sequence-reduction
  (is (= (take 5 (sequence-reduction + (range))) [0 1 3 6 10]))
  (is (= (sequence-reduction conj [1] [2 3 4])
    [[1] [1 2] [1 2 3] [1 2 3 4]]))
  (is (= (last (sequence-reduction * 2 [3 4 5]))
    (reduce * 2 [3 4 5]) 120)))

(run-tests)
