package game.chars;
import game.BattleField;


public class Sniper extends Xbowman {

    public Sniper(int x, int y, String fraction, BattleField field) {
        super(12, 10, new int[]{8, 10}, 15, 9, "Sniper", 32, x, y, fraction, field);
    }

    public Sniper(int x, int y, String fraction, BattleField field, int quantity) {
        super(12, 10, new int[]{8, 10}, 15, 9, "Sniper", 32, x, y, fraction, field, quantity);
    }

}
