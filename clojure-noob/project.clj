(defproject clojure-noob "0.1.0-SNAPSHOT"
  :description "A chronological work-through of the book "Clojure for the brave and true" by Daniel Higginbotham"
  :url "https://github.com/j0zeph/clojure-bat"
  :license {:name "MIT License"
            :url "https://mit-license.org/"}
  :dependencies [[org.clojure/clojure "1.11.1"]]
  :main ^:skip-aot clojure-noob.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
