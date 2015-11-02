package src

import api.Factorial

/**
 * Created by vicboma on 31/10/15.
 */
class Functional : Factorial {
    init {
        println("Initialize Factorial Functional")
    }

    override fun method(n: Long) : Long {
         fun _functional(n : Long, res: Long) :Long {
            return if(n <= 0) res
            else _functional(n -1, n * res)
        }
        return _functional(n,1)
    }
}