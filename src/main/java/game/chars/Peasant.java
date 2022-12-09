package game.chars;
import java.util.ArrayList;

public class Peasant extends BaseHero {

    public Peasant(int x, int y, String fraction) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", x, y, fraction);
    }

    @Override
    public void step(Party party) {
        if (status.equals("used")) status = "stand";
    }
}
