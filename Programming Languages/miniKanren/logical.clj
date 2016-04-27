(use 'clojure.core.logic)

(defn my-last
  [lst]
  (cond (empty? (rest lst)) (first lst)
    (not (empty? (rest lst))) (my-last (rest lst))))

(defn lasto
  [lst result]
  (fresh [h t] (conde
    [(conso result t lst) (== t [])]
    [(conso h t lst) (!= t []) (lasto t result)])))

(defn my-reverse
  [lst]
  (cond (empty? lst) '()
    (not (empty? lst)) (append
      (my-reverse (rest lst)) [(first lst)])))

(defn reverseo
  [lst result]
  (fresh [h t x] (conde
    [(== lst []) (== result [])]
    [(!= lst []) (conso h t lst)
      (reverseo t x) (appendo x [h] result)])))
