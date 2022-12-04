(ns aoc2022.day03
  (:require
    [clojure.string :as string]
    [clojure.set :as set]))


(defn split-in-middle
  [line]
  (split-at (/ (count line) 2) line))


(defn find-similar-item
  [[c1 c2]]
  (first (set/intersection (set c1) (set c2))))


(defn get-priority
  [s]
  (if (Character/isUpperCase s)
    (+ (- (int s) 64) 26)
    (- (int s) 96)))
  

(defn find-badge
  [[elf1 elf2 elf3]]
  (set/intersection
    (set elf1)
    (set elf2)
    (set elf3)))

(defn solution-1
  "Sum the priorities of the similar items in each rucksack compartiment"
  [fname]
  (->> (string/split-lines (slurp fname))
       (map (comp get-priority find-similar-item split-in-middle))
       (apply +)))


(defn solution-2 
  "Sum the priorities of the badges in each group of 3 elves"
  [fname]
  (->> (string/split-lines (slurp fname))
       (partition 3)
       (map (comp get-priority first find-badge))
       (apply +)))
       

(solution-1 "resources/day03.txt")
(solution-2 "resources/day03.txt")

