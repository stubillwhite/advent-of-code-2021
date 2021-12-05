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

(defn- mark-number [colls x]
  (map (fn [coll] (remove (partial = x) coll)) colls))

(defn- mark-board [x board]
  (-> board
      (update :unmarked-rows mark-number x)
      (update :unmarked-cols mark-number x)))

(defn- winner? [{:keys [unmarked-rows unmarked-cols]}]
  (if (or (some #{[]} unmarked-rows) (some #{[]} unmarked-cols))
    true
    false))

(defn- score-winner [board last-called]
  (* last-called (apply + (apply concat (:unmarked-rows board)))))

(defn- calculate-winning-scores [boards numbers]
  (letfn [(call-number [{:keys [boards scores]} number]
            (let [new-boards                         (map (fn [board] (mark-board number board)) boards)
                  {winners true still-playing false} (group-by winner? new-boards)]
              {:boards still-playing
               :scores (concat scores (for [board winners] (score-winner board number)))}))]
    (:scores (reduce call-number {:boards boards :scores []} numbers))))

(defn solution-part-one [input]
  (let [{:keys [boards numbers]} (parse-input input)]
    (first (calculate-winning-scores boards numbers))))

;; Part two

(defn solution-part-two [input]
  (let [{:keys [boards numbers]} (parse-input input)]
    (last (calculate-winning-scores boards numbers))))
