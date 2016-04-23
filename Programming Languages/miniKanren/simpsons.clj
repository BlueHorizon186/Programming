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
    [mothero :marge :maggie]

    [mothero :jackeline :marge]
    [mothero :jackeline :patty]
    [mothero :jackeline :selma]
    [mothero :selma :ling]

    [fathero :clancy :marge]
    [fathero :clancy :patty]
    [fathero :clancy :selma]

    [fathero :abraham :homer]
    [mothero :mona :homer]))

(defn grand-mothero
  [gm gch]
  (fresh [p] (conde
      [(mothero gm p) (mothero p gch)]
      [(mothero gm p) (fathero p gch)])))

(defn grand-fathero
  [gf gch]
  (fresh [p] (conde
      [(fathero gf p) (mothero p gch)]
      [(fathero gf p) (fathero p gch)])))

(defn marriedo
  [h w]
  (fresh [ch] (fathero h ch) (mothero w ch)))

(defn siblingo
  [p1 p2]
  (fresh [prnt]
    (!= p1 p2)
    (conda
      [(fathero prnt p1) (fathero prnt p2)]
      [(mothero prnt p1) (mothero prnt p2)])))

; Run* Example
; (with-db simpsons (run* [q] (mothero q :lisa)))
; (with-db simpsons (run* [q] (mothero :marge q)))

; New Version
; (run-db* simpsons [q] (mothero q :lisa))
; (run-db* simpsons [q] (mothero :marge q))
