package data;

import java.util.List;

public class DataCleaner {
    public static void fillNAColumnsWithMean(List<List<String>> data) {
        // Calculate mean for each column and fill "N/A" values with mean
        for (int col = 0; col < data.get(0).size(); col++) {
            double sum = 0.0;
            int count = 0;
            for (List<String> row : data) {
                String value = row.get(col);
                if (!value.equals("N/A")) {
                    try {
                        sum += Double.parseDouble(value);
                        count++;
                    } catch (NumberFormatException e) {
                        // Skip non-numeric values
                    }
                }
            }
            double mean = sum / count;
            for (List<String> row : data) {
                String value = row.get(col);
                if (value.equals("N/A")) {
                    row.set(col, Double.toString(mean));
                }
            }
        }
    }

    // Method to print the dataset
    public static void printDataset(List<List<String>> data) {
        for (List<String> row : data) {
            for (String value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }
}
