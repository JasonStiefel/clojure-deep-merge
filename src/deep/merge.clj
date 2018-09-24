(ns deep.merge
  (:gen-class))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;L;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; These are variables needed for the main 'deep-merge' method of this project ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defonce ^:dynamic *vector-merge-method* nil)
(defonce ^:dynamic *merge-mixed-vector-map-sets* false)
(defmacro with-mixed-vector-map-merging
  "deep-merges within this macro will allow mixed sets of vectors/maps to be merged.
  This happens by taking maps and putting them into the first index of new vectors.
  After the conversion happens, deep merge is called again."
  [& body]
  `(binding [*merge-mixed-vector-map-sets* true]
     ~@body))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; This is the main method that can be used by redefining the above variables ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn deep-merge
  "Based on the various environment variables in this namespace, merge two maps or vectors.
  If a merge of the objects cannot happen, the last object is returned."
  ([] nil)
  ([& obj-args]
   (cond
     (every? map? obj-args) (apply merge-with deep-merge obj-args)
     (every? vector? obj-args) (apply *vector-merge-method* obj-args)
     (and *merge-mixed-vector-map-sets* (every? #(or (map? %) (vector? %)) obj-args)) (deep-merge (map #(if (map? %) [%] %)))
     :always (last obj-args))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; These methods define the various methods by which vectors can be merged ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmacro with-vector-merge-method
  "Adds a vector merge strategy to deep-merge."
  [method & body]
  `(binding [*vector-merge-method* ~method]
     ~@body))
(defn vector-blind-merge
  "Blindly puts all vectors passed in into one vector.
  If not all items are vectors, the last item is returned"
  [& vectors]
  (if (every? vector? vectors)
    (apply concat vectors)
    (last vectors)))
(defn vector-index-merge
  "Looks at each index across all vectors and runs deep-merge on that.
  If not all items are vectors, the last item is returned."
  [& vectors]
  (if (every? vector? vectors)
    (let [max-index (max (map count vectors))
          get-all-indexes (fn [index] (filter
                                        #(not (nil? %))
                                        (map #(nth % index nil) vectors)))]
      (for [i (range 0 max-index)] (deep-merge (get-all-indexes i))))
    (last vectors)))
(defn vector-blind-merge-with-dedupe
  "Does the same thing as blind merge, but applies distinct to the resulting vector.
  If not all items are vectors, the last item is returned."
  [& vectors]
  (if (every? vector? vectors)
    (distinct (apply concat vectors))
    (last vectors)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Setting defaults for deep-merge ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;TODO: Get this working. I can't bind the root value even if I set the default to nil
(alter-var-root (var *vector-merge-method*) vector-blind-merge)