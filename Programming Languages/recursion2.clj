;----------------------------------------------------------
; Activity: Recursive Functions, Part II
; Date: February 4, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

; Import Clojure unit test framework
(use 'clojure.test)

; /******* Auxiliary Functions *******/

(defn expand
    "Takes a number 'n' and a symbol as arguments. Returns a list
    containing the symbol 'n' times."
    [cnt sym]
    (if (zero? cnt)
        '()
        (cons sym (expand (dec cnt) sym))))

; /******* Functions *******/

(defn my-repeat
    "Takes a number 'n' and any data 'x' as its arguments. It
    returns a list that contains n copies of x."
    [n x]
    (if (zero? n)
        '()
        (cons x (my-repeat (dec n) x))))
    
(defn invert-pairs
    "Takes as an argument a list of vectors containing two elements
    each. It returns a new list with every vector pair inverted."
    [vec-list]
    (if (empty? vec-list)
        '()
        (let [vctr (first vec-list)
              a (get vctr 0)
              b (get vctr 1)]
            (cons [b a] (invert-pairs (rest vec-list))))))
        
(defn enlist
    "Surrounds in a list every upper-level element of the list it takes as input."
    [lst]
    (if (empty? lst)
        '()
        (cons (list (first lst)) (enlist (rest lst)))))

(defn my-interleave
    "Takes two arguments: the lists a and b. It returns a list containing the
    first element of a, followed by the first element of b, and so on. The
    interleaving of the elements stops when either a or b is exhausted."
    [lst-a lst-b]
    (loop [res '()
           a lst-a
           b lst-b]
        (if (or (empty? a) (empty? b))
            (reverse res)
            (recur (cons (first b) (cons (first a) res)) (rest a) (rest b)))))

(defn my-flatten
    "Removes all the interior parenthesis of the list it takes as input."
    [with-nested]
    (if (empty? with-nested)
        '()
        (let [el (first with-nested)]
            (if (list? el)
                (concat (my-flatten el) (my-flatten (rest with-nested)))
                (cons el (my-flatten (rest with-nested)))))))

(defn exchange
    "Takes three arguments: two non-list values x1 and x2, and a list lst.
    It returns a list with the same elements as lst, except that all
    occurrences of x1 are replaced by x2 and vice versa, including any
    occurrences inside nested lists."
    [x1 x2 lst]
    (if (empty? lst)
        '()
        (let [next-el (first lst)]
            (if (list? next-el)
                (cons (exchange x1 x2 next-el) (exchange x1 x2 (rest lst)))
                (cond
                    (= next-el x1)
                        (cons x2 (exchange x1 x2 (rest lst)))
                    (= next-el x2)
                        (cons x1 (exchange x1 x2 (rest lst)))
                    :else
                        (cons next-el (exchange x1 x2 (rest lst))))))))

(defn insert
    "Takes two arguments: a number 'n' and a list of numbers lst in ascending
    order. It returns a new list with the same elements as lst but inserting
    'n' in its corresponding place."
    [n lst]
    (loop [res '()
           nmbr n
           orig lst]
        (if (empty? orig)
            (reverse (cons nmbr res))
            (if (< nmbr (first orig))
                (concat (reverse (cons nmbr res)) orig)
                (recur (cons (first orig) res) n (rest orig))))))

(defn my-sort
    "Takes an unordered list of numbers as an argument, and returns a new
    list with the same elements but in ascending order."
    [num-list]
    (if (empty? num-list)
        '()
        (insert (first num-list) (my-sort (rest num-list)))))

(defn binary
    "Takes an integer 'n' greater than or equal to zero as input.
    Returns a list with a sequence of ones and zeros equivalent to
    the binary representation of n."
    [n]
    (loop [bin '()
           nmbr n]
        (if (zero? nmbr)
            bin
            (recur (cons (rem nmbr 2) bin) (quot nmbr 2)))))

(defn prime-factors
    "Takes an integer 'n' greater than zero as input, and returns a list
    containing the prime factors of n in ascending order."
    [n]
    (loop [facts '()
           part n
           mult 2]
        (if (< part 2)
            (reverse facts)
            (let [r (rem part mult)]
                (if (zero? r)
                    (recur (cons mult facts) (quot part mult) 2)
                    (recur facts part (inc mult)))))))

(defn compress
    "Takes a list as its argument. If the list contains consecutive
    repeated elements, they are be replaced with a single copy of
    the element."
    [with-dups]
    (loop [res '()
           wd with-dups
           curr nil]
        (if (empty? wd)
            (reverse res)
            (let [nx (first wd)]
                (if (= nx curr)
                    (recur res (rest wd) nx)
                    (recur (cons nx res) (rest wd) nx))))))

(defn pack
    "Takes a list as its argument. If the list contains consecutive
    repeated elements they are placed in separate sublists."
    [unpacked-list]
    (loop [packed '()
           nx-pack '()
           unp unpacked-list
           curr (first unp)]
        (if (empty? unp)
            (reverse packed)
            (let [nx (second unp)]
                (if (= nx curr)
                    (recur packed (cons curr nx-pack) (rest unp) nx)
                    (recur (conj packed (cons curr nx-pack)) '()
                        (rest unp) nx))))))

(defn encode
    "Takes a list as its argument. Consecutive duplicates of elements
    in the list are encoded as vectors [n e], where 'n' is the number
    of duplicates of the element 'e'."
    [plain-list]
    (loop [coded '()
           lst plain-list
           curr (first lst)
           cnt 1]
        (if (empty? lst)
            (reverse coded)
            (let [nx (second lst)]
                (if (= nx curr)
                    (recur coded (rest lst) nx (inc cnt))
                    (recur (conj coded [cnt curr])
                        (rest lst) nx 1))))))

(defn encode-modified
    "Takes a list as its argument. It works the same as the encode
    function, but if an element has no duplicates it is simply copied
    into the result list."
    [plain-list]
    (loop [coded '()
           lst plain-list
           curr (first lst)
           cnt 1]
        (if (empty? lst)
            (reverse coded)
            (let [nx (second lst)]
                (if (= nx curr)
                    (recur coded (rest lst) nx (inc cnt))
                    (if (= cnt 1)
                        (recur (cons curr coded) (rest lst) nx 1)
                        (recur (cons [cnt curr] coded)
                            (rest lst) nx 1)))))))

; Pending
(defn decode
    "Takes as its argument an encoded list with a structure as the output
    from the encoded-modified function. It returns the decoded version
    of that list."
    [coded-list]
    (if (empty? coded-list)
        '()
        (let [next-el (first coded-list)]
            (if (vector? next-el)
                (concat (expand (get next-el 0) (get next-el 1))
                    (decode (rest coded-list)))
                (cons next-el (decode (rest coded-list)))))))

; /******* Unit Tests *******/

(deftest test-my-repeat
    (is (= () (my-repeat 0 'x)))
    (is (= '(6 6 6) (my-repeat 3 6)))
    (is (= '((ha ha) (ha ha) (ha ha)) (my-repeat 3 '(ha ha))))
    (is (= '(true true true true true) (my-repeat 5 true))))

(deftest test-invert-pairs
    (is (= () (invert-pairs ())))
    (is (= '([1 a][2 a][1 b][2 b]))(invert-pairs '([a 1][a 2][b 1][b 2])))
    (is (= '([1 January][2 February][3 March]) 
            (invert-pairs '([January 1][February 2][March 3])))))
        
(deftest test-enlist
    (is (= () (enlist ())))
    (is (= '((a) (b) (c)) (enlist '(a b c))))
    (is (= '(((1 2 3)) (4) ((5)) (7) (8)) (enlist '((1 2 3) 4 (5) 7 8)))))

(deftest test-my-interleave
    (is (= () (my-interleave () ())))
    (is (= () (my-interleave '(a) ())))
    (is (= () (my-interleave () '(1))))
    (is (= '(a 1 b 2 c 3 d 4 e 5) (my-interleave '(a b c d e) '(1 2 3 4 5))))
    (is (= '(a 1 b 2 c 3 d 4) (my-interleave '(a b c d e) '(1 2 3 4))))
    (is (= '(a 1 b 2 c 3 d 4) (my-interleave '(a b c d) '(1 2 3 4 5))))
    (is (= '(a 1) (my-interleave '(a) '(1 2 3 4 5))))
    (is (= '(a 1) (my-interleave '(a b c d e) '(1)))))

(deftest test-my-flatten
    (is (= () (my-flatten ())))
    (is (= '(a b c d e) (my-flatten '((a b) ((c) d (e))))))
    (is (= '(one two three four) 
            (my-flatten '(((one) ((two))) () (three (())) four)))))

(deftest test-exchange
    (is (= () (exchange 'x 'y ())))
    (is (= '(d b c a) (exchange 'a 'd '(a b c d))))
    (is (= '((42) true ((cool (true)) (42))))
            (exchange true 42 '((true) 42 ((cool (42)) (true))))))

(deftest test-insert
    (is (= '(14) (insert 14 ())))
    (is (= '(4 5 6 7 8) (insert 4 '(5 6 7 8))))
    (is (= '(1 3 5 6 7 9 16) (insert 5 '(1 3 6 7 9 16))))
    (is (= '(1 5 6 10) (insert 10 '(1 5 6)))))

(deftest test-my-sort
    (is (= () (my-sort ())))
    (is (= '(0 1 3 3 4 6 7 8 9) (my-sort '(4 3 6 8 3 0 9 1 7))))
    (is (= '(1 2 3 4 5 6) (my-sort '(1 2 3 4 5 6))))
    (is (= '(1 5 5 5 5 5 5) (my-sort '(5 5 5 1 5 5 5)))))

(deftest test-binary
    (is (= () (binary 0)))
    (is (= '(1 1 1 1 0) (binary 30)))
    (is (= '(1 0 1 1 0 0 0 0 0 1 0 0 0 0 1 1) (binary 45123))))

(deftest test-prime-factors
    (is (= () (prime-factors 1)))
    (is (= '(2 3) (prime-factors 6)))
    (is (= '(2 2 2 2 2 3) (prime-factors 96)))
    (is (= '(97) (prime-factors 97)))
    (is (= '(2 3 3 37) (prime-factors 666))))

(deftest test-compress
    (is (= () (compress ())))
    (is (= '(a b c d) (compress '(a b c d))))
    (is (= '(a b c a d e) (compress '(a a a a b c c a a d e e e e))))
    (is (= '(a) (compress '(a a a a a a a a a a)))))

(deftest test-pack
    (is (= () (pack ())))
    (is (= '((a a a a) (b) (c c) (a a) (d) (e e e e))
            (pack '(a a a a b c c a a d e e e e))))
    (is (= '((1) (2) (3) (4) (5)) (pack '(1 2 3 4 5))))
    (is (= '((9 9 9 9 9 9 9 9 9)) (pack '(9 9 9 9 9 9 9 9 9)))))

(deftest test-encode
    (is (= () (encode ())))
    (is (= '([4 a] [1 b] [2 c] [2 a] [1 d] [4 e])
            (encode '(a a a a b c c a a d e e e e))))
    (is (= '([1 1] [1 2] [1 3] [1 4] [1 5]) (encode '(1 2 3 4 5))))
    (is (= '([9 9]) (encode '(9 9 9 9 9 9 9 9 9)))))

(deftest test-encode-modified
    (is (= () (encode-modified ())))
    (is (= '([4 a] b [2 c] [2 a] d [4 e])
            (encode-modified '(a a a a b c c a a d e e e e))))
    (is (= '(1 2 3 4 5) (encode-modified '(1 2 3 4 5))))
    (is (= '([9 9]) (encode-modified '(9 9 9 9 9 9 9 9 9)))))

(deftest test-decode
    (is (= () (decode ())))
    (is (= '(a a a a b c c a a d e e e e)
            (decode '([4 a] b [2 c] [2 a] d [4 e]))))
    (is (= '(1 2 3 4 5) (decode '(1 2 3 4 5))))
    (is (= '(9 9 9 9 9 9 9 9 9) (decode '([9 9])))))

; Call the unit tests!
(run-tests)
