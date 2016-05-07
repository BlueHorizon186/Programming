;==========================================================
; Name: Ivan David Diaz Sanchez
; ID: A01371166
;
; Run your program like this:
;
;     lein exec -p equalo.clj
;
;==========================================================

(use '[clojure.core.logic :rename {is logic-is}])
(use 'clojure.test)

;==========================================================

(defne equalo-helper
  [coll]
  ([[]] succeed)
  ([[x]] succeed)
  ([[h . t]] (fresh [y]
    (firsto t y)
    (== h y) (equalo-helper t)
    (!= h y) fail)))

(defn equalo
  "This logical function succeeds if all the elements
  contained in lst unify to the same value, otherwise
  fails."
  [lst]
  (equalo-helper lst))

  ; ([]) succeed
  ; ([[x]]) succeed
  ; ([[h . t]]
  ;   (condo
  ;     (== h (firsto t)) (equalo (resto t))
  ;     (!= h (firsto t)) fail)))

;==========================================================
(deftest test-equalo
  (is (= [:yes]
         (run 1 [q] (equalo []) (== q :yes))))
  (is (= [:yes]
         (run 1 [q] (equalo [:x]) (== q :yes))))
  (is (= [:yes]
         (run 1 [q] (equalo [:x :x]) (== q :yes)))))
  (is (= [:yes]
         (run 1 [q] (equalo [:x :x :x :x :x]) (== q :yes))))
  (is (= [:x]
         (run 1 [q] (equalo [:x :x q :x]))))
  ; (is (= '[_0]
  ;        (run 1 [q] (equalo [q q q q q q]))))
  ; (is (= '([_0 _0 _0 _0 _0])
  ;        (run 1 [q1 q2 q3 q4 q5] (equalo [q1 q2 q3 q4 q5]))))
  ; (is (= []
  ;        (run 1 [q] (equalo [:x :y]) (== q :yes))))
  ; (is (= []
  ;        (run 1 [q1 q2] (equalo [q1 q1 q2 q1 q1]) (!= q1 q2))))
  ; (is (= '([]
  ;          [_0]
  ;          [_0 _0]
  ;          [_0 _0 _0]
  ;          [_0 _0 _0 _0]
  ;          [_0 _0 _0 _0 _0]
  ;          [_0 _0 _0 _0 _0 _0])
  ;        (run 7 [q] (equalo q)))))

;==========================================================
(run-tests)