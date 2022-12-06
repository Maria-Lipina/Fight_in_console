package game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Logger {
    private FileWriter fw;
//    private HashMap<String, String> record;
    private ArrayList<String[]> table;
    private int tableRowSize;

    public Logger(String filepath, String[] header) throws IOException {
        fw = new FileWriter(filepath, true);
        fw.append(String.join(";", header));
//        record = new HashMap<>();
        table = new ArrayList<>();
        tableRowSize = header.length;
//        for (String i: header) {
//            record.put(i, "");
//        }
    }
        //"Step No", "Side", "Hero+ID", "Target", "Damage val"
        public void add (String[] data) {
            table.add(data);
        }

        public void print() throws IOException {
            for (String[] i: table) {
                fw.append("\r\n");
                fw.append(String.join(";", i));
            }
        }

    public void close() throws IOException {
        fw.flush();
    }

}
