package game;

import game.chars.BaseHero;
import game.chars.Party;

import java.util.ArrayList;

public class Fight {

    Party members;
    BattleField field;

    public Party getMembers() {
        return members;
    }

    public Fight(int teamSize, String [] request, String [] request1, int fieldSize) {
        field = new BattleField(fieldSize);
        this.members = new Party(teamSize, request, request1, fieldSize, field);
    }

    public void round (int step) {
        if (step == 0) {
            members.sortBySpeed();

            for (int i = 0; i < field.field.length; i++) {
                for (int j = 0; j < field.field[i].length; j++) {
                    System.out.print(field.field[i][j] + " ");
                }
                System.out.println();
            }

        } else {
            ArrayList <BaseHero> active = members.getAliveAsList();
            for (BaseHero h: active) h.step(members);

            for (int i = 0; i < field.field.length; i++) {
                for (int j = 0; j < field.field[i].length; j++) {
                    System.out.print(field.field[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
}

