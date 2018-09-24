(ns deep.merge-test
  (:require [clojure.test :refer :all]
            [deep.merge :as sut]))

(defn deep-merge-test
  [description output & input]
  (testing description (is (= output (apply sut/deep-merge input)))))

(deftest test-deep-merge
  (deep-merge-test
    "Can deep-merge be called without setting any strategies"
    {:a :b, :c :d :e :f}
    {:a :b} {:c :d} {:e :f})
  (deep-merge-test
    "Can deep-merge work if two values are passed in"
    :b
    :a :b))

(deftest test-blind-merge
  (sut/with-vector-merge-method
    sut/vector-blind-merge
    (deep-merge-test
      "Can deep-merge merge two arrays"
      [:a :b :c :d]
      [:a :b] [:c :d])
    (deep-merge-test
      "Will deep merge keep all items of two arrays"
      [:a :b :b :c]
      [:a :b] [:b :c])
    (deep-merge-test
      "Does merging two arrays within two maps work well?"
      {:a [{:b :c} {:d :e} {:b :d} {:e :d}]}
      {:a [{:b :c} {:d :e}]} {:a [{:b :d} {:e :d}]})))

(deftest test-index-merge
  (sut/with-vector-merge-method
    sut/vector-index-merge
    (deep-merge-test
      "Will deep merge keep all items of two arrays"
      [:b :c]
      [:a :b] [:b :c])))