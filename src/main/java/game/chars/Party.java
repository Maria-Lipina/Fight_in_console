package game.chars;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Consumer;

public class Party {
    private ArrayList<BaseHero> members;
    private ArrayList<String> fractions;

    public Party(int teamSize, String [] request, String [] request1, int fieldSize) {
        fractions = new ArrayList<>();
        fractions.add(request[0]);
        fractions.add(request1[0]);
        members = this.makeRandomly(teamSize, request, 0, 0, request[0], fieldSize);
        members.addAll(this.makeRandomly(teamSize, request1, 0, fieldSize-1, request1[0], fieldSize));
    }

    public String getFraction(int index) {
        return fractions.get(index);
    }

    private ArrayList<BaseHero> makeRandomly(int teamCount, String [] request, int x, int y, String fraction, int fieldSize) {
        ArrayList<BaseHero> team = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < teamCount; i++) {
            switch (request[r.nextInt(1, request.length)]) {
                case "Monk" -> team.add(new Monk(team, x++, y, fraction));
                case "Peasant" -> team.add(new Peasant(team, x++, y, fraction));
                case "Robber" -> team.add(new Robber(team, x++, y, fraction, fieldSize));
                case "Sniper" -> team.add(new Sniper(team, x++, y, fraction));
                case "Spearman" -> team.add(new Spearman(team, x++, y, fraction, fieldSize));
                case "Warlock" -> team.add(new Warlock(team, x++, y, fraction));
                case "Xbowman" -> team.add(new Xbowman(team, x++, y, fraction));
            }
        }
        return team;
    }

    public BaseHero get(int index) {
        return members.get(index);
    }
    public int size() {
        return members.size();
    }

    public void sortBySpeed() {
        Comparator<BaseHero> comp = new Comparator<>() {
            @Override
            public int compare(BaseHero h1, BaseHero h2) {
                return Integer.compare(h1.speed, h2.speed);
            }
        };
        members.sort(comp.reversed());
    }

    public ArrayList<BaseHero> getAlive() {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (!h.status.equals("dead")) res.add(h);
        }
        return res;
    }

    public ArrayList<BaseHero> getByFraction(String fraction, boolean ally) {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (h.fraction.equals(fraction) && ally) res.add(h);
            if (!(h.fraction.equals(fraction) && ally)) res.add(h);
        }
        return res;
    }

//    Возможно, как раз то, что нужно, чтобы запускать ходы, но с другой стороны зачем усложнять ещё больше?
//    @Override
//    public void forEachRemaining(Consumer<? super BaseHero> action) {
//        Iterator.super.forEachRemaining(action);
}

