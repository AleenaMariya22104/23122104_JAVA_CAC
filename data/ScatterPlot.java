package data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public class ScatterPlot {
    public static void plotScatter(List<List<String>> data, String age, String hypertension) {
        // Extract values for x and y columns
        List<Double> xValues = extractNumericColumn(data, age);
        List<Double> yValues = extractNumericColumn(data, hypertension);

        // Create a dataset
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries series = new XYSeries("Data");
        for (int i = 0; i < xValues.size(); i++) {
            series.add(xValues.get(i), yValues.get(i));
        }
        dataset.addSeries(series);

        // Create the chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Age vs Hypertension", // chart title
                "age",    // x axis label
                "hypertension",    // y axis label
                dataset,
                PlotOrientation.VERTICAL,
                true,           // include legend
                true,           // tooltips
                false           // urls
        );

        // Display the chart
        JFrame frame = new JFrame("Scatter Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static List<Double> extractNumericColumn(List<List<String>> data, String columnName) {
        List<Double> values = new ArrayList<>();
        int columnIndex = -1;
        // Find column index
        List<String> header = data.get(0);
        for (int i = 0; i < header.size(); i++) {
            if (header.get(i).equalsIgnoreCase(columnName)) {
                columnIndex = i;
                break;
            }
        }
        if (columnIndex == -1) {
            throw new IllegalArgumentException("Column " + columnName + " not found in the dataset.");
        }
        // Extract numeric values
        for (int i = 1; i < data.size(); i++) { // start from index 1 to skip header
            String value = data.get(i).get(columnIndex);
            try {
                double numericValue = Double.parseDouble(value);
                values.add(numericValue);
            } catch (NumberFormatException e) {
                // Ignore non-numeric values
            }
        }
        return values;
    }
}
