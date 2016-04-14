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

(println (inc (debug (* 4 5))))
