package Model;

public class Record {
    private int index;
    private String value;
    int scanIndex;

    public Record(String record) {
        this.index = Integer.valueOf(record.split(";")[0]);
        this.value = record;
    }

    public Record(String record, int scanIndex) {
        this.index = Integer.valueOf(record.split(";")[0]);
        this.value = record;
        this.scanIndex = scanIndex;
    }

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    public int getScanIndex() {
        return scanIndex;
    }
}
