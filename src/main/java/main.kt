
import api.Factorial
import org.jfree.chart.JFreeChart
import src.Recursive
import src.TailCallFunctional
import src.util.PlotK
import java.awt.EventQueue
import kotlin.concurrent.thread

/**
 * Created by vicboma on 02/11/15.
 *
 */
fun main(args: Array<String>) {
    val rangeSequence = 65
    val namePlot = "Factorial"
    val plot = PlotK(namePlot, rangeSequence)
    val listOfFactorial = listFactorial()
    execute(listOfFactorial, rangeSequence,plot)
}

private fun <T>execute(factorial: T, sequence: Long): Long where T: Factorial = factorial.method(sequence)

private fun listFactorial() = listOf(Recursive(),TailCallFunctional())

private fun execute<T>(listFactorial: List<T>, rangeSequence: Int, plot : PlotK) where T: Factorial {

    for(factorial in listFactorial) {
        thread{
            for (sequence in 0..rangeSequence) {
                Thread.sleep(50)
                val execute = calcule(factorial, sequence)
                draw(execute, factorial, plot, sequence)
            }
        }
    }
}

private fun <T> calcule(factorial: T, sequence: Int): Long where T : Factorial {
    val timeInit = System.nanoTime()
    val execute = execute(factorial, sequence.toLong())
    val timeEnd = System.nanoTime() - timeInit
    println("${factorial.javaClass.canonicalName} ${execute}")
    return execute
}

private fun <T> draw(execute: Long, factorial: T, plot: PlotK, sequence: Int) where T : Factorial {
    EventQueue.invokeAndWait {
        addValueDataSet(factorial, plot, sequence, execute)
        drawPlot(plot.chart, sequence)
    }
}

private fun addValueDataSet<T>(factorial: T, plot: PlotK, sequence: Int, value: Long) where T : api.Factorial {
    plot.dataset.addValue(value, factorial.javaClass.canonicalName, sequence)
}

private fun drawPlot(plot : JFreeChart, sequence: Int) {
    plot.setTitle("Factorial T($sequence)")
}



















