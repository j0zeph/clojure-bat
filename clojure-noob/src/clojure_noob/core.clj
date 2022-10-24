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










;;#################### DATA STRUCTURES ####################


;;---------- NUMBERS ----------


99
4.2
22/7





;;---------- STRINGS ----------


"Clara Oswald"
"\"The impossible girl\""
"\"Hello Sweetie!\" - Puffy hair"


;;clojure only allows string concatenation via the `str` function
(def mood "quite functional at the moment")
(str "I feel " mood "at the moment, actually.")





;;---------- MAPS (similar to dictionaries) ----------


;;an empty map
{}


{:shirt-color "red"
 :shirt-size "small"}


;;associating the string "add-these" with the + function
{"add-these" +}


;;maps can be nested
{:name {:first-day "Monday"
        :middle-day "Wednesday"
        :last-day "Sunday"}}


;;you can use the hash-map function to create a map
(hash-map :a 1 :b 2)


;;look up values in a map with the `get` function
(def letter-ascii (hash-map :a 97 :b 98 :c 99))

(get letter-ascii :a)


(def nested-map (hash-map :a 97 :b (hash-map :c 99)))

(get nested-map :b)


;;`get` will return `nil` if your key is not found
(get {:u "here" :v "there"} :a)


;;you can also provide a default value for missing keys
(def get-more-ascii (hash-map :r 114 :s 115 :t 116))

(get get-more-ascii :q "no key of that nature")





;;---------- VECTORS (similar to arrays in other languages) ----------
;;
;; they allow for mixed data


;;creating a simple vector literally
[10 9 8 7 6 5 4 3 2 1]


;;creating a vector using the `vector` function
(vector "and a one" "and a two" "and a one, two, three, four")


;;getting the zeroth element with `get`
(get [10 9 8 7 6 5 4 3 2 1] 0)


;;getting the first element
(get ["x" {:name "Shayala Buff"} "brandishing a kinfe..."] 1)


;;adding more elements to the vector using `conj`

(conj [:z :y :x :w :v] :u) ;;gives  an output of [:z :y :x :w :v :u]





;;---------- KEYWORDS (similar to arrays in other languages) ----------

;;keywords are primarily used as keys in maps
;;keywords start with ':'

:z
:space
:43
:_?


;;Keywords are also used as functions to look up a value in data structures
;;using :a to look up the value of that key in a map
(:a {:a 1 :b 2 :c 3})


;;looking up the value of the key :d, and also providing a default value,
;;if the value is not present.
(:d {:a 1 :b 2 :c 3} "No gnome know homes like Noah knows")





;;---------- LISTS (similar to vectors, but different in some respects) ----------


;;creating a list literally.
;;The single quote at the beginning is required
'(1 2 3 4)


;;retrieving elements with the `nth` function (because you can't use get)

(nth '(:a :b :c) 0)

(nth '(:a :b :c) 2)


;;performance sidenote : using `nth` to get an element in a list is slower than using
;;`get` to get an element from a vector. 
;;This is because `nth` has to visit every element in a list


;;lists can contain different types of values (t is heterogenous)
(list 1 "two" {3 4})


;;using `conj` to add elements at the `beginning` of the list
(conj '(1 2 3) 4)





;;---------- SETS (no duplicates allowed) ----------
;;
;;Collections of unique values


;;creating a set, literally
#{"kurt vonnegut" 20 :icicle}


;;Using the `hash-set` function to create a set (Note that duplicates will be removed)
(hash-set 1 1 2 2)


;;making a set out of a vector using the `set `function
(set [1 2 3 3 3 8 8 9])


;;making a set out of a map using the `set `function
(set {:d "here" :r "there"})


;;using conj adds an element (if it doesn't exist yet) to the set.
(conj #{:q :r :s} :t)

(conj #{:q :r :s} :r :a)


;;check whether an element exists in a set using the `contains?` function

(def our_set (set ["a" 1 :c]))

(contains? our_set :c)


;;you can also check for an existing element using a keyword as well
;;`nil` will be returned if the element does not exist

(:a #{:a :b})


;;you can also use the `get` function to check if an element exists

(get (hash-set :a :d :r "here" "there" "everywhere") :r)










;;###################### FUNCTIONS  ######################


;;---------- CALLING/USING FUNCTIONS ----------


(+ 5 5 5)


(* 5 4 3 2 1)


(first [1 2 3 4 5 6 7 8])


(last ["a one" "a two" "a three" "four"])


;;recall that `or` returns the first truthy value (or if none exists, the last value)

(or + -)


;;you can therefore use the return value of one function as a function that does something else

((or + -) 1 2)


;;recall that `and` returns the first falsey value (or if none exists, the last value)

((and (= 1 1) +) 1 2 3)


((first [+ 0]) 1 2 3)


;;higher-order functions are those that can either take a function as an argument or
;;return a function

;;for example the `map` function (that applies a function over every element in a collection)

(inc 1.1) ;; recall that the `inc` function increases whatever value we give it


(map inc [1 2 3 4])





;;---------- SPECIAL FORMS ----------
;;
;;do not always evaluate all of their operands.


;;An if statement is an example of a special form

(def using-twitter true)

(defn tweet
  "Returns the given string with `Tweet:` prepended"
  [text]
  (str "Tweet: " text))


(if using-twitter
  (tweet "Hello Miss Akdag!")
  (println "You are not using Twitter at the moment"))


;; special forms also CANNOT be used as arguments to other functions



;;---------- DEFINING FUNCTIONS ----------


;;general structure of function declaration

;(defn <functionname>
;;<optional function description / docstring>
;;<[arguments]>
;;<function body>)


(defn hello-friend
  "Tell the friend about some secret"
  [friend-name]
  (str "Hello " friend-name " , \nWhat I'm about to tell you is top-secret. "
       "\nA conspiracy bigger than all of us ..."))

(println (hello-friend "Taylor"))


;;functions can have any number of parameters (they can have any "arity")

(defn no-params
  "I take no parameters!"
  [])

(defn one-param
  "Functions that takes one parameter"
  [x]
  (str "I take one parameter: " x))

(defn two-params
  "Function that takes two parameters"
  [x y]
  (println (str "I take two parameters, and they are: " x " and " y " too.")))


(no-params)

(one-param "this one")

(two-params "\"this one\"" "\"this one\"")


;;functions can have multiple arities, to decide which version of the function runs
;;this is a nice way to provide default arguments to your function

(defn name-and-location
  "Greets the creature, and asks how the weather is in their location"

  ([name location]
   (str "Hello " name "! " "How is the weather in " location " today?"))

  ([name]
   (name-and-location name "earth"))

  ([]
   (name-and-location "human" "the solar system")))


(name-and-location "Megan" "Scotland")

(name-and-location "Megan")

(name-and-location)



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
