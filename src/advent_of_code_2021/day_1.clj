(ns advent-of-code-2021.day-1
  (:require [advent-of-code-2021.utils :refer [parse-long]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-1-input.txt"))))

(defn- parse-input [input]
  (->> input
       (string/split-lines)
       (map parse-long)
       (into [])))

(defn- depth-change [[a b]]
  (cond
    (< a b) :increased
    (= a b) :constant
    (> a b) :decreased))

(defn solution-part-one [input]
  (->> (parse-input input)
       (partition 2 1)
       (map depth-change)
       (filter (fn [x] (= :increased x)))
       (count)))







