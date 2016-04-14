;----------------------------------------------------------
; Problem 118: Re-implement Map
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def new-map
  (fn nm [fun seqn] (lazy-seq
    (if (empty? seqn) '()
      (cons (fun (first seqn)) (nm fun (rest seqn)))))))

(deftest test-new-map
  (is (= [3 4 5 6 7] (new-map inc [2 3 4 5 6])))
  (is (= (repeat 10 nil)
    (new-map (fn [new-map] nil) (range 10))))
  (is (= [1000000 1000001]
   (->> (new-map inc (range))
        (drop (dec 1000000))
        (take 2)))))

(run-tests)
