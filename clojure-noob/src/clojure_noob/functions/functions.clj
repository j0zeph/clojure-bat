;;A work-through of the `Functions section of 
;;Chapter 3: Do Things: A Clojure Crash Course





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
