package src

import api.Factorial

class Recursive : Factorial {
    init {
        println("Initialize Factorial Recursive")
    }

   /**
    *  fact(2)
    *  (* 2 (fact 1)))
    *  (* 2 (* 1 (fact 0))))
    *  (* 2 (* 1 1)))
    *  (* 2 1))
    *  2
    */
    override fun method(n:Long): Long {
       return when(n){
           0L -> 1L
           else ->  n * method(n-1)
       }
    }

}




