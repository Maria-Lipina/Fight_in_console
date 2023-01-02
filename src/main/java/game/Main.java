package game;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException { //TODO: да у меня тут исключения совсем не обработаны

        int step = 0;
        int teamSize = 10;
        int fieldSize = 10;
//        String [] request = new String [] {"Light", "Peasant", "Robber", "Sniper", "Warlock"};
//        String [] request1 = new String [] {"Dark", "Peasant", "Spearman", "Xbowman", "Monk"};

        String [] reqBalanced = new String [] {"Light", "Peasant", "Robber", "Sniper", "Warlock", "Peasant", "Robber", "Sniper", "Warlock", "Robber", "Warlock"};
        int [] reqBalancedQuantity = {            0,       50,        45,       10,        30,        60,       45,       10,       10,        40,        20};
        String [] reqBalanced1 = new String [] {"Dark", "Peasant", "Spearman", "Xbowman", "Monk", "Peasant", "Spearman", "Xbowman", "Monk", "Spearman", "Monk"};
        int [] reqBalancedQuantity1 = {            0,       50,        45,       15,        40,        60,       45,       50,       15,        40,        30};

//        Fight fight = new Fight(teamSize, request, request1, fieldSize);
        Fight fight = new Fight(reqBalanced, reqBalanced1, reqBalancedQuantity, reqBalancedQuantity1, fieldSize);
        Logger lg = new Logger(fight.getMembers());
        ConsoleView view = new ConsoleView(fieldSize, fight.getMembers());
        System.out.println("Print Enter to begin or Q to exit");

        while (!((char) System.in.read() == 'Q') && fight.round(step)) {
            lg.printDefault(step);
            System.out.println(view.show(step));
            step++;
        }
        System.out.println(view.show(-1));

    }
}