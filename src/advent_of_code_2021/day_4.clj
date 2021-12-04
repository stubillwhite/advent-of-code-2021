(ns advent-of-code-2021.day-4
  (:require
   [advent-of-code-2021.utils :refer [parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-4-input.txt"))))

(defn- parse-numbers [s]
  (map parse-long (re-seq #"\d+" s)))

(defn- parse-board [lines]
  (let [rows (map (fn [s] (map parse-long (re-seq #"\d+" s))) (rest lines))
        cols (map vec (partition 5 (apply interleave rows)))]
    {:unmarked-rows rows
     :unmarked-cols cols}))

(defn- parse-input [input]
  (let [lines   (string/split input #"\n")
        numbers (parse-numbers (first lines))
        boards  (map parse-board (partition 6 (rest lines)))]
    {:numbers numbers
     :boards  boards}))

(defn- find-first [f xs]
  (first (drop-while (complement f) xs)))

(defn- mark-number [colls x]
  (map (fn [coll] (remove (partial = x) coll)) colls))

(defn- mark-board [x board]
  (-> board
      (update :unmarked-rows mark-number x)
      (update :unmarked-cols mark-number x)))

(defn- winner? [{:keys [unmarked-rows unmarked-cols]}]
  (or (some #{[]} unmarked-rows)
      (some #{[]} unmarked-cols)))

(defn- find-winner [boards x]
  (let [new-boards (map (fn [board] (mark-board x board)) boards)]
    (if-let [winner (find-first winner? new-boards)]
      (reduced {:winner winner :last-called x})
      new-boards)))

(defn solution-part-one [input]
  (let [{:keys [numbers boards]}     (parse-input input)
        {:keys [winner last-called]} (reduce find-winner boards numbers)]
    (* last-called (apply + (apply concat (:unmarked-rows winner))))))
