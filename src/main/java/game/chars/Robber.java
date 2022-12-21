package game.chars;
import game.BattleField;


public class Robber extends Spearman {
    public Robber(int x, int y, String fraction, BattleField field) {
        super(8, 3, new int[]{2,4}, 10, 6, "Robber", x, y, fraction, field);
    }

    public Robber(int x, int y, String fraction, BattleField field, int quantity) {
        super(8, 3, new int[]{2,4}, 10, 6, "Robber", x, y, fraction, field, quantity);
    }

}
