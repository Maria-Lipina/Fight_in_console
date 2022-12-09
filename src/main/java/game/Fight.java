package game;

import game.chars.BaseHero;
import game.chars.Party;

import java.util.ArrayList;

public class Fight {

    Party members;

    public Party getMembers() {
        return members;
    }

    public Fight(int teamSize, String [] request, String [] request1, int fieldSize) {
        this.members = new Party(teamSize, request, request1, fieldSize);
    }

    public void round (int step) {
        if (step == 0) {
            members.sortBySpeed();
        } else {
            ArrayList <BaseHero> active = members.getAlive();
            for (BaseHero h: active) h.step(members);
        }
        }
}

