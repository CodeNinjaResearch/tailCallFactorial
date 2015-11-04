package src

import api.Factorial

/**
 * Created by vicboma on 02/11/15.
 */
class TailCallFunctional : Factorial {
    init {
        println("Initialize Factorial Tail Call Functional")
    }

    /**
     *  (fact 2)
     *  (tailrec-fact 2 1)
     *  (tailrec-fact 1 2)
     *  (tailrec-fact 0 2)
     *  2
     */
    override fun method(n: Long) : Long {
        tailrec fun _functional(n : Long, res: Long) :Long {
                return if(n <= 0) res
                else _functional(n -1, n * res)
            }
        return _functional(n,1)
    }
}








