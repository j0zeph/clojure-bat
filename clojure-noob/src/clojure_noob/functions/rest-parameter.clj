;;---------- REST PARAMETER (&) ----------
;;
;; Allows  you to group provided parameters into a list
;; When mixing normal parameters and rest parameters, the rest parameter must always come last

(defn hello-to-you
  [person-name]
  (str "Hello, " person-name "!"))


(defn hello-to-your-friends
  [& your-friends]
  (map hello-to-you your-friends))

(hello-to-your-friends "Magdalena", "Rosetta", "Clarice", "Rene")


;;mixing rest-parameters and normal parameters ----------------------

(defn pick-combat-items
  [character-name & combat-items]
  (str "Hi " character-name ", here are your combat items; "
       (clojure.string/join ", " combat-items)))

(pick-combat-items "Stranger" "spoon" "pen" "sword")
