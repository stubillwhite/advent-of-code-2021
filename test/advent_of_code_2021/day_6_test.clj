(ns advent-of-code-2021.day-6-test
  (:require [advent-of-code-2021.day-6 :refer :all]
            [advent-of-code-2021.utils :refer [def-]]
            [clojure.string :as string]
            [clojure.test :refer :all]))

(def example-input "3,4,3,1,2")

(deftest parse-input-then-groups-by-days-to-reproduce
  (is (= [0 1 1 2 1 0 0 0 0] (parse-input example-input))))

(deftest simulation-by-day-given-example-input-then-example-output
  (let [days (simulation-by-day (parse-input example-input))]
    (is (= (parse-input "3,4,3,1,2")                                           (nth days  0)))
    (is (= (parse-input "2,3,2,0,1")                                           (nth days  1)))
    (is (= (parse-input "1,2,1,6,0,8")                                         (nth days  2)))
    (is (= (parse-input "0,1,0,5,6,7,8")                                       (nth days  3)))
    (is (= (parse-input "6,0,6,4,5,6,7,8,8")                                   (nth days  4)))
    (is (= (parse-input "5,6,5,3,4,5,6,7,7,8")                                 (nth days  5)))
    (is (= (parse-input "4,5,4,2,3,4,5,6,6,7")                                 (nth days  6)))
    (is (= (parse-input "3,4,3,1,2,3,4,5,5,6")                                 (nth days  7)))
    (is (= (parse-input "2,3,2,0,1,2,3,4,4,5")                                 (nth days  8)))
    (is (= (parse-input "1,2,1,6,0,1,2,3,3,4,8")                               (nth days  9)))
    (is (= (parse-input "0,1,0,5,6,0,1,2,2,3,7,8")                             (nth days 10)))
    (is (= (parse-input "6,0,6,4,5,6,0,1,1,2,6,7,8,8,8")                       (nth days 11)))
    (is (= (parse-input "5,6,5,3,4,5,6,0,0,1,5,6,7,7,7,8,8")                   (nth days 12)))
    (is (= (parse-input "4,5,4,2,3,4,5,6,6,0,4,5,6,6,6,7,7,8,8")               (nth days 13)))
    (is (= (parse-input "3,4,3,1,2,3,4,5,5,6,3,4,5,5,5,6,6,7,7,8")             (nth days 14)))
    (is (= (parse-input "2,3,2,0,1,2,3,4,4,5,2,3,4,4,4,5,5,6,6,7")             (nth days 15)))
    (is (= (parse-input "1,2,1,6,0,1,2,3,3,4,1,2,3,3,3,4,4,5,5,6,8")           (nth days 16)))
    (is (= (parse-input "0,1,0,5,6,0,1,2,2,3,0,1,2,2,2,3,3,4,4,5,7,8")         (nth days 17)))
    (is (= (parse-input "6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8") (nth days 18)))))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 5934 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 383160 (solution-part-one problem-input))))

