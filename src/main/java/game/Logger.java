package game;

import game.chars.Party;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Logger {
    private FileWriter fw;
    private StringBuilder sb;

    private Party members;

    String[] header;

    public Logger(Party members) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd_HH.mm.ss");
        sb = new StringBuilder("D:\\IT\\Java\\Fight in console\\log\\");
        sb.append(dtf.format(LocalDateTime.now()));
        sb.append(".csv");

        fw = new FileWriter(sb.toString(), false);
        sb.delete(0, sb.length());

        this.header = new String[]{"StepNo", "Fraction", "Me", "MyPos", "Target", "TargetPos", "Damage val", "TgStatus"};
        this.members = members;

    }
        public void printDefault(int step) throws IOException {
            if (step == 0) {
                for (int i = 0; i < members.size(); i++) {
                    fw.append(members.get(i).getInfo());
                    fw.append("\r\n");
                }
                fw.append(String.join(";", header));
            } else {
                for (int i = 0; i < members.getAliveAsList().size(); i++) {
                    fw.append("\r\n");
                    fw.append(Integer.toString(step));
                    fw.append(";");
                    fw.append(members.getAliveAsList().get(i).defaultLog());
                }
            }
            fw.flush();
        }

}

