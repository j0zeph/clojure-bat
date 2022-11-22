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





;;---------- FUNCTION BODY ----------
;;
;; When a function has many forms, clojure will return the last form that was evaluated.
;; i.e. The `return` statement in clojure is implicit

(defn return-last-evaluated
  []
  #{1 2 3}
  (inc (get #{1 2 3} 1)))

(return-last-evaluated)



;; An example function that uses an if expression

(defn weather-comment
  "Takes a temperature (in celcius) and says whether it is above or below 25C"
  [temp]
  (if (< temp 25)
    "That's below 25C"
    (if (= temp 25)
      "That's 25C"
      "That's above 25C")))

(weather-comment 25)





;;---------- ANONYMOUS FUNCTIONS ----------
;;
;; Functions with no names
;;
;; the general form is:
;;
;; (fn [param-list]
;;   function body)



;; An example of an anonymous function

(map (fn [item-adjective]
       (str "Bring along something " item-adjective))
     ["old" "new" "borrowed" "blue"])


;; You can still associate an anonymous function with a name

(def character-counter (fn [word] (count word)))
(character-counter "banana")



;; There is a more compact way to create anonymous functions
;; using the `#` and `%` symbols

;; example: Anonymous function that adds 10 to a number
;; (#(+ % 10))

;; Applying that function to the number 35

(#(+ % 10) 35)


;; Re-writing the something old, new, borrowed, blue function to use the
;; compact syntax

(map #(str "Bring along something " %)
     ["old" "new" "borrowed" "blue"])



;; If the anonymous function take more than one argument, you can use many
;; `%` signs with a number --that represents the position of the argument--
;; to the right of it

(#(str "Bring along something " %1 ", " %2 ", " %3 ", and " %4)
 "old" "new" "borrowed" "blue")


;; Yes, shuffling the numbers around will shuffle the arguments as well

(#(str "Bring along something " %2 ", " %3 ", " %1 ", and " %4)
 "old" "new" "borrowed" "blue")


;; You can also use the rest parameter by using `%&`
;; `identity` returns the arguments it was given as a list

(#(identity %&) "old" "new" "borrowed" "blue")





;;---------- RETURNING FUNCTIONS ----------
;;
;; Functions can return other functions.
;; The returned functions still have access to all the variables that were in scope when the 
;; funcition was created.



(defn inc-maker
  "Create a custom incrementor"
  [increase-by]
  #(+ % increase-by)) ;; inc-maker here returns an anonymous function


;; when 3 is passed to inc-maker, the anonymous function that is returned is now outside of inc-maker
;; but the `increase-by` binding (the 3 that was passed earlier) is still accesible by the anonymous
;; function that was returned.

(def inc-by-3 (inc-maker 3))

(inc-by-3 7)





;;---------- PUTTING IT ALL TOGETHER ----------
;;
;; Modelling a hobbit.


;; A vector of maps listing various hobbit body-parts and their relative sizes
;; The body parts also lack their right-side counterparts

(def asymmetric-body-parts
  [{:name "head" :size 3}
   {:name "left-eye" :size 1}
   {:name "left-ear" :size 1}
   {:name "mouth" :size 1}
   {:name "mose" :size 1}
   {:name "neck" :size 2}
   {:name "left-shoulder" :size 3}
   {:name "left-upper-arm" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "left-forearm" :size 3}
   {:name "abdomen" :size 6}
   {:name "left-kidney" :size 1}
   {:name "left-hand" :size 2}
   {:name "left-knee" :size 2}
   {:name "left-thigh" :size 4}
   {:name "left-lower-leg" :size 3}
   {:name "left-achilles" :size 1}
   {:name "left-foot" :size 2}])


;; function to ...
(defn match-body-part
  [left-body-part]
  {:name (clojure.string/replace (:name left-body-part) #"^left-" "right-")
   :size (:size left-body-part)})


(defn make-parts-symmetric
  "Expects a sequence of maps, each with :name and :size keys"
  [asym-body-parts]

  (loop [remaining-asym-parts asym-body-parts final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (match-body-part part)])))))))


(make-parts-symmetric asymmetric-body-parts)





;;---------- EXAMPLE BREAKDOWN: `let` keyword ----------
;;
;; `let` binds names to values


;; binding `3` to the name `x`

(let [x 3] 
  x)

(def spy-list
  ["Napoleon Solo" "Ethan Hunt" "Evelyn Salt" "Jason Bourne"])


;; binding the last two people from `spy-list` to the name `two-spies`,
;; and then returning `two-spies`

(let [two-spies (take-last 2 spy-list)] 
     two-spies)


;; `let` also creates a new scope/context for names

(def m 100)


;; The new `m` refers to the result of multiplying 300 by the old `m` from before 
;; This new `m` is only active/alive withing the context of the `let` form.
;; Outside this `let`, m is still 100

(let [m (* 300 m)] 
  m)


;; You can use the rest parameter in `let` forms as well
;;
;; Here, `first-spy` and `other-spies` are bound to the first person in `spy-list`
;; and everyone else in `spy-list` respectively
;; The result is then returned as a vector

(let [[first-spy & other-spies] spy-list]
  [first-spy other-spies])
