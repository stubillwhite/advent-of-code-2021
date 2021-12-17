(ns advent-of-code-2021.day-11-test
  (:require
   [advent-of-code-2021.day-11 :refer :all]
   [advent-of-code-2021.utils :refer [def-]]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(defn- join-rows [rows]
  (string/join "\n" rows))

(def- example-input
  (join-rows ["5483143223"
              "2745854711"
              "5264556173"
              "6141336146"
              "6357385478"
              "4167524645"
              "2176841721"
              "6882881134"
              "4846848554"
              "5283751526"]))

(def- basic-input
  (join-rows ["11111"
              "19991"
              "19191"
              "19991"
              "11111"]))

(def- basic-result-after-1
  (join-rows ["34543"
              "40004"
              "50005"
              "40004"
              "34543"]))

(def- basic-result-after-2
  (join-rows ["45654"
              "51115"
              "61116"
              "51115"
              "45654"]))

(def- example-result-after-1
  (join-rows ["6594254334"
              "3856965822"
              "6375667284"
              "7252447257"
              "7468496589"
              "5278635756"
              "3287952832"
              "7993992245"
              "5957959665"
              "6394862637"]))

(def- example-result-after-2
  (join-rows ["8807476555"
              "5089087054"
              "8597889608"
              "8485769600"
              "8700908800"
              "6600088989"
              "6800005943"
              "0000007456"
              "9000000876"
              "8700006848"]))

(defn- visualise-grid [grid]
  (let [size   (inc (apply max (map first (keys grid))))
        values (for [y (range size) x (range size)] (grid [x y]))]
    (->> values
         (map (fn [x] (if (> x 9) "X" x)))
         (partition size)
         (map (partial apply str))
         (string/join "\n"))))

(defn- nth-as-str [states n]
  (visualise-grid (nth states n)))

(deftest simulation-states-given-basic-input-then-example-result
  (let [states (simulation-states basic-input)]
    (is (= (nth-as-str states 1) basic-result-after-1))
    (is (= (nth-as-str states 2) basic-result-after-2))))

(deftest simulation-states-given-example-input-then-example-result
  (let [states (simulation-states example-input)]
    (is (= (nth-as-str states 1) example-result-after-1))
    (is (= (nth-as-str states 2) example-result-after-2))))

(deftest total-flashes-after-n-steps-given-example-input-then-example-result
  (is (= 204 (total-flashes-after-n-steps example-input 10))))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 1656 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 1723 (solution-part-one problem-input))))

(deftest solution-part-two-given-example-input-then-example-result
  (is (= 195 (solution-part-two example-input))))

(deftest solution-part-two-given-problem-input-then-correct-result
  (is (= 327 (solution-part-two problem-input))))
