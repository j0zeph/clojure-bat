(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I am an enormously huge planet, Jupiter said!"))

;;pg41
(defn danger-alert
  [danger-level]
  (str "That's terrifying! "
       (if (= danger-level :not-acute)
         "but also nothing that serious!"
         "we may not make it alive!")))

(danger-alert :not-acute)
