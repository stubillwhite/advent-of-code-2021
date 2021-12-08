(ns advent-of-code-2021.day-3
  (:require
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-3-input.txt"))))

(defn- parse-input [input]
     (string/split input #"\n"))

(defn- map-vals [f m]
  (into {} (for [[k v] m] [k (f v)])))

(defn nth-bit-counts [numbers n]
  (->> numbers
       (map (fn [s] (nth s n)))
       (group-by identity)
       (map-vals count)))

(defn- most-common-or-one [{zeroes \0 ones \1}]
  (if (> zeroes ones) \0 \1))

(defn- least-common-or-zero [{zeroes \0 ones \1}]
  (if (< ones zeroes) \1 \0))

(defn- generate-number-from-criteria [criteria numbers]
  (->> (range (count (first numbers)))
       (map (partial nth-bit-counts numbers))
       (map criteria)
       (string/join)))

(defn- parse-binary [s]
  (Long/parseLong s 2))

(defn- gamma-rate [numbers]
  (parse-binary (generate-number-from-criteria most-common-or-one numbers)))

(defn- epsilon-rate [numbers]
  (parse-binary (generate-number-from-criteria least-common-or-zero numbers)))

(defn solution-part-one [input]
  (let [numbers (parse-input input)]
    (* (gamma-rate numbers) (epsilon-rate numbers))))

;; Part two

(defn- filter-by-criteria [criteria numbers]
  (loop [n       0
         numbers numbers]
    (if (= (count numbers) 1)
      (first numbers)
      (let [desired-bit (criteria (nth-bit-counts numbers n))]
           (recur (inc n)
                  (filter (fn [s] (= (nth s n) desired-bit)) numbers))))))

(defn- oxygen-generator-rating [numbers]
  (parse-binary (filter-by-criteria most-common-or-one numbers)))

(defn- co2-scrubber-rating [numbers]
  (parse-binary (filter-by-criteria least-common-or-zero numbers)))

(defn solution-part-two [input]
  (let [numbers (parse-input input)]
    (* (oxygen-generator-rating numbers) (co2-scrubber-rating numbers))))
