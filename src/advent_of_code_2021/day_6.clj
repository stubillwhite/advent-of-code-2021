(ns advent-of-code-2021.day-6
  (:require
   [advent-of-code-2021.utils :refer [parse-long]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-6-input.txt"))))

(defn- group-by-days-to-reproduce [fish]
  (reduce (fn [acc x] (update acc x inc)) (vec (repeat 9 0)) fish))

(defn parse-input [input]
  (->> (string/split input #",")
       (map parse-long)
       (group-by-days-to-reproduce)))

(defn- next-day [grouped-fish]
  (let [[reproducing & non-reproducing] grouped-fish]
    (-> (vec non-reproducing)
        (update 6 (partial + reproducing))
        (assoc 8 reproducing)
        (vec))))

(defn simulation-by-day [grouped-fish]
  (iterate next-day grouped-fish))

(defn solution-part-one [input]
  (let [simulation (simulation-by-day (parse-input input))]
    (reduce + (nth simulation 80))))

;; Part two

(defn solution-part-two [input]
  (let [simulation (simulation-by-day (parse-input input))]
    (reduce + (nth simulation 256))))
