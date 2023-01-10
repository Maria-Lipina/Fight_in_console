package game.chars;
import game.BattleField;


public class Robber extends Spearman {

    /**
     * см. родительский класс {@link game.chars.Spearman#Spearman(int, int, String, BattleField) Spearman}.
     */
    public Robber(int x, int y, String fraction, BattleField field) {
        super(8, 3, new int[]{2,4}, 10, 6, "Robber", x, y, fraction, field);
    }

    /**
     * см. родительский класс {@link game.chars.Spearman#Spearman(int, int, String, BattleField, int) Spearman}.
     */
    public Robber(int x, int y, String fraction, BattleField field, int quantity) {
        super(8, 3, new int[]{2,4}, 10, 6, "Robber", x, y, fraction, field, quantity);
    }

}
