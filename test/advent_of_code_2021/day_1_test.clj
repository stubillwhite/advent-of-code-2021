(ns advent-of-code-2021.day-1-test
  (:require [advent-of-code-2021.day-1 :refer :all]
            [advent-of-code-2021.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.test :refer :all]))

(def- example-input
  (string/join "\n" [199 200 208 210 200 207 240 269 260 263]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 7 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 1292 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 5 (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 1262 (solution-part-two problem-input))))
