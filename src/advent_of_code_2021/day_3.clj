(ns advent-of-code-2021.day-3
  (:require [advent-of-code-2021.utils :refer [parse-long]]
            [clojure.java.io :as io]
            [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-3-input.txt"))))

(defn- parse-input [input]
     (string/split input #"\n"))

(defn- accumulate-bit-counts [acc s]
  (let [indexed-str (interleave (range) (seq s))
        inc-counter (fnil inc 0)]
    (reduce
     (fn [acc [idx bit]] (update-in acc [idx bit] inc-counter))
     acc
     (partition 2 indexed-str))))

(defn- parse-binary [s]
  (Long/parseLong s 2))

(defn- gamma-rate [bit-counts]
  (let [most-common-bit (fn [m] (key (apply max-key val m)))]
    (->> bit-counts
         (map most-common-bit)
         (string/join)
         (parse-binary))))

(defn- epsilon-rate [bit-counts]
  (let [least-common-bit (fn [m] (key (apply min-key val m)))]
    (->> bit-counts
         (map least-common-bit)
         (string/join)
         (parse-binary))))

(defn solution-part-one [input]
  (let [bit-counts (reduce accumulate-bit-counts [] (parse-input input))]
    (* (gamma-rate bit-counts) (epsilon-rate bit-counts))))



