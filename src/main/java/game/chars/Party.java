package game.chars;

import java.util.*;


public class Party {
    public ArrayList<BaseHero> members;
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

    public ArrayList<BaseHero> getAll() {
        return members;
    }

    public ArrayList<BaseHero> getByFraction(String fraction, boolean ally) {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (h.fraction.equals(fraction) && ally) res.add(h);
            if (!(h.fraction.equals(fraction) && ally)) res.add(h);
        }
        return res;
    }



    public void sortByClass() {

        HashMap<String, Integer> order = new HashMap<>();
        order.put("Robber", 0);
        order.put("Spearman", 0);
        order.put("Sniper", 1);
        order.put("Xbowman", 1);
        order.put("Monk", 2);
        order.put("Warlock", 2);
        order.put("Peasant", 3);

        mergeSort(order, members);
        }

        private void mergeSort(HashMap<String, Integer> order, ArrayList<BaseHero> sortThem) {
        int n = sortThem.size();
        if (n==1) return;
        int mid = n / 2;
            ArrayList<BaseHero> l = new ArrayList<>();
            ArrayList<BaseHero> r = new ArrayList<>();
            for (int i = 0; i < mid; i++) l.add(i, sortThem.get(i));
            for (int i = mid; i < n; i++) r.add(i-mid, sortThem.get(i));

            mergeSort(order, l);
            mergeSort(order, r);
            merge(order, members, l, r);
            }

        private void merge(HashMap<String, Integer> order,
                           ArrayList<BaseHero> members,  ArrayList<BaseHero> l, ArrayList<BaseHero> r) {
        int left = l.size();
        int right = r.size();
        int i = 0;
        int j = 0;
        int idx = 0;

        while (i < left && j < right) {
            if(order.get(l.get(i).getName()) < order.get(r.get(i).getName())) {
                if (idx < members.size()) {
                    members.set(idx++, l.get(i));
                }
                i++;
            } else {
                if (idx < members.size()) {
                    members.set(idx++, r.get(j));
                }
                j++;
            }

            for (int le = i; le < left; le++) {
                if (idx < members.size()) members.set(idx++, l.get(le));
            }
            for (int ra = j; ra < left; ra++) {
                if (idx < members.size()) members.set(idx++, r.get(ra));
            }
        }

    }

}

//На прошлом занятии делали возможность добавления группового юнита: ячейка одна, но здоровье и урон помножены на количество членов отряда. При добавлении такого юнита изменяется вид: На поле под буквой количество. Ячейка больше в ширину и в высоту на 1. Как при таком распечатывать информацию о персонажах, как разбивать стрингбилдер строку на две... хороший вопрос

