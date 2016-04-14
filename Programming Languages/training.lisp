; /******* Functions *******/

(defun get-last (lst)
  "Returns the last element of a list."
  (cond ((null lst) nil)
    ((null (rest lst)) (first lst))
    ((get-last (rest lst)))))


(defun get-last-two (lst)
  "Returns a list with the last two elements of the given list."
  (cond ((null lst) nil)
    ((<= (length lst) 2) lst)
    ((get-last-two (rest lst)))))


(defun get-kth-element (lst index)
  "Returns the element at the specified index (min. 1) in the
  given list or nil if it's out of bounds."
  (if (= 1 index) (first lst)
    (get-kth-element (rest lst) (decf index 1))))


(defun get-list-size (lst)
  "Returns the number of elements contained in the given list."
  (if (null lst) 0
    (+ 1 (get-list-size (rest lst)))))


(defun append-to-tail (value lst)
  "Returns a new list with the value added to the end of the
  original list."
  (if (null lst) (cons value lst)
    (cons (first lst) (append-to-tail value (rest lst)))))


(defun get-reversed (lst)
  "Returns the given list in reverse order."
  (if (null lst) ()
    (append-to-tail (first lst) (get-reversed (rest lst)))))


(defun is-palindrome (lst)
  "Returns true whether the given list's elements form a palindrome
  or false otherwise."
  (if (null lst) 't
    (let ((left (car lst)) (right (car (last lst))))
      (if (equal left right)
        (is-palindrome (butlast (cdr lst)))
        nil))))


(defun gcd2 (x y)
  "Takes two positive integer arguments greater than zero. Returns
  the Greatest Common Divisor (gcd) of the two numbers."
  (let ((smaller (if (>= x y) y x)))
    (let ((pos-mults (reverse (loop for i from 1 to smaller
                                collect i))))
    
      (loop for j in pos-mults
        when (and (eql 0 (mod x j)) (eql 0 (mod y j)))
        return j))))

; /******* Testing *******/

(print (gcd2 13 7919))
(print (gcd2 20 16))
(print (gcd2 54 24))
(print (gcd2 6307 1995))
(print (gcd2 48 180))
(print (gcd2 42 56))

; (format t "Get-Last Function")
; (print (get-last ()))
; (print (get-last (list 'x)))
; (print (get-last (list 1 2 3 4 5)))

; (format t "~%~%Get-Last-Two Function")
; (print (get-last-two ()))
; (print (get-last-two (list 's)))
; (print (get-last-two (list 1 2 3 4 5 6 7 8 9)))

; (format t "~%~%Get-Kth-Element Function")
; (print (get-kth-element () 2))
; (print (get-kth-element (list 1 2 3 4 5 6 7 8 9) 5))
; (print (get-kth-element (list 'a 'b 'c 'd 'e 'f 'g 'h) 3))
; (print (get-kth-element (list 1 2 3 4 5 6 7 8 9) 12))

; (format t "~%~%Get-List-Size Function")
; (print (get-list-size ()))
; (print (get-list-size (list 1 2 3)))
; (print (get-list-size (list 'a 'b 'c 'd 'e 'f 'g 'h)))

; (format t "~%~%Get-Reversed Function")
; (print (get-reversed ()))
; (print (get-reversed (list 1 2 3 4 5 6 7 8 9)))
; (print (get-reversed (list 'a 'b 'c 'd 'e 'f 'g 'h)))

; (format t "~%~%Is-Palindrome Function")
; (print (is-palindrome ()))
; (print (is-palindrome (list 1 2 3 4 5)))
; (print (is-palindrome (list 'a 'b 'c 'b 'a)))
; (print (is-palindrome (list 'true 'false)))
