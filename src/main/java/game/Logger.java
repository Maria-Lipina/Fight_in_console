package game;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class Logger {
    private FileWriter fw;
    private ArrayList<String[]> table;

    public Logger(String filepath, String[] header) throws IOException {
        fw = new FileWriter(filepath, false);
        fw.append(String.join(";", header));
        table = new ArrayList<>();
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
