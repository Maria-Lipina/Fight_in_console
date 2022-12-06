package game.chars;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class Team {
    public static ArrayList<BaseHero> make(int teamCount, String [] request, int x, int y, String side) { //x и y (это по-хорошему должно быть в документации) - это начальные значения, от которых начинается отсчет
        ArrayList<BaseHero> team = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < teamCount; i++) {
            switch (request[r.nextInt(request.length)]) {
                case "Monk" -> team.add(new Monk(team, x++, y, side));
                case "Peasant" -> team.add(new Peasant(team, x++, y, side));
                case "Robber" -> team.add(new Robber(team, x++, y, side));
                case "Sniper" -> team.add(new Sniper(team, x++, y, side));
                case "Spearman" -> team.add(new Spearman(team, x++, y, side));
                case "Warlock" -> team.add(new Warlock(team, x++, y, side));
                case "Xbowman" -> team.add(new Xbowman(team, x++, y, side));
            }
        }
        return team;
    }

    public static void sortBySpeed(ArrayList<BaseHero> team) {
        Comparator<BaseHero> comp = new Comparator<>() {
            @Override
            public int compare(BaseHero h1, BaseHero h2) {
                return Integer.compare(h1.speed, h2.speed);
            }
        };
        team.sort(comp.reversed());
    }

}
