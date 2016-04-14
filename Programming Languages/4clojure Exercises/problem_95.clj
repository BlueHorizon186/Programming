;----------------------------------------------------------
; Problem 95: To Tree or Not to Tree
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def is-binary-tree?
  (fn binary-tree? [tree] (cond (coll? tree)
    (and (= 3 (count tree)) (binary-tree? (second tree))
          (binary-tree? (last tree)))
    (nil? tree) true
    :else false)))

(deftest test-is-binary-tree?
  (is (= (is-binary-tree? '(:a (:b nil nil) nil)) true))
  (is (= (is-binary-tree? '(:a (:b nil nil))) false))
  (is (= (is-binary-tree? [1 nil [2 [3 nil nil] [4 nil nil]]])
    true))
  (is (= (is-binary-tree? [1 [2 nil nil] [3 nil nil] [4 nil nil]])
    false))
  (is (= (is-binary-tree? [1 [2 [3 [4 nil nil] nil] nil] nil])
    true))
  (is (= (is-binary-tree? [1 [2 [3 [4 false nil] nil] nil] nil])
    false))
  (is (= (is-binary-tree? '(:a nil ())) false)))

(run-tests)
