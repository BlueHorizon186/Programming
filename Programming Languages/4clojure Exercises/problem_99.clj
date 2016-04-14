;----------------------------------------------------------
; Problem 99: Product Digits
; Date: March 17, 2016.
; Authors:
;          A01371166 Ivan David Diaz Sanchez
;----------------------------------------------------------

(use 'clojure.test)

(def product-digits
  (fn [x y] (let [product (* x y)]
    (reverse (map #(mod % 10)
      (take-while pos? (iterate #(quot % 10) product)))))))

(deftest test-product-digits
  (is (= (product-digits 1 1) [1]))
  (is (= (product-digits 99 9) [8 9 1]))
  (is (= (product-digits 999 99) [9 8 9 0 1])))

(run-tests)
