;----------------------------------------------------------
; Activity: Project Euler
; Date: March 3, 2016.
; Author:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

; Auxiliary Functions

;==========================================================

(defn factorial
  "Returns the factorial of the input number."
  [n]
  (reduce * (range (bigint 1) (inc n))))

(defn is-divisible-by-all?
  "Returns true if the given numbers are evenly divisible or
  false otherwise"
  [n bound]
  (if (= 1 bound) true
  (if (zero? (rem n bound))
    (is-divisible-by-all? n (dec bound))
    false)))

(defn is-prime?
  "Returns true if the given number is prime of false otherwise."
  [number]
  (cond (<= number 3) true
    (or (zero? (rem number 2)) (zero? (rem number 3))) false
    :else (loop [i 5]
            (cond (> (* i i) number) true
              (or (zero? (rem number i))
                (zero? (rem number (+ i 2)))) false
              :else (recur (+ 6 i))))))

(defn power
  "Returns the result of calculating base^exp."
  [base exp]
  (if (zero? exp) 1
    (reduce * (repeat exp (bigint base)))))

(defn palindrome?
  "Returns true if a sequence is a palindrome or false otherwise."
  [s]
  (= (seq (str s)) (reverse (str s))))

(defn number-of-digits
  "Returns the number of the digits of the given number."
  [number]
  (if (< number 10) 1
    (+ 1 (number-of-digits (quot number 10)))))

(defn reverse-number
  "Returns a new number with the digits in inverse order."
  [number reversed]
  (if (zero? number) reversed
    (reverse-number (quot number 10)
      (+ (rem number 10) (* reversed 10)))))

(defn lychrel?
  "Returns true if the given number is considered lychrel
  or false otherwise."
  [lych]
  (loop [it 1 lsum (bigint lych)]
    (cond (>= it 50) true
      (and (palindrome? lsum) (not= 1 it)) false
      :else (recur (inc it) (+ lsum (reverse-number lsum 0))))))

;==========================================================

; Exercises

;==========================================================

(defn sum-of-multiples-of-3-and-5
  "Returns the sum of all natural numbers that are multiples of
  3 and 5, less than the specified limit."
  [limit]
  (->>
    (range 1 limit)
    (filter (fn [x] (if (or (zero? (rem x 3)) (zero? (rem x 5))) x)))
    (reduce +)))

;==========================================================

(defn sum-of-even-fib-nums
  "Return the sum of all even fibonacci numbers less than the given
  limit."
  [limit]
  (->>
    (iterate (fn [[a b]] [b (+ a b)]) [1 2])
    (take-while (fn [[a b]] (< a limit)))
    (map first)
    (filter even?)
    (reduce +)))

;==========================================================

(defn largest-palindrome-3-dig-products
  "Returns the largest palindrome product of two 3-digit numbers."
  [& args]
  (reduce max (filter palindrome?
                (for [x (range 100 1000)
                      y (range 100 1000)] (* x y)))))

;==========================================================

(defn smallest-multiple
  "Returns the first number that is evenly divisible by all natural
  numbers from 1 to the given bound."
  [bound]
  (take 1 (filter (fn [x] (is-divisible-by-all? x bound))
            (iterate #(+ % bound) bound))))

;==========================================================

(defn sum-square-difference
  "Returns the difference between the sum of the squares and the
  square of the sum of all natural numbers less than or equal
  to the given limit."
  [limit]
  (let [rng (range 1 (inc limit))
        sum-of-sq (reduce + (map #(* % %) rng))
        sq-of-sum (* (reduce + rng) (reduce + rng))]
    (- sq-of-sum sum-of-sq)))

;==========================================================

(defn nth-prime
  "Returns the nth prime determined by the input number."
  [n]
  (let [primes (filter (fn [x] (if (is-prime? x) x))
                  (iterate #(+ % 2) 3))]
    (last (take (dec n) primes))))

;==========================================================

(defn special-pythagorean-triplet-1000
  "Returns the product abc of the triplet such that a < b < c and
  their sum equals 1000."
  [& args]
  (let [range-1000 (range 1 1000)]
    (for [a range-1000 b range-1000 c range-1000
      :when (and (< a b c) (= (* c c) (+ (* a a) (* b b)))
              (= 1000 (+ a b c)))] (* a b c))))

;==========================================================

(defn sum-of-primes
  "Returns the sum of all prime numbers less than the given bound."
  [bound]
  (let [primes (cons 2 (filter (fn [x] (if (is-prime? x) x))
                          (range 3 bound 2)))]
    (reduce + primes)))

;==========================================================

(defn factorial-digit-sum
  "Returns the sum of the digits of the factorial of the given number."
  [number]
  (loop [total 0
         nmbr (factorial number)]
    (if (zero? nmbr)
      total
      (recur (+ total (rem nmbr 10)) (quot nmbr 10)))))

;==========================================================

(defn fibonacci-number
  "Returns the index of the first number in the fibonacci sequence with the
  specified amount of digits."
  [digits]
  (inc (first (last (map-indexed vector (map second
    (take-while (fn [[a b]] (< (number-of-digits a) digits))
      (iterate (fn [[a b]] [(bigint b) (+ a b)]) [0 1]))))))))

;==========================================================

(defn self-powers
  "Returns the sum of all n^n natural numbers less than or equal
  to the given limit."
  [limit]
  (reduce + (map #(power % %) (range 1 (inc limit)))))

;==========================================================

(defn lychrel-numbers
  "Returns the amount of lychrel numbers below the given bound."
  [bound]
  (->>
    (range 1 bound)
    (filter lychrel?)
    (count)))

;==========================================================

; Tests and Results

(println "1) Problem #1")
(println (sum-of-multiples-of-3-and-5 10))
(println (sum-of-multiples-of-3-and-5 1000))

(println "\n2) Problem #2")
(println (sum-of-even-fib-nums 4000000))

(println "\n3) Problem #4")
(println (largest-palindrome-3-dig-products))

(println "\n4) Problem #5")
(println (smallest-multiple 10))
(println (smallest-multiple 20))

(println "\n5) Problem #6")
(println (sum-square-difference 10))
(println (sum-square-difference 100))

(println "\n6) Problem #7")
(println (nth-prime 6))
(println (nth-prime 10001))

(println "\n7) Problem #9")
(println (special-pythagorean-triplet-1000))

(println "\n8) Problem #10")
(println (sum-of-primes 10))
(println (sum-of-primes 2000000))

(println "\n9) Problem #20")
(println (factorial-digit-sum 10))
(println (factorial-digit-sum 100))

(println "\n10) Problem #25")
(println (fibonacci-number 3))
(println (fibonacci-number 1000))

(println "\n11) Problem #48")
(println (self-powers 10))
(println (self-powers 1000))

(println "\n12) Problem #55")
(println (lychrel-numbers 10000))
