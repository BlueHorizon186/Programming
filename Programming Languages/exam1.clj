;==========================================================
; Student Name: Ivan David Diaz Sanchez
; Student ID: A01371166
;==========================================================

(use 'clojure.test)

;==========================================================
(defn gcd
  "Returns the greatest common divisor of the two positive non-zero 
  integers a and b."
  [a b]
  (cond
    (= a b) a
    (> a b) (gcd (- a b) b)
    :else (gcd a (- b a))))

(defn lcm
  "Returns the least common multiple of the two positive non-zero 
  integers a and b."
  [a b]
  (/ (* a b) (gcd a b)))

;==========================================================
(defn next-triangle-line
  "Returns the next Pascal Triangle line calculated from the given
  one as a list argument. The list cannot be empty."
  [line]
  (loop [new-len (inc (count line))
         new-line '()
         ind 0]
    (cond
      (empty? new-line) (recur new-len (cons 1 new-line) (inc ind))
      (= (count new-line) (count line)) (reverse (cons 1 new-line))
      :else
        (let [next-el (+ (nth line ind) (nth line (dec ind)))]
          (recur new-len (cons next-el new-line) (inc ind))))))

(defn pascal
  "Returns a list with all the elements of row n of Pascal's triangle.
  Rows are numbered starting at zero."
  [n]
  (loop [curr-lvl 0
         desired n
         last-lvl '()]
    (cond
      (zero? curr-lvl) (recur (inc curr-lvl) desired (cons 1 last-lvl))
      (= (inc desired) curr-lvl) last-lvl
      :else (recur (inc curr-lvl) desired (next-triangle-line last-lvl)))))

(defn prev-line
  "Auxiliary function to the pascal-other function."
  [lst]
  (if (< (count lst) 2)
    '()
    (cons (+ (first lst) (second lst))
          (prev-line (rest lst)))))

(defn pascal-other
  "Returns a list with all the elements of row n of Pascal's triangle.
  Rows are numbered starting at zero."
  [n]
  (if (zero? n)
    '(1)
    (concat '(1) (prev-line (pascal-other (dec n))) '(1))))

;==========================================================
(defn index-in-list-by-predicate
  "Takes three arguments: any data x, a predicate function f, and a
  list lst. It returns the index of the first occurrence of x in lst,
  or false if not found. The first element of the list is at index 0.
  The comparison between x and the elements of lst is carried out by the
  predicate function f, which takes two arguments and returns true or
  false."
  [x f lst]
  (loop [ind 0
         el x
         fun f
         l lst]
    (if (empty? l)
      false
      (let [to-test (first l)]
        (if (fun el to-test)
          ind
          (recur (inc ind) el fun (rest l)))))))

(defn index-in-list-by-predicate-other
  "Takes three arguments: any data x, a predicate function f, and a
  list lst. It returns the index of the first occurrence of x in lst,
  or false if not found. The first element of the list is at index 0.
  The comparison between x and the elements of lst is carried out by the
  predicate function f, which takes two arguments and returns true or
  false."
  [x f lst]
  (loop [lst lst
         ind 0]
    (cond
      (empty? lst) false
      (f x (first lst)) ind
      :else (recur (rest lst) (inc ind)))))

;==========================================================
(deftest test-lcm
  (is (= 12 (lcm 4 6)))
  (is (= 80 (lcm 16 20)))
  (is (= 20 (lcm 10 20)))  
  (is (= 42 (lcm 21 6)))
  (is (= 561741 (lcm 123 4567)))
  (is (= 98084900347 (lcm 412619 237713))))

;==========================================================
(deftest test-pascal
  (is (= '(1) (pascal 0)))
  (is (= '(1 1) (pascal 1)))
  (is (= '(1 2 1) (pascal 2)))
  (is (= '(1 3 3 1) (pascal 3)))
  (is (= '(1 4 6 4 1) (pascal 4)))
  (is (= '(1 5 10 10 5 1) (pascal 5)))
  (is (= '(1 6 15 20 15 6 1) (pascal 6)))
  (is (= '(1 7 21 35 35 21 7 1) (pascal 7)))
  (is (= '(1 10 45 120 210 252 210 120 45 10 1) (pascal 10)))
  (is (= '(1 20 190 1140 4845 15504 38760 77520 125970 167960 184756 167960
    125970 77520 38760 15504 4845 1140 190 20 1) (pascal 20)))
  (is (= '(1 50 1225 19600 230300 2118760 15890700 99884400 536878650 2505433700
    10272278170 37353738800 121399651100 354860518600 937845656300 2250829575120
    4923689695575 9847379391150 18053528883775 30405943383200 47129212243960
    67327446062800 88749815264600 108043253365600 121548660036300
    126410606437752 121548660036300 108043253365600 88749815264600
    67327446062800 47129212243960 30405943383200 18053528883775 9847379391150
    4923689695575 2250829575120 937845656300 354860518600 121399651100
    37353738800 10272278170 2505433700 536878650 99884400 15890700 2118760
    230300 19600 1225 50 1) (pascal 50))))

;==========================================================
(deftest test-index-in-list-by-predicate
  (is (= 5 
         (index-in-list-by-predicate
           42 
           = 
           '(10 -1 3 23 -8 42 9))))
  (is (= 2 
         (index-in-list-by-predicate
           10 
           #(<= (Math/abs (- %1 %2)) 1)
           '(4 -15 9 0 -2 10 24))))
  (is (= 2
         (index-in-list-by-predicate
           "Bilbo"
           #(= (first %1) (first %2))
           '("Thorin" "Dwalin" "Balin" "Gloin" "Kili" "Fili"
             "Dori" "Nori" "Ori" "Oin" "Bifur" "Bombur" "Bofur"))))
  (is (= 3 
         (index-in-list-by-predicate 
           first
           identical?
           (list rest map second first list cons))))
  (is (not 
         (index-in-list-by-predicate
           22 
           = 
           '(10 -1 3 23 -8 42 9))))
  (is (not 
         (index-in-list-by-predicate
           12 
           #(<= (Math/abs (- %1 %2)) 1)
           '(4 -15 9 0 -2 10 24))))
  (is (not
         (index-in-list-by-predicate
           "Arwen"
           #(= (first %1) (first %2))
           '("Thorin" "Dwalin" "Balin" "Gloin" "Kili" "Fili"
             "Dori" "Nori" "Ori" "Oin" "Bifur" "Bombur" "Bofur"))))
  (is (not 
         (index-in-list-by-predicate 
           concat
           identical?
           (list rest map second first list cons)))))

;==========================================================
(run-tests)
        