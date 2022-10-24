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
