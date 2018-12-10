(ns deep.merge
  (:gen-class))

(defn- deep-coll-merge-with
  "The base method used for recursive collection merging"
  [collection-merge-method non-collection-merge-method & vals]
  (let [this-method (partial deep-coll-merge-with collection-merge-method non-collection-merge-method)]
    (cond
      (every? map? vals) (apply merge-with this-method vals)
      (every? coll? vals) (apply collection-merge-method vals)
      :else (apply non-collection-merge-method vals))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Collection Merge Methods ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn- concat-coll-merge
  "Puts all items from all args blindly into one vector"
  [& args]
  (->> args
       (apply concat)
       (into [])))
(defn- distinct-concat-coll-merge
  "Puts all items from all args into one deduplicated vector"
  [& args]
  (->> args
       (apply concat)
       (distinct)
       (into [])))
(defn- index-coll-merge
  "Looks at each index of all args and runs deep-coll-merge-with on all items at each index"
  [non-collection-merge-method & args]
  (let [max-index (apply max (map count args))
        values-at-index (fn [index] (->> args
                                         (filter #(< index (count %)))
                                         (map #(nth % index))))
        merge-index #(apply deep-coll-merge-with index-coll-merge non-collection-merge-method (values-at-index %))]
    (->> (range 0 max-index)
         (map merge-index)
         (into []))))

;;;;;;;;;;;;;;;;;;;;;
;; Wrapper Methods ;;
;;;;;;;;;;;;;;;;;;;;;
(def concat-merge-with (partial deep-coll-merge-with concat-coll-merge))
(def concat-merge (partial concat-merge-with #(last %&)))
(def distinct-merge-with (partial deep-coll-merge-with distinct-concat-coll-merge))
(def distinct-merge (partial distinct-merge-with #(last %&)))
(defn index-merge-with
  [non-collection-merge-method & vals]
  (apply deep-coll-merge-with (partial index-coll-merge non-collection-merge-method) non-collection-merge-method vals))
(def index-merge (partial index-merge-with #(last %&)))