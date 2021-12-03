(ns advent-of-code-2021.day-3-test
  (:require [advent-of-code-2021.day-3 :refer :all]
            [advent-of-code-2021.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.test :refer :all]))

(def example-input
  (string/join "\n" ["00100" "11110" "10110" "10111" "10101" "01111" "00111" "11100" "10000" "11001" "00010" "01010"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 198 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 3958484 (solution-part-one problem-input))))




