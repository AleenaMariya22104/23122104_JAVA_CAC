import data.*;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> data = null;

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Read CSV file");
            System.out.println("2. Clean Dataset");
            System.out.println("3. Print Dataset");
            System.out.println("4. Plot Scatter");
            System.out.println("5. Plot Line");
            System.out.println("6. Plot Histogram");
            System.out.println("7. Plot Box Plot");
            System.out.println("8. Plot Pie Chart");
            System.out.println("9. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    try {
                        data = CSVReader.readCSV("healthcare.csv");
                        System.out.println("Dataset read from CSV:");
                        CSVReader.printDataset(data);
                    } catch (FileNotFoundException e) {
                        System.out.println("File not found: " + e.getMessage());
                    }
                    break;
                case 2:
                    if (data != null) {
                        DataCleaner.fillNAColumnsWithMean(data);
                        System.out.println("Cleaned Dataset:");
                        CSVReader.printDataset(data);
                    } else {
                        System.out.println("Please read the dataset first (Option 1).");
                    }
                    break;
                case 3:
                    if (data != null) {
                        DataCleaner.printDataset(data);
                    } else {
                        System.out.println("No data to print. Please read CSV first.");
                    }
                    break;
                case 4:
                    if (data != null) {
                        ScatterPlot.plotScatter(data, "age", "hypertension");
                    } else {
                        System.out.println("No data to plot. Please read CSV first.");
                    }
                    break;
                case 5:
                    if (data != null) {
                        LinePlot.plotLine(data, "age", "bmi");
                    } else {
                        System.out.println("No data to plot. Please read CSV first.");
                    }
                    break;
                case 6:
                    if (data != null) {
                        HistogramPlot.plotAgeHistogram(data);
                    } else {
                        System.out.println("No data to plot. Please read CSV first.");
                    }
                    break;
                    
                case 7:
                    if (data != null) {
                        BoxPlotter.plotBox(data, "age", "avg_glucose_level");
                    } else {
                        System.out.println("No data to plot. Please read CSV first.");
                    }
                    break;
                case 8:
                    if (data != null) {
                        PiePlotter.plotPie(data, "smoking_status");
                    } else {
                        System.out.println("No data to plot. Please read CSV first.");
                    }
                    break;
                case 9:
                    System.out.println("Exiting!!!");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
        }
    }
}
