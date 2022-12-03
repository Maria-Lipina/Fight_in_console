package game;

import game.chars.BaseHero;
import game.chars.Team;

import java.util.*;
public class Turn {
    private static HashMap<Integer, List <String>> phases = new HashMap<>();

    public static void orderBySpeed() {

        if (Main.step == 0) {
            Team.sortBySpeed(Main.lightSide);
            Team.sortBySpeed(Main.darkSide);
            Main.lightSide.forEach(n -> n.step(Main.darkSide, Main.FIELD_SIZE));
            Main.darkSide.forEach(n -> n.step(Main.lightSide, Main.FIELD_SIZE));
        } else {
            Main.lightSide.forEach(n -> n.step(Main.darkSide, Main.FIELD_SIZE));
            Main.darkSide.forEach(n -> n.step(Main.lightSide, Main.FIELD_SIZE));
        }
    }

    public static void orderByClass() {

        if (Main.step == 0) {

            phases.put(0, List.of("Robber", "Spearman"));
            phases.put(1, List.of("Sniper", "Xbowman"));
            phases.put(2, List.of("Monk", "Warlock"));
            phases.put(3, List.of("Peasant"));

            for (int i = 0; i < phases.size(); i++) {
                steps(Main.lightSide, Main.darkSide, phases.get(i), Main.FIELD_SIZE);
            }
        } else {
            for (int i = 0; i < phases.size(); i++) {
                steps(Main.lightSide, Main.darkSide, phases.get(i), Main.FIELD_SIZE);
            }
        }
    }

    private static void steps(ArrayList<BaseHero> side1, ArrayList<BaseHero> side2, List<String> phase, int fieldSize) {
        for (BaseHero h: side1) {
            if (!(h.getStatus().equals("dead")) && phase.contains(h.getName()))
                h.step(side2, Main.FIELD_SIZE);
        }
        for (BaseHero h: side2) {
            if (!(h.getStatus().equals("dead")) && phase.contains(h.getName()))
                h.step(side1, Main.FIELD_SIZE);
        }
    }
}
