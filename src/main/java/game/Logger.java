package game;

import game.chars.BaseHero;
import game.chars.Party;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Logger {
    private FileWriter fw;
    private StringBuilder sb;
    private ArrayList<String[]> table;

    private Party members;

    String[] header;

    public Logger(Party members) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd_HH.mm.ss");
        sb = new StringBuilder("D:\\IT\\Java\\Fight in console\\log\\");
        sb.append(dtf.format(LocalDateTime.now()));
        sb.append(".csv");

        fw = new FileWriter(sb.toString(), false);
        sb.delete(0, sb.length());

        this.header = new String[]{"StepNo", "Fraction", "Me", "MyPos", "Target", "TargetPos", "Damage val", "MyStatus"};
        this.members = members;

    }
    //TODO потом попробовать обойтись без ArrayList. Через родной знакомый StringBuilder сразу в строку и в файл
        public void add (String[] data) {
            table.add(data);
        }

        public void printDefault(int step) throws IOException {
            if (step == 0) {
                for (int i = 0; i < members.size(); i++) {
                    fw.append(members.get(i).getInfo());
                    fw.append("\r\n");
                }
                fw.append(String.join(";", header));
            } else {
                // "Step No", "Side", "MyName+ID", "MyPos", "Target", "TargetPos", "Damage val", "MyStatus"
                // здесь должен быть sb.append и итератор по нужным полям BaseHero для лога
            }
            fw.flush();
        }

    public void close() throws IOException {
        fw.flush();
    }

}
