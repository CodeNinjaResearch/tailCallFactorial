package src

import api.Factorial

class Iterative : Factorial {
    init {
        println("Initialize Factorial Iterative")
    }

    override fun method(n:Long) : Long {
        var res = 1L
        for(i in 1..n)
            res *= i
        return res
    }
}




