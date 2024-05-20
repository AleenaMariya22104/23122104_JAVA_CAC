package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSVReader {
    public static List<List<String>> readCSV(String filename) throws FileNotFoundException {
        List<List<String>> data = new ArrayList<>();
        Scanner scanner = new Scanner(new File(filename));

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            List<String> row = new ArrayList<>();
            for (String value : values) {
                row.add(value.trim()); // Trim to remove leading/trailing whitespace
            }
            data.add(row);
        }
        scanner.close();
        return data;
    }

    // Method to print the dataset
    public static void printDataset(List<List<String>> data) {
        // Print first 10 rows of the dataset
        int rowCount = 0;
        for (List<String> row : data) {
            for (String value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
            rowCount++;
            if (rowCount >= 10) break; // Stop after printing 10 rows
        }
    }
}
