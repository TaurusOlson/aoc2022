(ns aoc2022.day04
  (:require
    [clojure.string :as string]
    [clojure.set :as set]))


(defn xrange
  [[xmin xmax]]
  (range xmin (inc xmax)))


(defn parse-ids
  [id-range]
  (->> (string/split id-range #"-")
       (map read-string)
       xrange))


(defn parse-pair
  "Parse the pair or ranges"
  [pair]
  (map parse-ids pair))


(defn line->pair
  [line]
  (string/split line #","))
       

(defn pair-fully-contained?
  [[p1 p2]]
  (or
    (set/superset? (set p1) (set p2))
    (set/superset? (set p2) (set p1))))


(defn pair-overlapped?
  [[p1 p2]]
  (set/intersection (set p1) (set p2)))


(defn solution-1
  "Count the number of fully contained pairs of IDs"
  [fname]
  (->> (string/split-lines (slurp fname))
       (map (comp pair-fully-contained? parse-pair line->pair))
       (filter true?)
       count))


(defn solution-2
  "Count the number of overlapped pairs of IDs"
  [fname]
  (->> (string/split-lines (slurp fname))
       (map (comp pair-overlapped? parse-pair line->pair))
       (filter (comp not empty?))
       count))


(solution-1 "resources/day04.txt")
(solution-2 "resources/day04.txt")
