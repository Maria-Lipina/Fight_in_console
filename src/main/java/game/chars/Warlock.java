package game.chars;

import game.BattleField;

import java.util.ArrayList;

public class Warlock extends Monk {

    public Warlock(int x, int y, String fraction, BattleField field) {
        super(17, 12, new int[]{-5, -5}, 30, 9, "Warlock", x, y, fraction, field);
    }

    public Warlock(int x, int y, String fraction, BattleField field, int quantity) {
        super(17, 12, new int[]{-5, -5}, 30, 9, "Warlock", x, y, fraction, field, quantity);
    }

}
