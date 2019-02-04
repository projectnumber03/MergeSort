package Main;

import Utils.CsvCollector;
import Utils.CsvSplitter;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new CsvSplitter("source.csv").splitAndSort();
        new CsvCollector("temp", "finalfile.csv").collect();
    }
}
