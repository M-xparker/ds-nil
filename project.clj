(defproject ds-nil "0.1.0-SNAPSHOT"
  :description "FIXME: write this!"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "1.7.122"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [datascript "0.11.6"]
                 [compojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.4.0"]
                 [com.cognitect/transit-clj "0.8.281"]
                 [com.cognitect/transit-cljs "0.8.220"]
                 [hiccup "1.0.5"]]

  :plugins [[lein-ring "0.9.6"]
            [lein-cljsbuild "1.1.0"]]

  :source-paths ["src/clj"]
  :main ds-nil.core
  :ring {:handler ds-nil.core/app}
  :cljsbuild {
    :builds [{:id "min"
              :source-paths ["src/cljs"]
              :compiler {:output-to "resources/public/js/compiled/ds_nil.js"
                         :main ds-nil.core
                         :optimizations :advanced
                         :pretty-print false}}]})
