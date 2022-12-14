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


;;lists can contain different types of values (it is heterogenous)
(list 1 "two" {3 4})


;;using `conj` to add elements at the `beginning` of the list
(conj '(1 2 3) 4)
