(ns aoc2022.day01
  (:require [clojure.string :as string]))


(defn get-calories-per-elf
  [fname]
  (let [lines (string/split-lines (slurp fname))
        calories (partition-by #(= % "") lines)]
    (for [cal calories :when (not= cal '(""))]
      (apply + (map read-string cal)))))


(defn solution-1
  "Find the highest value of calory carried by an elf"
  [fname]
  (apply max (get-calories-per-elf fname)))


(defn solution-2
  "Sum the 3 highest values of calory carried by the elves"
  [fname]
  (apply + (take-last 3 (sort (get-calories-per-elf fname)))))


;; Get the highest value of calories brought by an elf
(solution-1 "resources/day01.txt")

;; Sum of the top 3 calories
(solution-2 "resources/day01.txt")
