package Utils;

import Model.Record;

import java.io.*;
import java.util.ArrayList;

public class RecordWriter {
    int fileIndex;

    public RecordWriter() {
        this.fileIndex = 0;
        createTempDir();
    }

    void write(ArrayList<Record> linesToWrite) throws IOException {
        File file = new File("temp/" + fileIndex + ".csv");
        fileIndex++;
        BufferedWriter bw = new BufferedWriter(new FileWriter(file));
        for (Record rcrd : linesToWrite) {
            if(linesToWrite.indexOf(rcrd) != linesToWrite.size() - 1){
                bw.write(rcrd.getValue() + "\n");
            } else bw.write(rcrd.getValue());
        }
        bw.flush();
        bw.close();
    }

    private void createTempDir(){
        try{
            File temp = new File("temp");
            temp.mkdir();
        }catch (SecurityException se){
            System.out.print("Временная директория уже существует");
        }
    }
}
