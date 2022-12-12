package game.chars;

import java.util.*;


public class Party {
    public ArrayList<BaseHero> members;
    private final ArrayList<String> fractions;

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
                case "Monk" -> team.add(new Monk(x++, y, fraction));
                case "Peasant" -> team.add(new Peasant(x++, y, fraction));
                case "Robber" -> team.add(new Robber(x++, y, fraction, fieldSize));
                case "Sniper" -> team.add(new Sniper(x++, y, fraction));
                case "Spearman" -> team.add(new Spearman(x++, y, fraction, fieldSize));
                case "Warlock" -> team.add(new Warlock(x++, y, fraction));
                case "Xbowman" -> team.add(new Xbowman(x++, y, fraction));
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
        Comparator<BaseHero> comp = (h1, h2) -> Integer.compare(h1.speed, h2.speed);
        members.sort(comp.reversed());
    }

    public ArrayList<BaseHero> getAliveAsList() {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (!h.status.equals("dead")) res.add(h);
        }
        return res;
    }


    private Party(Party whole) {
        this.fractions = whole.fractions;
        this.members = whole.getAliveAsList();
    }

    public Party getAliveAsParty() {
        return new Party(this);
    }

    public ArrayList<BaseHero> getAll() {
        return members;
    }

    public ArrayList<BaseHero> getByFraction(String fraction, boolean ally) {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (!ally) {
                if (!h.fraction.equals(fraction)) res.add(h);
            }
            if (ally && h.fraction.equals(fraction)) res.add(h);
        }
        return res;
    }

}