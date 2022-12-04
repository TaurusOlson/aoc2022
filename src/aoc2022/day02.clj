(ns aoc2022.day02
  (:require [clojure.string :as string]))


;; This matrix determines the results from the triplets (A, B, C) and (X, Y, Z)
;; where (A, B, C) are the opponent choices:
;; A: rock, B: paper, C: scissor
;;
;; and (X, Y, Z) are my choices
;; X: rock, Y: paper, Z: scissor

;; Indexes of the matrix
;; 1 2 3
;; 2 
;; 3 
;; Number of points
;; lost: 0 point
;; draw: 3 points
;; win: 6 points

(def results
  [[3 0 6]
   [6 3 0]
   [0 6 3]])


;; This matrix determines the choice to make from the triplets (A, B, C) and (X, Y, Z)
;; where (A, B, C) are the opponent choices
;; A: rock, B: paper, C: scissor
;;
;; and (X, Y, Z) are the results
;; X: lost, Y: draw, Z: win

;; X Y Z 
;; A 
;; B
;; C
(def choices
  [[2 1 3]
   [3 2 1]
   [1 3 2]])


(defn determine-result
  [x-val y-val]
  (nth (nth results (dec x-val)) (dec y-val)))


(defn determine-choice
  [op-val result]
  ;; We invert the points because the result is given for us not the opponent
  (let [results [6 3 0]]
    (nth (nth choices (dec op-val)) (.indexOf results result))))
  

(defn count-score
  [me-val op-val]
  (+ (determine-result me-val op-val) me-val))


(defn get-games
  [fname]
  (let [lines (string/split-lines (slurp fname))]
    (map #(string/split % #" ") lines)))


;; 1: rock
;; 2: paper
;; 3: scissor
(defn solution-1
  "Sum the scores of each game when the line is [opponent me]"
  [fname]
  (apply +
    (let [letter->val {"A" 1 "B" 2 "C" 3 "X" 1 "Y" 2 "Z" 3}]
      (map (fn [[op me]] (count-score (letter->val me) (letter->val op)))
           (get-games fname)))))


(defn solution-2
  "Sum the scores of each game when the line is [opponent result]"
  [fname]
  (apply +
    (let [letter->val {"A" 1 "B" 2 "C" 3 "X" 0 "Y" 3 "Z" 6}]
     (map (fn [[op result]] (+ (letter->val result)
                               (determine-choice (letter->val op) (letter->val result))))
          (get-games fname)))))


(solution-1 "resources/day02.txt")
(solution-2 "resources/day02.txt")
