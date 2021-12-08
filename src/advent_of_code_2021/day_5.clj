(ns advent-of-code-2021.day-5
  (:require
   [advent-of-code-2021.utils :refer [any-pred parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-5-input.txt"))))

(defn- parse-line [s]
  (let [[_ x1 y1 x2 y2] (re-matches #"(\d+),(\d+) -> (\d+),(\d+)" s)]
    (->> [x1 y1 x2 y2]
         (map parse-long)
         (vec))))

(defn- parse-input [input]
  (map parse-line (string/split input #"\n")))

(defn- horizontal? [[x1 y1 x2 y2]]
  (= y1 y2))

(defn- vertical? [[x1 y1 x2 y2]]
  (= x1 x2))

(defn- abs [x]
  (Math/abs x))

(defn- to-points [[x1 y1 x2 y2]]
  (let [[x-len y-len]   [(- x2 x1) (- y2 y1)]
        total-length    (max (abs x-len) (abs y-len))
        [x-step y-step] [(/ x-len total-length) (/ y-len total-length)]]
    (for [i (range (inc total-length))]
      [(+ x1 (* i x-step)) (+ y1 (* i y-step))])))

(defn- mark-points [points]
  (reduce (fn [acc point] (update acc point (fnil inc 0))) {} points))

(defn- count-overlaps [points]
  (let [overlap? (fn [[k v]] (>= v 2))]
    (count (filter overlap? points))))

(defn solution-part-one [input]
  (->> (parse-input input)
       (filter (any-pred horizontal? vertical?))
       (mapcat to-points)
       (mark-points)
       (count-overlaps)))

;; Part two

(defn solution-part-two [input]
  (->> (parse-input input)
       (mapcat to-points)
       (mark-points)
       (count-overlaps)))
