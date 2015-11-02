package src

import org.junit.Test
import kotlin.test.assertTrue


/**
 * Created by vicboma on 02/11/15.
 */
class TailCallFunctionalTest {

    val expected = arrayOf(1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800, 87178291200, 1307674368000, 20922789888000, 355687428096000, 6402373705728000, 121645100408832000, 2432902008176640000)

    @Test
    fun testMethod() {
        val factorial = TailCallFunctional()
        val size = expected.size - 1
        for (sequence in 0..size) {
            val result = factorial.method(sequence.toLong())
            assertTrue {
                "Fail resutl"
                result == expected.get(sequence)
            }
        }
    }
}