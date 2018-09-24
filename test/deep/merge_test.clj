(ns deep.merge-test
  (:require [clojure.test :refer :all]
            [deep.merge :as sut]))

(deftest test-deep-merge
  (testing "Can deep-merge be called without setting any strategies"
    (is (= (sut/deep-merge {:a :b} {:c :d} {:e :f}) {:a :b, :c :d :e :f})))
  (testing "Can deep-merge work if two values are passed in"
    (is (= (sut/deep-merge :a :b) :b))))

(deftest test-blind-merge
  (sut/with-vector-merge-method
    sut/vector-blind-merge
    (testing "Can deep-merge merge two arrays"
      (is (= (sut/deep-merge [:a :b] [:c :d]) [:a :b :c :d])))
    (testing "Will deep merge keep all items of two arrays"
      (is (= (sut/deep-merge [:a :b] [:b :c]) [:a :b :b :c])))
    (testing "Does merging two arrays within two maps work well?"
      (is (= (sut/deep-merge {:a [{:b :c} {:d :e}]} {:a [{:b :d} {:e :d}]})
             {:a [{:b :c} {:d :e} {:b :d} {:e :d}]})))))
