;----------------------------------------------------------
; Problem 62: Re-implement Iterate
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def new-iterate
  (fn iter [fun init] (lazy-seq
    (cons init (iter fun (fun init))))))

(deftest test-new-iterate
  (is (= (take 5 (new-iterate #(* 2 %) 1)) [1 2 4 8 16]))
  (is (= (take 100 (new-iterate inc 0)) (take 100 (range))))
  (is (= (take 9 (new-iterate #(inc (mod % 3)) 1))
    (take 9 (cycle [1 2 3])))))

(run-tests)
