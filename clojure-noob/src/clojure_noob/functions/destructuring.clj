;;---------- DESTRUCTURING ----------
;;
;;Allows you to concisely bind names to values within a collection


;;Returning the first element of a collection (a vector in this case) using destructuring

(defn first-place
  [[first-runner]]
  (str "The runner in first place is: " first-runner))

(first-place ["Julien" "Borris" "Travis"])


(defn give-medals
  "Gives the first three runners a gold, silver, and bronze medal, then lists everyone else"
  [[first-runner second-runner third-runner & everyone-else]]
  (println (str "The gold medal goes to: " first-runner))
  (println (str "The silver medal goes to: " second-runner))
  (println (str "The bronze medal goes to: " third-runner))
  (println (str "And here is everyone else: " (clojure.string/join ", " everyone-else))))

(give-medals  ["Julien" "Borris" "Travis" "Marcus" "Daniel" "Freddie"])



;;Destructuring maps

(defn announce-point-location
  "Shows the x, y, and z coordinate location of a point"
  [{x-coordinate :x y-coordinate :y z-coordinate :z}]
  (println (str "x-coordinate is: " x-coordinate))
  (println (str "y-coordinate is: " y-coordinate))
  (println (str "z-coordinate is: " z-coordinate)))

(announce-point-location {:x 200 :y 300 :z 120})



;;Destructuring maps, simpler version using `:keys` to more directly extract the keywords
;;from a map

(defn announce-point-location-simpler
  "Shows the x, y, and z coordinate location of a point"
  [{:keys [x y z]}]
  (println (str "x-coordinate is: " x))
  (println (str "y-coordinate is: " y))
  (println (str "z-coordinate is: " z)))

;; note: you have to use the same names as the actual keys in the map

(announce-point-location-simpler {:x 200 :y 300 :z 120})



;;Destructure maps, while retaining the original map using the :as keyword

(defn receive-point-location
  [{:keys [x y z] :as point-location}]
  (println (str "x-coordinate is: " x))
  (println (str "y-coordinate is: " y))
  (println (str "z-coordinate is: " z))

  (println (str "The original point location was: " point-location)))

(receive-point-location {:x 10 :y 20 :z 30})
