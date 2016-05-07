;==========================================================
; Type your name and student ID here.

; Run your program like this:
;
;     lein exec -p counto.clj
;
;==========================================================

(use '[clojure.core.logic :rename {is logic-is}])
(require '[clojure.core.logic.fd :as fd])
(use 'clojure.test)

;==========================================================

(defne counto-helper
  [coll res]
  ([[] 0])
  ([[y] 1])
  ([[h . t] res] (fresh [x]
    (counto-helper t x)
    (fd/+ x 1 res))))

(defn counto
  "This logical function unifies result with the number
  of elements contained in lst."
  [lst result]
  (counto-helper lst result))

;==========================================================
(deftest test-counto
  (is (= [0]
         (run 1 [q]
           (fd/in q (fd/interval 0 10))
           (counto [] q))))
  (is (= [1]
         (run 1 [q]
           (fd/in q (fd/interval 0 10))
           (counto [:a] q))))
  (is (= [2]
         (run 1 [q]
           (fd/in q (fd/interval 0 10))
           (counto [:a :b] q))))
  (is (= [3]
         (run 1 [q]
           (fd/in q (fd/interval 0 10))
           (counto [:a :b :c] q))))
  (is (= [10]
         (run 1 [q]
           (fd/in q (fd/interval 0 10))
           (counto (repeat 10 :x) q))))
  (is (= '([_0])
         (run 1 [q]
           (fd/in q (fd/interval 0 10))
           (counto q 1))))
  (is (= '([_0 _1 _2 _3 _4])
         (run 1 [q]
           (fd/in q (fd/interval 0 10))
           (counto q 5))))
  (is (= '([[] 0]
           [(_0) 1]
           [(_0 _1) 2]
           [(_0 _1 _2) 3]
           [(_0 _1 _2 _3) 4]
           [(_0 _1 _2 _3 _4) 5]
           [(_0 _1 _2 _3 _4 _5) 6])
         (run 7 [q1 q2]
           (fd/in q1 q2 (fd/interval 0 10))
           (counto q1 q2)))))

;==========================================================
(run-tests)