(ns advent-of-code-2021.day-11
  (:require
   [advent-of-code-2021.utils :refer [parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]
   [clojure.set :as set]))

(def problem-input
  (string/trim (slurp (io/resource "day-11-input.txt"))))

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

(defn parse-input [input]
  (index-grid (parse-grid input)))

(defn- neighbour-locations [grid [x y]]
  (let [deltas (for [dx (range -1 2)
                     dy (range -1 2)
                     :when (not (= [dx dy] [0 0]))]
                 [dx dy])]
    (filter (partial contains? grid)
            (for [[dx dy] deltas] [(+ x dx) (+ y dy)]))))

(defn- increase-energy-level
  ([grid loc]
   (update grid loc (fn [x] (if (> x 9) x (inc x))))))

(defn- flashing? [grid loc]
  (> (grid loc) 9))

(defn flashed? [grid loc]
  (zero? (grid loc)))

(defn- total-flashed [grid]
  (count (filter (partial flashed? grid) (keys grid))))

(defn- increase-energy-and-maybe-flash [grid]
  (loop [grid        grid
         to-increase (keys grid)]
    (if (empty? to-increase)
      grid
      (let [new-grid        (reduce increase-energy-level grid to-increase)
            newly-flashing  (->> (keys grid)
                                 (filter (partial (complement flashing?) grid))
                                 (filter (partial flashing? new-grid)))
            new-to-increase (mapcat (partial neighbour-locations new-grid) newly-flashing)]
        (recur new-grid new-to-increase)))))

(defn- exhaust-octopodes [grid]
  (into {} (for [[k v] grid] [k (if (> v 9) 0 v)])))

(defn- next-state [grid]
  (->> grid
       (increase-energy-and-maybe-flash)
       (exhaust-octopodes)))

(defn simulation-states [input]
  (iterate next-state (parse-input input)))

(defn total-flashes-after-n-steps [input n]
  (reduce (fn [acc state] (+ acc (total-flashed state)))
          0
          (take (inc n) (simulation-states input))))

(defn solution-part-one [input]
  (total-flashes-after-n-steps input 100))

;; Part two

(defn- all-flashed? [grid]
  (= (total-flashed grid) (count (keys grid))))

(defn solution-part-two [input]
  (let [states (simulation-states input)]
    (reduce (fn [n state]
              (if (all-flashed? state)
                (reduced n)
                (inc n)))
            0
            states)))
