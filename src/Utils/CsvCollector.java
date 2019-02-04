package Utils;

import Model.Record;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class CsvCollector {
    private File[] files;
    private FileOutputStream fos;

    public CsvCollector(String tempPath, String finalCsvFilename) throws FileNotFoundException {
        this.files = new File(tempPath).listFiles();
        this.fos = new FileOutputStream(new File(finalCsvFilename));
    }


    public void collect() throws FileNotFoundException {
        ArrayList<Scanner> scaners = new ArrayList<>();
        Stack<Record> stackRecords = new Stack<>();
        Record workingRecord;
        PrintStream pw = new PrintStream(fos);

        for (int i = 0; i < files.length; i++) {
            scaners.add(new Scanner(files[i]));
        }

        for (Scanner scan : scaners) {
            stackRecords.push(new Record(scan.nextLine(), scaners.indexOf(scan)));
        }

        while (stackRecords.size() > 0) {
            Collections.sort(stackRecords, new Comparators.RecComparator().reversed());
            workingRecord = stackRecords.pop();
            pw.println(workingRecord.getValue());
            pw.flush();
            try {
                stackRecords.push(new Record(scaners.get(workingRecord.getScanIndex()).nextLine(), workingRecord.getScanIndex()));
            } catch (Exception e) {
                scaners.get(workingRecord.getScanIndex()).close();
            }
        }
        pw.close();
        clearTemp();
        System.out.println("Сборка отсортированного файла окончена");
    }

    private void clearTemp() {
        try {
            for (int i = 0; i < files.length; i++) {
                Files.deleteIfExists(Paths.get("temp/" + i + ".csv"));
            }
            File file = new File("temp");
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
