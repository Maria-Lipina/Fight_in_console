package game.chars;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.function.Predicate;

public class Team {
//    public static ArrayList<BaseHero> make(int teamCount, String [] request, int x, int y, String fraction) { //x и y (это по-хорошему должно быть в документации) - это начальные значения, от которых начинается отсчет
//        ArrayList<BaseHero> team = new ArrayList<>();
//        Random r = new Random();
//        for (int i = 0; i < teamCount; i++) {
//            switch (request[r.nextInt(request.length)]) {
//                case "Monk" -> team.add(new Monk(team, x++, y, fraction));
//                case "Peasant" -> team.add(new Peasant(team, x++, y, fraction));
//                case "Robber" -> team.add(new Robber(team, x++, y, fraction));
//                case "Sniper" -> team.add(new Sniper(team, x++, y, fraction));
//                case "Spearman" -> team.add(new Spearman(team, x++, y, fraction));
//                case "Warlock" -> team.add(new Warlock(team, x++, y, fraction));
//                case "Xbowman" -> team.add(new Xbowman(team, x++, y, fraction));
//            }
//        }
//        return team;
//    }
//
//    public static void sortBySpeed(ArrayList<BaseHero> team) {
//        Comparator<BaseHero> comp = new Comparator<>() {
//            @Override
//            public int compare(BaseHero h1, BaseHero h2) {
//                return Integer.compare(h1.speed, h2.speed);
//            }
//        };
//        team.sort(comp.reversed());
//    }

}
