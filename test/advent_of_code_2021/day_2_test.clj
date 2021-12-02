(ns advent-of-code-2021.day-2-test
  (:require [advent-of-code-2021.day-2 :refer :all]
            [advent-of-code-2021.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.test :refer :all]))

(def- example-input
  (string/join "\n" ["forward 5"
                     "down 5"
                     "forward 8"
                     "up 3"
                     "down 8"
                     "forward 2"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 150 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 1250395 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 900 (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 1451210346 (solution-part-two problem-input))))


