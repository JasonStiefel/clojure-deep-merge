(defproject clojure-deep-merge "0.1.1"
  :url "https://github.com/JasonStiefel/clojure-deep-merge"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :main ^:skip-aot clojure-deep-merge.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
