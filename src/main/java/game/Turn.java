package game;

import game.chars.BaseHero;
import game.chars.Team;

import java.util.*;

import static game.Main.*;

public class Turn {
    private static HashMap<Integer, List <String>> phases = new HashMap<>();

    private static ArrayList<BaseHero> crowd = new ArrayList<>();

    public static void orderBySpeed(int step) {

        if (step == 1) {
            crowd.addAll(lightSide);
            crowd.addAll(darkSide);
//            Team.sortBySpeed(crowd);

//            Team.sortBySpeed(Main.lightSide);
//            Team.sortBySpeed(Main.darkSide);

        } else {
//            Main.lightSide.forEach(n -> n.step(Main.darkSide));
//            Main.darkSide.forEach(n -> n.step(Main.lightSide));
            for (BaseHero h: crowd) {
                if (h.getFraction().equals("Light")) {
                    h.step(darkSide);
//                    lg.add(new String[] {
//                            Integer.toString(Main.step), h.getSide(), h.getName()+h.getId(), h.getPosition().toString()
//                    });
                }
                if (h.getFraction().equals("Dark")) {
                    h.step(lightSide);
//                    lg.add(new String[] {
//                            Integer.toString(Main.step), h.getSide(), h.getName()+h.getId(), h.getPosition().toString()
//                    });
                }
            }
        }
    }

    public static void orderByClass(int step) {

        if (step == 1) {

            phases.put(0, List.of("Robber", "Spearman"));
            phases.put(1, List.of("Sniper", "Xbowman"));
            phases.put(2, List.of("Monk", "Warlock"));
            phases.put(3, List.of("Peasant"));

        } else {
            for (int i = 0; i < phases.size(); i++) {
                steps(Main.lightSide, Main.darkSide, phases.get(i));
            }
        }
    }

    private static void steps(ArrayList<BaseHero> side1, ArrayList<BaseHero> side2, List<String> phase) {
        for (BaseHero h: side1) {
            if (!(h.getStatus().equals("dead")) && phase.contains(h.getName()))
                h.step(side2);

        }
        for (BaseHero h: side2) {
            if (!(h.getStatus().equals("dead")) && phase.contains(h.getName()))
                h.step(side1);
        }
    }

}
