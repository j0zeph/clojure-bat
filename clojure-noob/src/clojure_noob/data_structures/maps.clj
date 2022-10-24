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