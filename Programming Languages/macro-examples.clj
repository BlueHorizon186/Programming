;;; Macro-Examples: How to implement macros in Clojure

(defmacro my-and
  "Evaluates all its arguments one at a time, from left to
  right. If a form returns logical false (nil or false),
  my-and returns that value and doesn't evaluate any of the
  other expressions, otherwise it returns the value of the
  last expr. (my-and) returns true."
  ([] true)
  ([x] x)
  ([x & y]
   `(let [temp# ~x]
      (if temp#
          (my-and ~@y)
          temp#))))

(defmacro debug
  "Provides a simple debugging facility for Clojure code.
  When the expression is evaluates, a debugging line is
  printed to the standard output."
  [expr]
  `(let [result# ~expr]
     (printf "debug: %s => %s%n" (quote ~expr) result#)
     result#))

(defn keep-between
  "Returns a new list with all the elements between start
  and end from the given lst."
  [start end lst]
  (take-while #(not= end %)
    (rest (drop-while #(not= start %) lst))))

(defmacro IF
  "Provides a conditional statement that is syntactically a
  bit more similar to those found in languages like Pascal
  or Fortran. It has the following form:
  (IF condition :THEN exp1 exp2 … :ELSE exp3 exp4 …)
  Everything is optional, except the condition. Also, the
  :ELSE keyword may come before the :THEN keyword."
  [condition & all]
  (let [then-part (keep-between :THEN :ELSE all)
        else-part (keep-between :ELSE :THEN all)]
    `(if ~condition
       (do ~@then-part)
       (do ~@else-part))))

(println (inc (debug (* 4 5))))
(println (IF (> 3 1) :THEN 'ok :ELSE 'oops))

(println
  (macroexpand-1 '(IF (> 3 1) 
                    :ELSE (println "Else section.") 'oops 
                    :THEN (println "Then section.") 'ok)))
