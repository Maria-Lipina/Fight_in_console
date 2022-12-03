package game;

import game.chars.BaseHero;
import game.chars.Team;

import java.io.IOException;
import java.util.ArrayList;
public class Main {

    public static final int TEAM_SIZE = 2; //так с каждой стороны по одному игроку
    public static final int FIELD_SIZE = 10;

    public static int step = 0;
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

        } while ((char) System.in.read() != 'Q');

        System.out.println((int)Math.sqrt(2));


    }
}