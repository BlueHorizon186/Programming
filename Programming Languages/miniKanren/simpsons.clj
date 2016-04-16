(use 'clojure.core.logic)
(use 'clojure.core.logic.pldb)

; Example: Database of Facts

(db-rel fathero f ch)
(db-rel mothero m ch)

(def simpsons
  (db
    [fathero :homer :bart]
    [fathero :homer :lisa]
    [fathero :homer :maggie]

    [mothero :marge :bart]
    [mothero :marge :lisa]
    [mothero :marge :maggie]))
