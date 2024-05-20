package data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.HistogramDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;


public class HistogramPlot {
    public static void plotAgeHistogram(List<List<String>> data) {
        // Extract age values from data
        List<Double> ageValues = extractNumericColumn(data, "age");

        // Create a dataset
        HistogramDataset dataset = new HistogramDataset();
        double[] ageData = new double[ageValues.size()];
        for (int i = 0; i < ageValues.size(); i++) {
            ageData[i] = ageValues.get(i);
        }
        dataset.addSeries("Age", ageData, 10); // 10 bins

        // Create the chart
        JFreeChart chart = ChartFactory.createHistogram(
        "Age vs Heart Disease", // chart title
        "age",              // x axis label
        "heart_disease",        // y axis label
        dataset,
        PlotOrientation.VERTICAL,
        true,
        true,
        false
);


        // Display the chart
        JFrame frame = new JFrame("Histogram Plot");
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
