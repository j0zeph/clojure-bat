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
