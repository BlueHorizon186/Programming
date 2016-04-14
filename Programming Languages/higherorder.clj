;----------------------------------------------------------
; Activity: Higher-Order Functions
; Date: February 11, 2016
; Author:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

; Import Clojure unit test framework
(use 'clojure.test)

; /******* Functions *******/

; Auxiliary approximating function. It is used to handle rounding errors
; when working with large floating-point numbers.
(defn approx=
  "Checks if x is approximately equal to y. Returns true 
  if |x - y| < epsilon, or false otherwise."
  [epsilon x y]
  (< (Math/abs (- x y)) epsilon))

; My Map-Indexed
(defn my-map-indexed
  "Takes two arguments: A function and a list. It returns a list
  consisting of the result of applying f to 0 and the first item of
  the list, and so on until the list is exhausted. The function
  takes 2 arguments: index and item."
  [f l]
  (loop [fun f
         lst l
         res '()
         cnt 0]
    (if (empty? lst)
      (reverse res)
      (let [next-el (fun cnt (first lst))]
        (recur fun (rest lst) (cons next-el res) (inc cnt))))))

; My Drop-While
(defn my-drop-while
  "Takes two arguments: A function and a list. It returns a list of
  items from the list dropping the initial items that evaluate to true
  when passed as arguments to the given function. The function accepts
  one argument only."
  [fun lst]
  (cond
    (empty? lst) '()
    (fun (first lst)) (my-drop-while fun (rest lst))
    :else lst))

; The Bisection Method
(defn bisection
  "Takes a, b, and f as arguments. It finds the corresponding root using
  the bisection method."
  [a b f]
  (let [c (double (/ (+ a b) 2))
        fa (f a)
        fb (f b)
        fc (f c)]
    (cond
      (< (Math/abs fc) 1E-15) c
      (neg? (* fa fc)) (bisection a c f)
      :else (bisection b c f))))

; The Derivative Function
; Pending
(defn deriv
  "Takes f and h as its arguments, and returns a new function that
  takes x as argument, and which represents the derivate of f given a
  certain value for h."
  [f h]
  (fn [x] (/ (- (f (+ x h)) (f x)) h)))

; The Integration Function
(defn integral
  "Takes as arguments a, b, n, and f. It returns the value of the
  integral, using Simpson's rule."
  [a b n f]
  (loop [h (/ (- b a) n)
         k 0
         sum 0]
    (let [yk (f (+ (* k h) a))]
      (cond
        (= k n) (* (/ h 3) (+ sum yk))
        (zero? k) (recur h (inc k) (+ sum yk))
        (even? k) (recur h (inc k) (+ (* 2 yk) sum))
        :else (recur h (inc k) (+ (* 4 yk) sum))))))

; /******* Unit Tests *******/

; My Map-Indexed
(deftest test-my-map-indexed
  (is (= () (my-map-indexed vector ())))
  (is (= '([0 a] [1 b] [2 c] [3 d])
         (my-map-indexed vector '(a b c d))))
  (is (= '(10 4 -2 8 5 5 13)
         (my-map-indexed + '(10 3 -4 5 1 0 7))))
  (is (= '(0 1 -4 3 1 0 6)
         (my-map-indexed min '(10 3 -4 5 1 0 7)))))

; My Drop-While
(deftest test-my-drop-while
  (is (= () (my-drop-while neg? ())))
  (is (= '(0 1 2 3 4)
         (my-drop-while
          neg?
          '(-10 -9 -8 -7 -6 -5 -4 -3 -2 -1 0 1 2 3 4))))
  (is (= '(2 three 4 five)
         (my-drop-while
          symbol?
          '(zero one 2 three 4 five))))
  (is (= '(0 one 2 three 4 five)
         (my-drop-while
          symbol?
          '(0 one 2 three 4 five)))))

; Bisection Method
(deftest test-bisection
  (is (approx= 0.0001 3.0 (bisection 1 4 (fn [x] (* (- x 3) (+ x 4))))))
  (is (approx= 0.0001 -4.0 (bisection -5 0 (fn [x] (* (- x 3) (+ x 4))))))
  (is (approx= 0.0001 Math/PI (bisection 1 4 (fn [x] (Math/sin x)))))
  (is (approx= 0.0001 (* 2 Math/PI) (bisection 5 10 (fn [x] (Math/sin x)))))
  (is (approx= 0.0001 1.618033988749895
                    (bisection 1 2 (fn [x] (- (* x x) x 1)))))
  (is (approx= 0.0001 -0.6180339887498948
                    (bisection -10 1 (fn [x] (- (* x x) x 1))))))

; Derivatives
(defn f [x] (* x x x))
(def df (deriv f 0.001))
(def ddf (deriv df 0.001))
(def dddf (deriv ddf 0.001))

(deftest test-deriv
  (is (approx= 0.05 75 (df 5)))
  (is (approx= 0.05 30 (ddf 5)))
  (is (approx= 0.05 6 (dddf 5))))

; Integrals
(deftest test-integral
  (is (= 1/4 (integral 0 1 10 (fn [x] (* x x x)))))
  (is (= 21/4
          (integral 1 2 10
          (fn [x]
              (integral 3 4 10
              (fn [y]
                  (* x y))))))))

; Call the unit tests!
(run-tests)
