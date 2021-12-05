(ns advent-of-code-2021.day-5-test
  (:require [advent-of-code-2021.day-5 :refer :all]
            [advent-of-code-2021.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.test :refer :all]))

(def example-input
  (string/join "\n"
               ["0,9 -> 5,9"
                "8,0 -> 0,8"
                "9,4 -> 3,4"
                "2,2 -> 2,1"
                "7,0 -> 7,4"
                "6,4 -> 2,0"
                "0,9 -> 2,9"
                "3,4 -> 1,4"
                "0,0 -> 8,8"
                "5,5 -> 8,2"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 5 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 7644 (solution-part-one problem-input))))
