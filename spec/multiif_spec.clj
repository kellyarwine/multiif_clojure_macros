(ns multiif-spec
  (:require [speclj.core :refer :all]
            [multiif :refer :all]))

(describe "multiif"
  (it "returns nothing when a truthy test is input and only the consequent is input"
    (should= nil (multi-if (> 1 3) :then "consequent")))

  (it "returns nothing when a truthy test is input and only the alternative is input"
    (should= nil (multi-if (> 3 1) :else "alternative")))

  (it "returns nothing when the consequent/alternative data is not input"
    (should= nil (multi-if (> 3 1))))

  (it "returns nothing when a truthy test is input and the consequent key is input but no consequent value is input"
    (should= nil (multi-if (> 3 1) :then nil)))

  (it "returns nothing when a falsey test is input and the alternative key is input but no alternative value is input"
    (should= nil (multi-if (> 1 3) :else nil)))

  (it "returns the consequent when a truthy test is input and no alternative is input"
    (should= "consequent" (multi-if (> 3 1) :then "consequent")))

  (it "returns the alternative when a falsey test is input and no consequent is input"
    (should= "alternative" (multi-if (> 1 3) :else "alternative")))

  (it "returns the consequent when a truthy test is input and the consequent is not a function"
    (should= "consequent" (multi-if (> 3 1) :then "consequent" :else "alternative")))

  (it "returns the alternative when a falsey test is input and the alternative is not a function"
    (should= "alternative" (multi-if (> 1 3) :then "consequent" :else "alternative")))

  (it "returns the result of the consequent function when a truthy test is input and the consequent is a function"
    (should= "consequent" (multi-if (> 3 1) :then (str "consequen" \t) :else (str "alternativ" \e))))

  (it "returns the result of the alternative function when a falsey test is input and the alternative is a function"
    (should= "alternative" (multi-if (> 1 3) :then (str "consequen" \t) :else (str "alternativ" \e))))
)


