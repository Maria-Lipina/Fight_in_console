package game.chars;
import game.BattleField;


public class Sniper extends Xbowman {

    /**
     * см. родительский класс {@link game.chars.Xbowman#Xbowman(int, int, String, BattleField) Xbowman}.
     */
    public Sniper(int x, int y, String fraction, BattleField field) {
        super(12, 10, new int[]{8, 10}, 15, 9, "Sniper", 32, x, y, fraction, field);
    }

    /**
     * см. родительский класс {@link game.chars.Xbowman#Xbowman(int, int, String, BattleField, int) Xbowman}.
     */
    public Sniper(int x, int y, String fraction, BattleField field, int quantity) {
        super(12, 10, new int[]{8, 10}, 15, 9, "Sniper", 32, x, y, fraction, field, quantity);
    }

}
