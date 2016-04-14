;----------------------------------------------------------
; Problem 63: Group a Sequence
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def group-seqs
  (fn [fun s] (map #(assoc {} (fun %) [%]) s)))

(deftest test-group-seqs
  (is (= (group-seqs #(> % 5) [1 3 6 8])
    {false [1 3], true [6 8]}))
  (is (= (group-seqs #(apply / %) [[1 2] [2 4] [4 6] [3 6]])
   {1/2 [[1 2] [2 4] [3 6]], 2/3 [[4 6]]}))
  (is (= (group-seqs count [[1] [1 2] [3] [1 2 3] [2 3]])
   {1 [[1] [3]], 2 [[1 2] [2 3]], 3 [[1 2 3]]})))

(run-tests)
