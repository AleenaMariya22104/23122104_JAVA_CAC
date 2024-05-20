package data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.statistics.BoxAndWhiskerCategoryDataset;
import org.jfree.data.statistics.DefaultBoxAndWhiskerCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoxPlotter {

    public static void plotBox(List<List<String>> data, String age, String avg_glucose_level) {
        // Extract values for x and y columns
        List<String> xValues = extractColumn(data, age);
        List<Double> yValues = extractNumericColumn(data, avg_glucose_level);

        // Create a dataset
        BoxAndWhiskerCategoryDataset dataset = createDataset(xValues, yValues);

        // Create the chart
        JFreeChart chart = ChartFactory.createBoxAndWhiskerChart(
                "Age vs Average Glucose Level",        // chart title
                "age",      // x axis label
                "avg_glucose_level",      // y axis label
                dataset,          // data
                true              // include legend
        );

        // Display the chart
        JFrame frame = new JFrame("Box Plot");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static BoxAndWhiskerCategoryDataset createDataset(List<String> xValues, List<Double> yValues) {
        DefaultBoxAndWhiskerCategoryDataset dataset = new DefaultBoxAndWhiskerCategoryDataset();

        for (int i = 0; i < xValues.size(); i++) {
            dataset.add(yValues.subList(i, i + 1), "", xValues.get(i));
        }

        return dataset;
    }

    private static List<String> extractColumn(List<List<String>> data, String columnName) {
        List<String> values = new ArrayList<>();
        int columnIndex = -1;
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
        for (int i = 1; i < data.size(); i++) {
            values.add(data.get(i).get(columnIndex));
        }
        return values;
    }

    private static List<Double> extractNumericColumn(List<List<String>> data, String columnName) {
        List<Double> values = new ArrayList<>();
        int columnIndex = -1;
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
        for (int i = 1; i < data.size(); i++) {
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
