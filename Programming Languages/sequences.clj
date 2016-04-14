;----------------------------------------------------------
; Activity: Using the Sequence API
; Date: February 25, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

; Functions

;==========================================================

(defn positives
  "Takes a list of numbers as its argument, and returns a new list
  that only contains the positive numbers of the input list."
  [num-lst]
  (filter pos? num-lst))

;==========================================================

(defn dot-product
  "Takes two lists, 'a' and 'b', as arguments. It returns the result
  of performing the dot product of a times b."
  [a b]
  (reduce + (map #(* %1 %2) a b)))

;==========================================================

(defn pow
  "Takes two arguments as input: A number 'b' and a positive integer
  'exp'. It returns the result of computing 'b' raised to the power
  of 'exp'."
  [b exp]
  (if (zero? exp)
    1
    (last (take exp (iterate #(* % b) b)))))

;==========================================================

(defn replic
  "Takes two arguments: A list 'lst' and an integer number 'n'
  greater than or equal to zero. It returns a new list that 
  replicates 'n' times each element contained in 'lst'."
  [n lst]
  (mapcat #(repeat n %) lst))

;==========================================================

(defn expand
  "Takes a list as its argument. It returns a list where the first
  element of the list appears one time, the second elements appears
  two times, and so on."
  [compressed-lst]
  (flatten (map-indexed repeat (cons 0 compressed-lst))))

;==========================================================

(defn largest
  "Takes as argument a nonempty list of numbers. It returns the
  largest value contained in the list."
  [num-lst]
  (reduce #(if (> %1 %2) %1 %2) num-lst))

;==========================================================

(defn drop-every
  "Takes two arguments: An integer number 'n' greater than or equal
  to 1, and a list 'lst'. It returns a new list that drops every
  n-th element from 'lst'."
  [n lst]
  (flatten (partition-all (dec n) n lst)))

;==========================================================

(defn rotate-left
  "Takes two arguments: An integer number 'n' and a list 'lst'.
  It returns the list that results from rotating 'lst' a total
  of 'n' elements to the left. If 'n' is negative, it rotates to
  the right."
  [n lst]
  (if (empty? lst)
    '()
    (let [size (count lst)
          parts (if (pos? n) (seq (split-at (rem n size) lst))
                  (seq (split-at (+ size (rem n size)) lst)))]
      (concat (second parts) (first parts)))))

;==========================================================

(defn factors
  "Returns a list with all factors of 'n'."
  [n]
  (filter #(zero? (rem n %)) (range 1 (inc n))))

(defn gcd
  "Takes two positive integer arguments greater than zero. Returns
  the Greatest Common Divisor (gcd) of the two numbers."
  [x y]
  (let [x-facts (factors x)
        y-facts (factors y)]
    (last (mapcat (fn [elem]
                    (if (some #{elem} y-facts) (list elem)))
            x-facts))))

;==========================================================

(defn insert-everywhere
  "Takes two arguments as input: an object 'obj' and a list 'lst'. It
  returns a new list with all the possible ways in which 'obj' can be
  inserted into every position of 'lst'."
  [obj lst]
  (concat (map-indexed (fn [i, e] 
                          (concat (take i lst) (list obj) (list e)
                          (drop (inc i) lst))) lst)
    (list (concat lst (list obj)))))


;==========================================================

; Tests

(deftest test-positives
  (is (= () (positives '())))
  (is (= () (positives '(-4 -1 -10 -13 -5))))
  (is (= '(3 6) (positives '(-4 3 -1 -10 -13 6 -5))))
  (is (= '(4 3 1 10 13 6 5) (positives '(4 3 1 10 13 6 5)))))

(deftest test-dot-product
  (is (= 0 (dot-product () ())))
  (is (= 32 (dot-product '(1 2 3) '(4 5 6))))
  (is (= 21.45 (dot-product '(1.3 3.4 5.7 9.5 10.4) '(-4.5 3.0 1.5 0.9 0.0)))))

(deftest test-pow
  (is (= 1 (pow 0 0)))
  (is (= 0 (pow 0 1)))
  (is (= 1 (pow 5 0)))
  (is (= 5 (pow 5 1)))
  (is (= 125 (pow 5 3)))
  (is (= 25 (pow -5 2)))
  (is (= -125 (pow -5 3)))
  (is (= 1024 (pow 2 10)))
  (is (= 525.21875 (pow 3.5 5)))
  (is (= 129746337890625 (pow 15 12)))
  (is (= 3909821048582988049 (pow 7 22))))

(deftest test-replic
  (is (= () (replic 7 ())))
  (is (= () (replic 0 '(a b c))))
  (is (= '(a a a) (replic 3 '(a))))
  (is (= '(1 1 1 1 2 2 2 2 3 3 3 3 4 4 4 4) (replic 4 '(1 2 3 4)))))

(deftest test-expand
  (is (= () (expand ())))
  (is (= '(a) (expand '(a))))
  (is (= '(1 2 2 3 3 3 4 4 4 4) (expand '(1 2 3 4))))
  (is (= '(a b b c c c d d d d e e e e e) (expand '(a b c d e)))))

(deftest test-largest
  (is (= 31 (largest '(31))))
  (is (= 5 (largest '(1 2 3 4 5))))
  (is (= -1 (largest '(-1 -2 -3 -4 -5))))
  (is (= 52 (largest '(32 -1 45 12 -42 52 17 0 21 2)))))

(deftest test-drop-every
  (is (= () (drop-every 5 ())))
  (is (= '(1 2 3) (drop-every 4 '(1 2 3 4))))
  (is (= '(1 3 5 7) (drop-every 2 '(1 2 3 4 5 6 7 8))))
  (is (= '(1 3 5 7 9) (drop-every 2 '(1 2 3 4 5 6 7 8 9))))
  (is (= '(a b d e g h j) (drop-every 3 '(a b c d e f g h i j))))
  (is (= '(a b c d e f g h i j) 
         (drop-every 20 '(a b c d e f g h i j))))
  (is (= () (drop-every 1 '(a b c d e f g h i j)))))

(deftest test-rotate-left
  (is (= () (rotate-left 5 ())))
  (is (= '(a b c d e f g) (rotate-left 0 '(a b c d e f g))))
  (is (= '(b c d e f g a) (rotate-left 1 '(a b c d e f g))))
  (is (= '(g a b c d e f) (rotate-left -1 '(a b c d e f g))))
  (is (= '(d e f g a b c) (rotate-left 3 '(a b c d e f g))))
  (is (= '(e f g a b c d) (rotate-left -3 '(a b c d e f g))))  
  (is (= '(a b c d e f g) (rotate-left 7 '(a b c d e f g))))
  (is (= '(a b c d e f g) (rotate-left -7 '(a b c d e f g))))
  (is (= '(b c d e f g a) (rotate-left 8 '(a b c d e f g))))
  (is (= '(g a b c d e f) (rotate-left -8 '(a b c d e f g))))
  (is (= '(d e f g a b c) (rotate-left 45 '(a b c d e f g))))
  (is (= '(e f g a b c d) (rotate-left -45 '(a b c d e f g)))))

(deftest test-gcd
  (is (= 1 (gcd 13 7919)))
  (is (= 4 (gcd 20 16)))
  (is (= 6 (gcd 54 24)))
  (is (= 7 (gcd 6307 1995)))
  (is (= 12 (gcd 48 180)))
  (is (= 14 (gcd 42 56))))

(deftest test-insert-everywhere
  (is (= '((1)) (insert-everywhere 1 ())))
  (is (= '((1 a) (a 1)) (insert-everywhere 1 '(a))))
  (is (= '((1 a b c) (a 1 b c) (a b 1 c) (a b c 1))
         (insert-everywhere 1 '(a b c))))
  (is (= '((1 a b c d e)
           (a 1 b c d e)
           (a b 1 c d e)
           (a b c 1 d e)
           (a b c d 1 e)
           (a b c d e 1))
          (insert-everywhere 1 '(a b c d e))))
  (is (= '((x 1 2 3 4 5 6 7 8 9 10)
           (1 x 2 3 4 5 6 7 8 9 10)
           (1 2 x 3 4 5 6 7 8 9 10)
           (1 2 3 x 4 5 6 7 8 9 10)
           (1 2 3 4 x 5 6 7 8 9 10)
           (1 2 3 4 5 x 6 7 8 9 10)
           (1 2 3 4 5 6 x 7 8 9 10)
           (1 2 3 4 5 6 7 x 8 9 10)
           (1 2 3 4 5 6 7 8 x 9 10)
           (1 2 3 4 5 6 7 8 9 x 10)
           (1 2 3 4 5 6 7 8 9 10 x))
          (insert-everywhere 'x '(1 2 3 4 5 6 7 8 9 10)))))

(run-tests)
