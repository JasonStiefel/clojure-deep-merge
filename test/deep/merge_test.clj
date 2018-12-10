(ns deep.merge-test
  (:require [clojure.test :refer :all]
            [deep.merge :as sut]))

(deftest test-concat-merge
  (testing "Various expected outputs of `concat-merge`, given various inputs"
    (are
      [arg-list expected-output]
      (= expected-output (apply sut/concat-merge arg-list))
      [{:a :b} {:c :d}] {:a :b :c :d}
      [{:a :b} {:a :d}] {:a :d}
      [{:a [:b]} {:c [:d]}] {:a [:b] :c [:d]}
      [{:a [:b :d]} {:c [:d]}] {:a [:b :d] :c [:d]}
      [{:a [:b]} {:a [:d]}] {:a [:b :d]}
      [{:a [:b :d]} {:a [:d]}] {:a [:b :d :d]}
      [{:a [:b :c]} {:a [:d]}] {:a [:b :c :d]}
      [{:a [:b]} {:a [:c]} {:a [:d]}] {:a [:b :c :d]}
      [{:a [{:b :c}]} {:a [{:b :c}]}] {:a [{:b :c} {:b :c}]}
      [{:a :b :c [:d]}] {:a :b :c [:d]}
      [{:a :b :c [:d]} {:e :f} {:c [:e]}] {:a :b :c [:d :e] :e :f})))

(deftest test-distinct-merge
  (testing "Various expected outputs of `distinct-merge`, given various inputs"
    (are
      [arg-list expected-output]
      (= expected-output (apply sut/distinct-merge arg-list))
      [{:a :b} {:c :d}] {:a :b :c :d}
      [{:a :b} {:a :d}] {:a :d}
      [{:a [:b]} {:c [:d]}] {:a [:b] :c [:d]}
      [{:a [{:b :c}]} {:a [{:b :c}]}] {:a [{:b :c}]}
      [{:a [:b :d]} {:a [:d]}] {:a [:b :d]})))

(deftest test-index-merge
  (testing "Various expected outputs of `index-merge`, given various inputs"
    (are
      [arg-list expected-output]
      (= expected-output (apply sut/index-merge arg-list))
      [{:a :b} {:c :d}] {:a :b :c :d}
      [{:a :b} {:a :d}] {:a :d}
      [{:a [:b]} {:c [:d]}] {:a [:b] :c [:d]}
      [{:a [{:b :c}]} {:a [{:b :c}]}] {:a [{:b :c}]}
      [{:a [:b :d]} {:a [:d]}] {:a [:d :d]})))