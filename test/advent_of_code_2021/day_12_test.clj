(ns advent-of-code-2021.day-12-test
  (:require
   [advent-of-code-2021.day-12 :refer :all]
   [advent-of-code-2021.utils :refer [def-]]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(def example-input (string/join "\n"
                                ["start-A"
                                 "start-b"
                                 "A-c"
                                 "A-b"
                                 "b-d"
                                 "A-end"
                                 "b-end"]))


(def larger-example-input (string/join "\n"
                                       ["dc-end"
                                        "HN-start"
                                        "start-kj"
                                        "dc-start"
                                        "dc-HN"
                                        "LN-dc"
                                        "HN-end"
                                        "kj-sa"
                                        "kj-HN"
                                        "kj-dc"]))

(def largest-example-input (string/join "\n"
                                        ["fs-end"
                                         "he-DX"
                                         "fs-he"
                                         "start-DX"
                                         "pj-DX"
                                         "end-zg"
                                         "zg-sl"
                                         "zg-pj"
                                         "pj-he"
                                         "RW-he"
                                         "fs-DX"
                                         "pj-RW"
                                         "zg-RW"
                                         "start-pj"
                                         "he-WI"
                                         "zg-he"
                                         "pj-fs"
                                         "start-RW"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 10  (solution-part-one example-input)))
  (is (= 19  (solution-part-one larger-example-input)))
  (is (= 226 (solution-part-one largest-example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 4773 (solution-part-one problem-input))))
