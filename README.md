# Tail Call Optimization / Kotlin  -  Factorial

##Build
 [![Build Status](https://travis-ci.org/CodeNinjaResearch/tailCallFactorial.svg?branch=master)](https://travis-ci.org/CodeNinjaResearch/tailCallFactorial) [![Kotlin](https://img.shields.io/badge/Kotlin-1.0.0--beta--1038-blue.svg?plastic)](http://kotlinlang.org) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/badge.svg)](https://maven-badges.herokuapp.com/maven-central/org.eluder.coveralls/coveralls-maven-plugin/) [![Analytics](https://ga-beacon.appspot.com/UA-68658653-7
/tailCallFibonacci/readme)](https://github.com/igrigorik/ga-beacon)

![](http://i.imgur.com/H9YCv4d.png)

## Api
```kotlin
interface Factorial {
    fun method(n : Int) : Long
}
```


##Recursive
```kotlin
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
```

![](http://i.imgur.com/2xvLINw.png)


##Funtional
```kotlin
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
```

![](http://i.imgur.com/p4nBgYk.png)


##Tail Call Functional
```kotlin
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
    override tailrec fun method(n: Long) : Long {             
        fun _functional(n : Long, res: Long) :Long {          
            return if(n <= 0) res                             
            else _functional(n -1, n * res)                   
        }                                                     
        return _functional(n,1)                               
    }                                                         
}                                                             
```

![](http://i.imgur.com/AzKv99T.png)

## Iterative
```kotlin
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
```

![](http://i.imgur.com/nTMVgqM.pnghttp://i.imgur.com/p4nBgYk.png)


References:
* The Original 'Lambda Papers' by Guy Steele and Gerald Sussman, http://library.readscheme.org/page1.html
* Steven S.Munchnick (2000). Advanced Compiler Design implementation, Procedure Optimizations, 15, 461 – 470.
* John Hudson Tiner (200). Exploring the World of Mathematics: From Ancient Record Keeping to the Latest Advances in Computers. New Leaf Publishing Group. ISBN 9781614581550.
* Tail Recursive, https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/tail-recursive/
* Tail Recursion Elimination, http://www.program-transformation.org/Transform/TailRecursionElimination
* Tail call, https://en.wikipedia.org/wiki/Tail_call 
* What is difference between tail calls and tail recursion ?, http://stackoverflow.com/questions/12045299/what-is-difference-between-tail-calls-and-tail-recursion
* “Assembly Language:   Function Calls", Jennifer Rexford http://www.cs.princeton.edu/courses/archive/spr11/cos217/lectures/15AssemblyFunctions.pdf
* Tail call Optimization, http://tratt.net/laurie/blog/entries/tail_call_optimization
* Tail calls in the VM, https://blogs.oracle.com/jrose/entry/tail_calls_in_the_vm
* Introduce Tail Call in Kotin, http://blog.jetbrains.com/kotlin/2013/12/m6-2-available/
* [Refactored to/from TailRecursion] - November 10, 2006 - http://c2.com/cgi/wiki?TailCallOptimization
* Pattern Matching, http://kenbarclay.blogspot.com.es/2014/04/kotlin-pattern-matching.html
