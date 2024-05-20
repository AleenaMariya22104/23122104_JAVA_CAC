package data;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PiePlotter {

    public static void plotPie(List<List<String>> data, String smoking_status) {
        // Create a dataset for the pie chart
        DefaultPieDataset dataset = createDataset(data, smoking_status);

        // Create the chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Smoking Status",  // chart title
                dataset,      // dataset
                true,         // include legend
                true,         // tooltips
                false         // urls
        );

        // Display the chart
        JFrame frame = new JFrame("Pie Chart");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(600, 400));
        frame.setContentPane(chartPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private static DefaultPieDataset createDataset(List<List<String>> data, String columnName) {
        DefaultPieDataset dataset = new DefaultPieDataset();
    
        // Count occurrences of each category in the specified column
        int columnIndex = getColumnIndex(data, columnName);
        for (int i = 1; i < data.size(); i++) {
            String category = data.get(i).get(columnIndex);
            if (dataset.getIndex(category) < 0) {
                dataset.setValue(category, 1); // Initialize count for new category
            } else {
                int count = dataset.getValue(category).intValue();
                dataset.setValue(category, count + 1); // Increment count for existing category
            }
        }
    
        return dataset;
    }
    

    private static int getColumnIndex(List<List<String>> data, String columnName) {
        List<String> header = data.get(0);
        for (int i = 0; i < header.size(); i++) {
            if (header.get(i).equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Column " + columnName + " not found in the dataset.");
    }
}
