(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "I am an enormously huge teapot!"))

;;pg41
(defn error-message
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))

(error-message :mild)



;;---------------------- DATA STRUCTURES

;;numbers

93
1.2
1/5





;;---------------------- STRINGS

"Lord Voldermot"
"\"He who must not be named\""
"\"Great cow of Moscow!\" - Hermes Conrad"

;;clojure only allows string concatenation via the `str` function
(def character_name "Chewbacca")
(str "\"Uggllglglglglglglglll\" -" character_name)





;;---------------------- MAPS (SIMILAR TO DICTIONARIES)

;;an empty map
{}

{:first-name "Charlie"
 :last-name "McFishwich"}

;;associating "string-key" with the + function
{"string-key" +}

;;maps can be nested
{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}}

;;You can use the hash-map function to create a map
(hash-map :a 1 :b 2)


;;Look up values in a map with the `get` function

;;example
(def letter-map (hash-map :a 10 :b 20 :c 30))

(get letter-map :a)

;;example
(def nested-map (hash-map :a 0 :b (hash-map :c "ho hum")))

(get nested-map :b)



;;`get` will return `nil` if your key is not found
(get {:u "here" :v "there"} :a)

;;you can also provide a default value for missing keys
(def missing-keys (hash-map :r 45 :s 56 :t 78))

(get missing-keys :q "no key of that nature")





;;---------------------- KEYWORDS


;;--> They are primarily used as keys in maps

:a
:rumplestiltsken
:34
:_?


;;--> Used as functions to look up a value in data structures

(:a {:a 1 :b 2 :c 3}) ;;using :a to look that key up in the map

(:d {:a 1 :b 2 :c 3} "No gnome know homes like Noah knows") ;;you can provide a default value as well.





;;---------------------- VECTORS (SIMILAR TO ARRAYS)

;;similar to arrays in other languages, and allows for mixed types


[3 2 1] ;;a simple vector

(get [3 2 1] 0) ;;getting the zeroth element

(get ["a" {:name "Pugsley Winterbottom"} "c"] 1) ;;getting the first element

(vector "creepy" "full" "moon") ;;using the `vector` function to create a vector


;;Adding more elements to the vector using `conj`

(conj [1 2 3 4] 5) ;;gives [1 2 3 4 5]





;;---------------------- LISTS (SIMILAR TO VECTORS, BUT A LITTLE DIFFERENTS)

;;creating a list

'(1 2 3 4) ;;how to construct a list literal . The quotation at the beginning is required


;;retrieving elements with the `nth` function (because you can't use get)

(nth '(:a :b :c) 0)

(nth '(:a :b :c) 2)


;;performance sidenote : using `nth` to get an element in a list is slower than using
;;`get` to get an element from a vector. This is because `nth` has to visit every element in a list


;;lists can contain different values
(list 1 "two" {3 4})

;;elements are added to the beginning of the list
(conj '(1 2 3) 4)





;;---------------------- SETS (NO DUPLICATES ALLOWED)

;;Collections of unique values


;;How to make a literal set
#{"kurt vonnegut" 20 :icicle}


;;You may also use `hash-set` to create a set
;;Note that duplicates are removed
(hash-set 1 1 2 2)


;;using conj adds an element (if it doesn't exist yet) to the set.
(conj #{:q :r :s} :t)

(conj #{:q :r :s} :r :a)


;;you can also make a set out of a vector and a list using the `set` function

(set [1 2 3 3 3 8 8 9])

(set {:d "here" :r "there"})


;;you can check whether an element exists in a set using the `contains?` function

(def our_set (set ["a" 1 :c]))

(contains? our_set :c)


;;you can check for an existing element using a keyword as well ...

(:a #{:a :b})


;;you can also use the `get` function as well...

(get (hash-set :a :d :r "here" "there" "everywhere") :r)










;;###################### FUNCTIONS  ######################



;;---------------------- CALLING FUNCTIONS

(+ 1 2 3 4)

(* 1 2 3 4)

(first [1 2 3 4])

(last [1 2 3 4])

;;recall that `or` returns the first truthy value (or if none exist, the last value)
;;the function below will return

(or + -)


;;you can therefore use the return value of one function as a function for some other operation

((or + -) 1 2)

((and (= 1 1) +) 1 2 3)

((first [+ 0]) 1 2 3)


;;Higher order functions are those that can either take a function as an argument
;;for example the `map` function (that applies another function over every element in a collection)

(inc 1.1)

(map inc [1 2 3 4]) ;;Note: `map` does not return a vector, even when we gave it one



;;---------------------- special forms

;;do not always evaluate all of their operands.
;;An if statement is an example

(def using-twitter true)

(defn tweet
  "Returns the given string with `Tweet:` prepended"
  [text]
  (str "Tweet: " text))


(if using-twitter
  (tweet "Hello Miss Akdag!")
  (println "You are not using Twitter at the moment"))



;;---------------------- DEFINING FUNCTIONS


;;general structure of function declaration ----------------------

;(defn <functionname>
;;<optional function description / docstring>
;;<[arguments]>
;;<function body>)

(defn too-enthusiastic
  "Return a cheer that might be too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINETLY LIKE THE BEST "
       "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))

(too-enthusiastic "Zelda")


;;functions can have different number of parameters a.k.a arity ----------------------

(defn no-params
  "I take no parameters!"
  [])

(defn one-param
  [x]
  (str "I take one parameter: " x))

(defn two-params
  [x y]
  (str "Two parameters! That's nothing! Pah! I will smooth them "
       "together to spite you! " x y))


;;functions can have multiple arities, to decide which version of the function runs ----------------------
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


;;rest parameter & ----------------------

(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!!!"))


(defn codger
  [& whippersnapper]
  (map codger-communication whippersnapper))

(codger "Billy" "Anne-Marie" "The incredible Bulk")


;;mixing rest-parameters and normal parameters ----------------------
;;The rest parameter must come last if you are doing this

(defn favorite-things
  [person-name & things]
  (str "Hi, " person-name ", here are my favorite things: "
       (clojure.string/join ", " things)))

(favorite-things "Doreen" "gum" "shoes" "kara-te")



;;DESTRUCTURING ----------------------


;;Return the first element of a collection ----------------------

(defn my-first
  [[first-thing]]
  (str "The first thing is: " first-thing))

(my-first ["oven" "bike" "war-axe"])


(defn chooser
  "Lists the first choice, second choice, and the rest of the choices"
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

(chooser ["Marmalade" "Handsome Jack" "Pigpen" "Aquaman"])



;;Destructure maps ----------------------

(defn announce-treasure-location
  "Shows the latitude and longitude of the treasure"
  [{latitude :lat longitude :lng}]
  (println (str "Treasure is at latitude: " latitude))
  (println (str "Treasure is at longitude: " longitude)))

(announce-treasure-location {:lat 28.2 :lng 81.33})


