;----------------------------------------------------------
; Activity: Recursive Functions, Part I
; Date: January 28, 2016.
; Authors:
;          A01371166 Iván David Díaz Sánchez
;----------------------------------------------------------

; Import Clojure unit test framework
(use 'clojure.test)

; /******* Functions *******/

(defn my-count
    "Returns the number of elements contained in its input list."
    [lst]
    (if (empty? lst)
        0
        (+ 1 (my-count (rest lst)))))

(defn add-list
    "Returns the sum of all the elements of its input list, or 0 if
    its empty. All the elements must be numbers."
    [num-lst]
    (if (empty? num-lst)
        0
        (+ (first num-lst) (add-list (rest num-lst)))))

(defn member?
    "Takes two arguments, any data value and a list. Returns true
    if x is contained in lst, false otherwise."
    [x lst]
    (if (empty? lst)
        false
        (if (= (first lst) x)
            true
            (member? x (rest lst)))))

(defn list-of-symbols?
    "Takes a list as its argument. It returns true if all the
    elements (possibly zero) contained in lst are symbols, or false
    otherwise."
    [lst]
    (if (empty? lst)
        true
        (if (not (symbol? (first lst)))
            false
            (list-of-symbols? (rest lst)))))
        
(defn my-last
    "Returns the last element of its input list, or nil of its empty."
    [input-lst]
    (if (empty? input-lst)
        nil
        (if (= 1 (count input-lst))
            (first input-lst)
            (my-last (rest input-lst)))))
        
(defn cons-end
    "Takes two arguments, any data value and a list. Returns a new
    list composed by the original list's elements and the new value
    appended at the end."
    [value origin-lst]
    (if (empty? origin-lst)
        (cons value origin-lst)
        (cons (first origin-lst)
              (cons-end value (rest origin-lst)))))

(defn my-reverse
    "Takes a list as an argument. It returns another list with the
    same elements as the input list, but in reverse order."
    [straight-lst]
    (if (empty? straight-lst)
        '()
        (cons-end (first straight-lst)
                  (my-reverse (rest straight-lst)))))
    
(defn my-butlast
    "Returns a list with the same elements as its input list but
    excluding the last element, or nil of its empty."
    [full-lst]
    (if (empty? full-lst)
        nil
        (if (= 1 (count full-lst))
            '()
            (cons (first full-lst) (my-butlast (rest full-lst))))))

(defn my-concat
    "Returns the resulting list of appending the two lists it takes
    as input."
    [list1 list2]
    (if (empty? list1)
        list2
        (if (empty? list2)
            list1
            (cons (first list1) (my-concat (rest list1) list2)))))

(defn deep-reverse
    "Takes a list as its input. It returns a list with the same elements
    as its input but in reverse order. If there are any nested lists,
    these are reversed too."
    [the-list]
    (if (empty? the-list)
        '()
        (let [next-el (first the-list)]
             (my-concat (deep-reverse (rest the-list))
                        (if (list? next-el)
                            (list (deep-reverse next-el))
                            (list next-el))))))

; /******* Unit Tests *******/

(deftest test-my-count
    (is (= 0 (my-count ())))
    (is (= 1 (my-count '(a))))
    (is (= 3 (my-count '(a b c)))))

(deftest test-add-list
    (is (= 0 (add-list ())))
    (is (= 10 (add-list '(2 4 1 3))))
    (is (= 55 (add-list '(1 2 3 4 5 6 7 8 9 10)))))

(deftest test-member?
    (is (not (member? 'a ())))
    (is (member? 'a '(a b c)))
    (is (member? 'a '(c b a b c)))
    (is (not (member? 'x '(a b c)))))

(deftest test-list-of-symbols?
    (is (list-of-symbols? ()))
    (is (list-of-symbols? '(a)))
    (is (list-of-symbols? '(a b c d e)))
    (is (not (list-of-symbols? '(a b c d 42 e))))
    (is (not (list-of-symbols? '(42 a b c)))))

(deftest test-my-last
    (is (nil? (my-last ())))
    (is (= 'x (my-last '(x))))
    (is (= 'c (my-last '(a b c)))))

(deftest test-cons-end
    (is (= '(b c d a) (cons-end 'a '(b c d))))
    (is (= '(a) (cons-end 'a ()))))

(deftest test-my-reverse
    (is (= () (my-reverse ())))
    (is (= '(c b a) (my-reverse '(a b c))))
    (is (= '(3 (b c d) a) (my-reverse '(a (b c d) 3)))))

(deftest test-my-butlast
    (is (nil? (my-butlast ())))
    (is (= () (my-butlast '(x))))
    (is (= '(a b) (my-butlast '(a b c)))))

(deftest test-my-concat
    (is (= '(a b c) (my-concat '(a b c) ())))
    (is (= '(1 2 3) (my-concat () '(1 2 3))))
    (is (= '(a b c 1 2 3) (my-concat '(a b c) '(1 2 3)))))

(deftest test-deep-reverse
    (is (= () (deep-reverse ())))
    (is (= '(3 (d c b) a) (deep-reverse '(a (b c d) 3))))
    (is (= '(((6 5) 4) 3 (2 1)) (deep-reverse '((1 2) 3 (4 (5 6)))))))

; Call the unit tests!
(run-tests)
