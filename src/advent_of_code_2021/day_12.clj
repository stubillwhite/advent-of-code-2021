(ns advent-of-code-2021.day-12
  (:require
   [advent-of-code-2021.utils :refer [parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]
   [clojure.set :as set]))

(def problem-input
  (string/trim (slurp (io/resource "day-12-input.txt"))))

(defn- parse-entry [s]
  (let [[_ a b] (re-matches #"([^-]+)-(.+)" s)]
    [a b]))

(defn- parse-input [input]
  (let [entries        (map parse-entry (string/split input #"\n"))
        add-connection (fn [xs x] (if xs (cons x xs) [x]))]
    (reduce (fn [acc [k v]]
              (-> acc
                  (update k add-connection v)
                  (update v add-connection k)))
            {}
            entries)))

(defn- small? [s]
  (= s (string/lower-case s)))

(defn- add-visit [xs x]
  (if (small? x)
    (conj xs x)
    xs))

(defn- distinct-paths [graph start complete?]
  (letfn [(step [path visited]
            (if (complete? path)
              [path]
              (let [next-nodes (->> (get graph (last path) [])
                                    (remove visited))]
                (mapcat (fn [x] (step (conj path x) (add-visit visited x))) next-nodes))))]
    
    (step [start] #{start})))

(defn- total-distinct-paths [graph]
  (let [at-end? (fn [path] (= (peek path) "end"))]
    (count (distinct-paths graph "start" at-end?))))

(defn solution-part-one [input]
  (total-distinct-paths (parse-input input)))

