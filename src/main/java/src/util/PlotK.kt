package src.util

import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.axis.CategoryLabelPositions
import org.jfree.chart.plot.PlotOrientation
import org.jfree.data.category.DefaultCategoryDataset
import java.awt.Color
import javax.swing.JFrame
/**
 * Created by vicboma on 04/11/15.
 */
class PlotK(val name:String, val size:Int){

    public var chart: JFreeChart;
    public var dataset : DefaultCategoryDataset;

    init{
        dataset = DefaultCategoryDataset();
        chart = configurePlot(dataset, size);
    }


    fun configurePlot(DefaultCategoryDataset : DefaultCategoryDataset, iter : Int): JFreeChart {
        val (chart, chartPanel) = configureChart(DefaultCategoryDataset, iter)

        val frame = JFrame(this.name);
        frame.setSize(1280, 670);
        frame.setContentPane(chartPanel);
        frame.setVisible(true);

        return chart
    }

    fun configureChart(DefaultCategoryDataset: DefaultCategoryDataset, iter: Int): Pair<JFreeChart, ChartPanel> {
        val chart = ChartFactory.createBarChart(
                "${this.name} T($iter)",
                "Iteration",
                "Value",
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


}