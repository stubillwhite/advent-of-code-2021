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

