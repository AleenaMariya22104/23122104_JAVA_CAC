package data;

import java.util.List;

public class DataPrinter {
    public static void printData(List<List<String>> data) {
        for (List<String> row : data) {
            for (String value : row) {
                System.out.print(value + " ");
            }
            System.out.println();
        }
    }
}
