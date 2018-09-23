(ns deep.merge-test
  (:require [clojure.test :refer :all]
            [deep.merge :refer :all :as core]))

(deftest test-vector-blind-merge
  (testing "Can deep-merge be called without setting any strategies"
    (is (= (deep-merge {:a :b} {:c :d} {:e :f}) {:a :b, :c :d :e :f})))
  (testing "Can deep-merge work if two values are passed in"
    (is (= (deep-merge :a :b) :b)))
  (core/with-vector-merge-method
    core/vector-blind-merge
    (is (= (deep-merge [:a :b] [:c :d]) [:a :b :c :d]))))
