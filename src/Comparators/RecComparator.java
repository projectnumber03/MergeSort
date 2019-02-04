package Comparators;

import Model.Record;

import java.util.Comparator;

public class RecComparator implements Comparator<Record> {

    @Override
    public int compare(Record r1, Record r2) {
        if (r1.getIndex() > r2.getIndex()) {
            return 1;
        } else if (r1.getIndex() < r2.getIndex()) {
            return -1;
        } else return 0;
    }
}
