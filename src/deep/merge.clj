(ns deep.merge
  (:gen-class))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;L;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;These are variables needed for the main '' method of this project ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(def ^:dynamic *vector-merge-method*)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; This is the main method that can be used by redefining the above variables ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defn deep-merge
  ""
  ([] nil)
  ([& obj-args]
   (cond
     (every? map? obj-args) (apply merge-with deep-merge obj-args)
     (every? vector? obj-args) (apply *vector-merge-method* obj-args)
     :always (last obj-args))))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; These methods define the various methods by which vectors can be merged ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
(defmacro with-vector-merge-method
  "Adds a vector merge strategy to deep-merge"
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

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;; Setting defaults for deep-merge ;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;TODO: Get this working. I can't bind the root value even if I set the default to nil
#_(alter-var-root (var *vector-merge-method*) #(vector-blind-merge))