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

(defn- direction-of-depth-change [measurements]
  (let [to-direction (fn [[prev curr]] (cond
                                        (< prev curr) :increased
                                        (= prev curr) :constant
                                        (> prev curr) :decreased))]
    (->> measurements
         (partition 2 1)
         (map to-direction))))

(defn- total-times-measure-increased [measurements]
  (->> measurements
       (direction-of-depth-change)
       (filter (fn [x] (= :increased x)))
       (count)))

(defn solution-part-one [input]
  (->> (parse-input input)
       (total-times-measure-increased)))

;; Part two

(defn- sliding-sum [measurements]
  (->> measurements
       (partition 3 1)
       (map (partial apply +))))

(defn solution-part-two [input]
  (->> (parse-input input)
       (sliding-sum)
       (total-times-measure-increased)))
