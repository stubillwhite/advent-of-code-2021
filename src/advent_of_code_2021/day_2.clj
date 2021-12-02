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
   :depth    0})

(defn- update-state [{:keys [position depth] :as state} {:keys [dir dist] :as action}]
  (case (:dir action)
        :forward (assoc state :position (+ position dist))
        :down    (assoc state :depth    (+ depth dist))
        :up      (assoc state :depth    (- depth dist))))

(defn- execute-actions [actions]
  (reduce update-state (create-state) actions))

(defn- product-of-final-position [{:keys [position depth]}]
  (* position depth))

(defn solution-part-one [input]
  (->> (parse-input input)
       (execute-actions)
       (product-of-final-position)))

;; Part two
