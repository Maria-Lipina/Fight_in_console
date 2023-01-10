package game.chars;

import game.BattleField;


public class Warlock extends Monk {

    /**
     * см. родительский класс {@link game.chars.Monk#Monk(int, int, String, BattleField) Monk}
     */
    public Warlock(int x, int y, String fraction, BattleField field) {
        super(17, 12, new int[]{-5, -5}, 30, 9, "Warlock", x, y, fraction, field);
    }

    /**
     * см. родительский класс {@link game.chars.Monk#Monk(int, int, String, BattleField, int) Monk}
     */
    public Warlock(int x, int y, String fraction, BattleField field, int quantity) {
        super(17, 12, new int[]{-5, -5}, 30, 9, "Warlock", x, y, fraction, field, quantity);
    }

}
