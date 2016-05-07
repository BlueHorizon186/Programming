;==========================================================
; Type your name and student ID here.

; Run your program like this:
;
;     lein exec defvars.clj
;
;==========================================================

(use 'clojure.test)

;==========================================================

(defmacro defvars
  "Defines as many global variables as items in exprs. The
  value of var-name is the name prefix for all these
  variables. The suffix is 0 for the first variable name,
  which is initialized with the first item in exprs. The
  next variable name has a 1 suffix and is initialized with
  the second item in exprs, and so on with all the
  remaining variables."
  [var-name & exprs]
  nil)

;==========================================================
(deftest test-defvars
  (is (= '(do)
         (macroexpand-1 '(defvars x))))
  (is (= '(do
            (def p0 (* (+ 1 2) (+ 3 4))))
         (macroexpand-1
           '(defvars p (* (+ 1 2) (+ 3 4))))))
  (is (= 21
         (do
           (defvars p (* (+ 1 2) (+ 3 4)))
           p0)))
  (is (= '(do
            (def my-var-0 :zero)
            (def my-var-1 :one)
            (def my-var-2 :two))
         (macroexpand-1
           '(defvars my-var- :zero :one :two))))
  (is (= [:zero :one :two]
         (do
           (defvars my-var- :zero :one :two)
           [my-var-0 my-var-1 my-var-2])))
  (is (= '(do
            (def a0 (+ 1 2))
            (def a1 (- 3 4))
            (def a2 (* 5 6))
            (def a3 (/ 7 8)))
         (macroexpand-1
           '(defvars a (+ 1 2) (- 3 4) (* 5 6) (/ 7 8)))))
  (is (= 263/8
         (do
           (defvars a (+ 1 2) (- 3 4) (* 5 6) (/ 7 8))
           (+ a0 a1 a2 a3)))))

;==========================================================
(run-tests)