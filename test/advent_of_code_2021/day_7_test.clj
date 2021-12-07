(ns advent-of-code-2021.day-7-test
  (:require [advent-of-code-2021.day-7 :refer :all]
            [advent-of-code-2021.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.test :refer :all]))

(def example-input "16,1,2,0,4,2,7,1,2,14")

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 37 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 344297 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 168 (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 97164301 (solution-part-two problem-input))))


