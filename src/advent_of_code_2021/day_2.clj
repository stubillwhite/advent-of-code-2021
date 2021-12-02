(ns advent-of-code-2021.day-2
  (:require [advent-of-code-2021.utils :refer [parse-long]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-2-input.txt"))))

(defn- parse-line [s]
  (let [[dir dist] (string/split s #" ")]
    {:dir  (keyword dir)
     :dist (parse-long dist)}))

(defn- parse-input [input]
  (->> input
       (string/split-lines)
       (map parse-line)
       (into [])))

(defn- create-state []
  {:position 0
   :depth    0
   :aim      0})

(defn- update-position [{:keys [position depth] :as state} {:keys [dir dist] :as action}]
  (case (:dir action)
    :forward (assoc state :position (+ position dist))
    :down    (assoc state :depth    (+ depth dist))
    :up      (assoc state :depth    (- depth dist))))

(defn- plot-course [actions]
  (reduce update-position (create-state) actions))

(defn- product-of-final-position [{:keys [position depth]}]
  (* position depth))

(defn solution-part-one [input]
  (->> (parse-input input)
       (plot-course)
       (product-of-final-position)))

;; Part two

(defn- update-position-and-aim [{:keys [position depth aim] :as state} {:keys [dir dist] :as action}]
  (case (:dir action)
    :forward (assoc state :position (+ position dist) :depth (+ depth (* aim dist)))
    :down    (assoc state :aim      (+ aim dist))
    :up      (assoc state :aim      (- aim dist))))

(defn- plot-course-considering-aim [actions]
  (reduce update-position-and-aim (create-state) actions))

(defn solution-part-two [input]
  (->> (parse-input input)
       (plot-course-considering-aim)
       (product-of-final-position)))
