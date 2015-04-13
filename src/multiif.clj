(ns multiif)

(defmacro index-of [search-value coll]
  `(first
    (keep-indexed #(if (= ~search-value %2) %1) ~coll)))

(defmacro index-of-value [key coll]
  `(inc (index-of ~key ~coll)))

(defmacro take-value-for [key coll]
  `(if
    (> (count ~coll) (index-of-value ~key ~coll))
    (nth ~coll (index-of-value ~key ~coll))
    nil))

(defmacro value-exists? [key coll]
  `(not-any? #(= % (take-value-for ~key ~coll)) '(:then :else nil)))

(defmacro key-exists? [key coll]
  `(not= nil (index-of ~key ~coll)))

(defmacro key-value-exists? [key coll]
  `(and
    (key-exists? ~key ~coll)
    (value-exists? ~key ~coll)))

(defmacro true-map [then-else-data]
  `(if
    (key-value-exists? :then ~then-else-data)
    { :true (take-value-for :then ~then-else-data) }
    { :true nil }))

(defmacro false-map [then-else-data]
  `(if
    (key-value-exists? :else ~then-else-data)
    { :false (take-value-for :else ~then-else-data) }
    { :false nil }))

(defmacro build-true-false-map [then-else-data]
  `(apply merge (true-map ~then-else-data) (false-map ~then-else-data)))

(defmacro evaluate [if-test]
  `(keyword(str ~if-test)))

(defmacro multi-if [if-test & then-else-data]
  `(eval ((evaluate ~if-test) (build-true-false-map '~then-else-data))))
