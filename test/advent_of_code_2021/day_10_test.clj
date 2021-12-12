(ns advent-of-code-2021.day-10-test
  (:require
   [advent-of-code-2021.day-10 :refer :all]
   [clojure.string :as string]
   [clojure.test :refer :all]))

(def example-input
  (string/join "\n" ["[({(<(())[]>[[{[]{<()<>>"
                     "[(()[<>])]({[<{<<[]>>("
                     "{([(<{}[<>[]}>{[]{[(<()>"
                     "(((({<>}<{<{<>}{[]{[]{}"
                     "[[<[([]))<([[{}[[()]]]"
                     "[{[{({}]{}}([{[{{{}}([]"
                     "{<[[]]>}<{[{[{[]{()[[[]"
                     "[<(<(<(<{}))><([]([]()"
                     "<{([([[(<>()){}]>(<<{{"
                     "<{([{{}}[<[[[<>{}]]]>[]]"]))

(deftest solution-part-one-given-example-input-then-example-result
  (is (= 26397 (solution-part-one example-input))))

(deftest solution-part-one-given-problem-input-then-correct-result
  (is (= 436497 (solution-part-one problem-input))))
