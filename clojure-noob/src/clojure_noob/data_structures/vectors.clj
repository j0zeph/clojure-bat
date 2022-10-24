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
