;;; ITESM CEM, April 14, 2016.
;;; Clojure Source File
;;; Activity: Macros
;;; Author: 
;;;          A01371166 Ivan David Diaz Sanchez

(use 'clojure.test)

; ==========================================================

(defmacro my-or
  "Evaluates its expressions one at a time, from left to right.
  If a form returns a logical true value, it returns that value
  and doesn't evaluate any of the other expressions, otherwise it
  returns the value of the last expression. If it receives no
  arguments, then it returns nil."
  ([] nil)
  ([x] x)
  ([x & others]
    `(let [expr# ~x]
       (if expr# expr# (my-or ~@others)))))

; ==========================================================

(defn parse-conditional
  "Given a loop expressions, returns the conditional keyword that
  denotes the loop's behaviour."
  [lst]
  (let [exprs (take-while
                #(and (not= :while (first %)) (not= :until (first %)))
                lst)
        final (flatten (drop-while
                #(and (not= :while (first %)) (not= :until (first %)))
                lst))]
    (list exprs (first final) (rest final))))

(defmacro do-loop
  "Implements a post-test loop control statement. Receives one
  or more expressions as arguments and a final conditional form
  prefaced with a :while or :until keyword. First, the expressions
  in the body are evaluated sequentially, and then the condition
  is evaluated. If the final form uses a :while keyword, the body
  of the loop is repeated while the condition holds true. On the
  other hand, if the final form uses an :until keyword, the body
  of the loop is repeated while the condition holds false.
  Returns nil."
  [& l-exprs]
  (let [loop-comp (parse-conditional l-exprs)
        exprs (first loop-comp)
        cond-key (second loop-comp)
        cond-expr (last loop-comp)]
    `(loop [c# ~cond-expr]
       (println c#)
       (cond
         (= :until ~cond-key)
           (if c# nil
             (do ~@exprs (recur ~cond-expr)))
         (= :while ~cond-key)
           (if c# (do ~@exprs (recur ~cond-expr))
             nil)))))


; ==========================================================

(defmacro def-pred
  "Takes a name, an arg vector, and a body of one or more expressions.
  Then, it defines two predicate functions: a regular one and its
  negated version. The latter's name is the same as the given one,
  but with a \"not-\" prefix."
  [p-name args & body] nil)

; ==========================================================

(defmacro defn-curry
  "Performs a currying transformation to a function definition.
  It takes as parameters a name, an args vector, and a body of
  one or more expressions. It defines a function called name that
  takes only the first argument from args and returns a function
  that takes the second argument from args, and so on. The last
  function returned takes the last argument from args and evaluates
  all the expressions in the body."
  [c-name args & body] nil)

; ==========================================================

; Tests

(deftest test-my-or
  (is (= nil (my-or)))
  (is (= :one (my-or false :one nil :two false :three)))
  (is (= nil (my-or false false nil)))
  (is (= false (my-or nil nil false))))

; (run-tests)

(def i (atom 0))
(do-loop
  (println @i)
  (swap! i inc)
  (:until (= @i 5)))
