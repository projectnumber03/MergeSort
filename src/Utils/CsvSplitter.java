package Utils;

import Comparators.RecComparator;
import Model.Record;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class CsvSplitter {
    private BufferedReader br;
    private RecordWriter rw = new RecordWriter();

    public CsvSplitter(String csvFileName) throws FileNotFoundException {
        this.br = new BufferedReader(new FileReader(csvFileName));
    }

    public void splitAndSort() throws IOException {
        String line;
        int fileLinesCnt = 1;
        ArrayList<Record> list = new ArrayList<>();

        while ((line = br.readLine()) != null) {
            if (fileLinesCnt < 16000) {
                list.add(new Record(line));
                fileLinesCnt++;
            } else {
                list.add(new Record(line));
                Collections.sort(list, new RecComparator());
                rw.write(list);
                list.clear();
                fileLinesCnt = 1;
            }
        }
        Collections.sort(list, new RecComparator());
        rw.write(list);
        br.close();
    }
}
