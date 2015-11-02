import api.Factorial
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import src.Functional
import src.Iterative
import src.Recursive
import src.TailCallFunctional
import java.awt.Color
import java.awt.EventQueue
import javax.swing.JFrame
import kotlin.concurrent.thread

/**
 * Created by vicboma on 02/11/15.
 */
fun main(args: Array<String>) {
    var dataset = DefaultCategoryDataset();
    val rangeSequence = 100
    val plot = configurePlot(dataset,rangeSequence)
    val listOfFibo = listFactorial()
    execute(listOfFibo, rangeSequence,plot,dataset)
}

private fun <T>execute(fibonacci: T, sequence: Long): Long where T: Factorial  = fibonacci.method(sequence)

private fun listFactorial() = listOf(Recursive(),Iterative(), Functional(), TailCallFunctional())

private fun <T> execute(listFact: List<T>, rangeSequence: Int, plot : JFreeChart,dataset: DefaultCategoryDataset) where T : Factorial{
    val max = 10000L
    for(fact in listFact) {
        thread{
            for (sequence in 0..rangeSequence) {
                val timeInit = System.nanoTime()
                execute(fact, sequence.toLong())
                val timeElapsed = System.nanoTime() - timeInit
                var timeEnd = timeElapsed
                if(timeElapsed > max)
                    timeEnd = max

                drawAndUpdatePlot(plot, dataset, fact, sequence, timeEnd.toDouble())
            }
        }
    }
}

private fun <T> drawAndUpdatePlot(plot : JFreeChart,dataset: DefaultCategoryDataset, fact: T, sequence: Int, timeEnd: Double) where T : Factorial{
    EventQueue.invokeAndWait {
        plot.setTitle("Async Chart Factorial T($sequence)")
        dataset.addValue(timeEnd, fact.javaClass.canonicalName, sequence)
    }
}


private fun configurePlot(DefaultCategoryDataset : DefaultCategoryDataset, iter : Int): JFreeChart {
    val (chart, chartPanel) = configureChart(DefaultCategoryDataset, iter)

    val frame = JFrame("Factorial");
    frame.setSize(1280, 670);
    frame.setContentPane(chartPanel);
    frame.setVisible(true);

    return chart
}

private fun configureChart(DefaultCategoryDataset: DefaultCategoryDataset, iter: Int): Pair<JFreeChart, ChartPanel> {
    val chart = ChartFactory.createBarChart(
            "Async Chart Factorial T($iter)",
            "Iteration",
            "Time ns",
            DefaultCategoryDataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
    );

    chart.setBackgroundPaint(Color.white);
    val plot = chart.getCategoryPlot();
    plot.setBackgroundPaint(Color.lightGray);
    plot.setDomainGridlinePaint(Color.white);
    plot.setDomainGridlinesVisible(true);
    plot.setRangeGridlinePaint(Color.white);

    val domainAxis = plot.getDomainAxis();
    domainAxis.setCategoryLabelPositions(CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0));

    val chartPanel = ChartPanel(chart);
    return Pair(chart, chartPanel)

}
























