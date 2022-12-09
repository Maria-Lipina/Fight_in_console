package game;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {

        int step = 0;
        //TODO: в перспективе эти поля можно запрашивать у пользователя
        int teamSize = 10;
        int fieldSize = 10;
        String [] request = new String [] {"Light", "Peasant", "Robber", "Sniper", "Warlock"};
        String [] request1 = new String [] {"Dark", "Peasant", "Spearman", "Xbowman", "Monk"};

        Fight fight = new Fight(teamSize, request, request1, fieldSize);
        Logger lg = new Logger(fight.getMembers());
        ConsoleView view = new ConsoleView(teamSize, fieldSize, fight.getMembers());

        do {
            fight.round(step);
            lg.printDefault(step);
            System.out.println(view.show(step));
            step++;

        } while ((char) System.in.read() != 'Q');

    }
}