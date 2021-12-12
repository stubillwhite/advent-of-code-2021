(ns advent-of-code-2021.day-10
  (:require
   [advent-of-code-2021.utils :refer [def-]]
   [clojure.java.io :as io]
   [clojure.string :as string]))

(def problem-input
  (string/trim (slurp (io/resource "day-10-input.txt"))))

(defn- parse-input [input]
  (string/split input #"\n"))

(def- openers "{([<")

(def- closers "})]>")

(defn- closes? [opener closer]
  (and opener closer (= (string/index-of openers opener)
                        (string/index-of closers closer))))

(defn- closing-char? [ch]
  (not (nil? (string/index-of closers ch))))

(defn- parse-chunks [s]
  (let [accumulate-chunks (fn [{:keys [stack chunks chunk] :as state} ch]
                            (if (closing-char? ch)
                              (if (closes? (first stack) ch)
                                (if (empty? (rest stack))
                                  {:stack []           :chunks (cons (str chunk ch) chunks) :chunk ""}
                                  {:stack (rest stack) :chunks chunks                       :chunk (str chunk ch)})
                                (reduced (assoc state :corrupted-chunk (str chunk ch))))
                              {:stack (cons ch stack) :chunks chunks :chunk (str chunk ch)}))]
    (reduce accumulate-chunks
            {:stack [] :chunks [] :chunk ""}
            s)))

(defn- corrupted? [{:keys [corrupted-chunk]}]
  (not (nil? corrupted-chunk)))

(defn- incomplete? [{:keys [stack chunk] :as parsed}]
  (and (not (corrupted? parsed))
       (not (empty? stack))))

(defn- score-syntax-error [s]
  (let [scores {\) 3
                \] 57
                \} 1197
                \> 25137}] 
    (get scores (last s))))

(defn solution-part-one [input]
  (->> (parse-input input)
       (map parse-chunks)
       (filter corrupted?)
       (map :corrupted-chunk)
       (map score-syntax-error)
       (apply +)))

;; Part two

(defn- autocomplete [{:keys [stack]}]
  (let [to-closer (fn [ch] (get closers (string/index-of openers ch)))]
    (->> stack
         (map to-closer)
         (apply str))))

(defn- score-autocompletion [s]
  (let [scores {\) 1
                \] 2
                \} 3
                \> 4}]
    (reduce (fn [score ch] (+ (* score 5) (get scores ch))) 0 s)))

(defn- winning-score [scores]
  (nth (sort scores) (/ (count scores) 2)))

(defn solution-part-two [input]
  (->> (parse-input input)
       (map parse-chunks)
       (filter (complement corrupted?))
       (map autocomplete)
       (map score-autocompletion)
       (winning-score)))

