(ns advent-of-code-2021.day-9
  (:require
   [advent-of-code-2021.utils :refer [parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-9-input.txt"))))

(defn- parse-grid [input]
  (let [parse-digit (comp parse-long str)]
    (->> (string/split input #"\n")
         (map (fn [s] (map parse-digit s)))
         (map vec)
         (vec))))

(defn- index-grid [grid]
  (into {}
        (for [y (range (count grid))
              x (range (count (first grid)))]
          [[x y] (get-in grid [y x])])))

(defn- parse-input [input]
  (index-grid (parse-grid input)))

(defn- neighbour-values [grid [x y]]
  (let [deltas [[0 -1] [1 0] [0 1] [-1 0]]]
    (filter (complement nil?)
            (for [[dx dy] deltas] (get grid [(+ x dx) (+ y dy)])))))

(defn- low-point? [grid [x y :as loc]]
  (let [value (get grid loc)]
    (not-any? (partial >= value) (neighbour-values grid loc))))

(defn- risk-level [grid loc]
  (inc (get grid loc)))

(defn solution-part-one [input]
  (let [grid (parse-input input)]
    (->> (keys grid)
         (filter (partial low-point? grid))
         (map (partial risk-level grid))
         (apply +))))


