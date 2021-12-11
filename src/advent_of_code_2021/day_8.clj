(ns advent-of-code-2021.day-8
  (:require
   [advent-of-code-2021.utils :refer [def- parse-long]]
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

(def- segment-mapping
  {0 "abcefg"
   1 "cf"
   2 "acdeg"
   3 "acdfg"
   4 "bcdf"
   5 "abdfg"
   6 "abdefg"
   7 "acf"
   8 "abcdefg"
   9 "abcdfg"})

(defn- digit-to-segment-count [digit]
  (count (get segment-mapping digit)))

(defn solution-part-one [input]
  (let [unique-digits   [1 4 7 8]
        unique-segments (into #{} (map digit-to-segment-count unique-digits))]
    (->> (parse-input input)
         (mapcat :output)
         (filter (fn [x] (contains? unique-segments (count x))))
         (count))))

;; Part two

(defn- to-binary-using-mapping [mapping s]
  (reduce (fn [acc ch]
            (let [bit-number (get mapping ch)]
              (bit-or acc (bit-shift-left 1 bit-number))))
          0
          s))

(def- binary-to-digit-lookup
  (into {} (for [[k v] segment-mapping] [(to-binary-using-mapping (zipmap "abcdefg" (range)) v) k])))

(defn- valid-mapping? [m values]
  (let [valid-binary-values  (keys binary-to-digit-lookup)
        mapped-binary-values (map (partial to-binary-using-mapping m) values)]
    (= (sort valid-binary-values)
       (sort mapped-binary-values))))

(defn- permutations-of-set [items]
  (if (= (count items) 1)
    [items]
    (mapcat (fn [x] (map (partial cons x) (permutations-of-set (disj items x))))
            items)))

(def- all-signal-permutations
  (permutations-of-set (set (range 7))))

(defn- brute-force-mapping [signals]
  (loop [permutations all-signal-permutations]
    (let [mapping (zipmap "abcdefg" (first permutations))]
      (if (valid-mapping? mapping signals)
        mapping
        (recur (rest permutations))))))

(defn- brute-force-output-value [{:keys [signals output]}]
  (let [mapping (brute-force-mapping signals)]
    (->> output
         (map (partial to-binary-using-mapping mapping))
         (map binary-to-digit-lookup)
         (string/join)
         (parse-long))))

(defn solution-part-two [input]
  (->> (parse-input input)
       (map brute-force-output-value)
       (apply +)))
