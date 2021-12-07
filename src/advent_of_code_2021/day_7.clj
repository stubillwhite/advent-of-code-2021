(ns advent-of-code-2021.day-7
  (:require
   [advent-of-code-2021.utils :refer [any-pred parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-7-input.txt"))))

(defn parse-input [input]
  (->> (string/split input #",")
       (map parse-long)))

(defn- minimise-cost [f data]
  (let [start      (apply min data)
        end        (apply max data)
        total-cost (fn [aligned-pos]
                     (->> data
                          (map (partial f aligned-pos))
                          (reduce +)))]
    (->> (range start (inc end))
         (pmap total-cost)
         (reduce min))))

(defn- abs [x]
  (Math/abs x))

(defn- constant-fuel-per-step [aligned-pos crab-pos]
  (abs (- aligned-pos crab-pos)))

(defn solution-part-one [input]
  (let [positions (parse-input input)]
    (minimise-cost constant-fuel-per-step positions)))

;; Part two

(defn- increasing-fuel-per-step [aligned-pos crab-pos]
  (let [dist (abs (- aligned-pos crab-pos))]
    (/ (* dist (inc dist))
       2)))

(defn solution-part-two [input]
  (let [positions (parse-input input)]
    (minimise-cost increasing-fuel-per-step positions)))
