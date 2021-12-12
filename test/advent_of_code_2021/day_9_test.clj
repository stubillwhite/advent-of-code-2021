(ns advent-of-code-2021.day-9-test
  (:require
   [advent-of-code-2021.day-9 :refer :all]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(def example-input
  (string/join "\n" ["2199943210"
                     "3987894921"
                     "9856789892"
                     "8767896789"
                     "9899965678"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 15 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 462 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 1134 (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 1397760 (solution-part-two problem-input))))
