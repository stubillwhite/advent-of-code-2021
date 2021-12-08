(ns advent-of-code-2021.day-8
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-8-input.txt"))))

(defn- parse-line [line]
  (let [[signals output] (map (fn [s] (string/split s #" ")) (string/split line #" \| "))]
    {:signals signals
     :output  output}))

(defn- parse-input [input]
  (->> (string/split input #"\n")
       (map parse-line)))

(defn- digit-to-segments [n]
  (let [mapping {0 6
                 1 2
                 2 5
                 3 5
                 4 4
                 5 5
                 6 6
                 7 3
                 8 7
                 9 6}]
       (get mapping n)))

(defn solution-part-one [input]
  (let [unique-digits   [1 4 7 8]
        unique-segments (into #{} (map digit-to-segments unique-digits))]
    (->> (parse-input input)
         (mapcat :output)
         (filter (fn [x] (contains? unique-segments (count x))))
         (count))))


