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

(defn- neighbour-locations [grid [x y]]
  (let [deltas [[0 -1] [1 0] [0 1] [-1 0]]]
    (filter (partial contains? grid)
            (for [[dx dy] deltas] [(+ x dx) (+ y dy)]))))

(defn- low-point? [grid [x y :as loc]]
  (let [value            (get grid loc)
        neighbour-values (map grid (neighbour-locations grid loc))]
    (not-any? (partial >= value) neighbour-values)))

(defn- risk-level [grid loc]
  (inc (get grid loc)))

(defn solution-part-one [input]
  (let [grid (parse-input input)]
    (->> (keys grid)
         (filter (partial low-point? grid))
         (map (partial risk-level grid))
         (apply +))))

;; Part two

(defn- neighbour-locations [grid [x y]]
  (let [deltas [[0 -1] [1 0] [0 1] [-1 0]]]
    (filter (partial contains? grid)
            (for [[dx dy] deltas] [(+ x dx) (+ y dy)]))))

(defn- find-basin-around-location [filtered-grid loc]
  (loop [to-check #{loc}
         basin    #{loc}]
    (if (empty? to-check)
      basin
      (let [new-neighbours (set (mapcat (partial neighbour-locations filtered-grid) to-check))]
        (recur (apply disj new-neighbours basin)
               (apply conj basin new-neighbours))))))

(defn- find-basins [grid]
  (let [filtered-grid (into {} (for [[k v] grid :when (< v 9)] [k v]))]
    (loop [locs   (set (keys filtered-grid))
           basins #{}]
      (if (empty? locs)
        basins
        (let [basin (find-basin-around-location filtered-grid (first locs))]
          (recur (apply disj locs basin) (conj basins basin)))))))

(defn solution-part-two [input]
  (let [grid (parse-input input)]
    (->> (find-basins grid)
         (map count)
         (sort)
         (take-last 3)
         (apply *))))

