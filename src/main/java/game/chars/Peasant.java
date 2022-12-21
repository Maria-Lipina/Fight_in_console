package game.chars;
import game.BattleField;

public class Peasant extends BaseHero {

    public Peasant(int x, int y, String fraction, BattleField field) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", x, y, fraction, field);
    }

    public Peasant(int x, int y, String fraction, BattleField field, int quantity) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", x, y, fraction, field, quantity);
    }

    @Override
    public void step(Party party) {
        if (status.equals("used")) status = "stand";
    }
}
