package game;

import game.chars.BaseHero;
import game.chars.Team;

import java.io.IOException;
import java.util.ArrayList;
public class Main {

    public static final int TEAM_SIZE = 1;
    public static final int FIELD_SIZE = 10;

    public static int step = 1;
    static ArrayList<BaseHero> lightSide;
    static ArrayList<BaseHero> darkSide;

    public static void main(String[] args) throws IOException { //кнопка вкл для всей игры

//        String [] request = new String [] {"Peasant", "Robber", "Sniper", "Warlock"};
//        String [] request1 = new String [] {"Peasant", "Spearman", "Xbowman", "Monk"};
        String [] request = new String [] {"Robber"};
        String [] request1 = new String [] {"Spearman"};
        Main.lightSide = Team.make(TEAM_SIZE, request, 0, 0);
        Main.darkSide = Team.make(TEAM_SIZE, request1, 0, Main.FIELD_SIZE-1);

        do {
            Turn.orderBySpeed();
            System.out.println(ConsoleView.field());
            step++;
            System.out.println("lightSide" + Main.lightSide.get(0).position.toString() + " "
                    + Main.lightSide.get(0).position.distance(Main.darkSide.get(0).position));
            System.out.println("darkSide" + Main.darkSide.get(0).position.toString() + " "
                    + Main.darkSide.get(0).position.distance(Main.lightSide.get(0).position));

        } while ((char) System.in.read() != 'Q');


    }
}