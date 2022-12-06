package game.chars;
import java.util.ArrayList;

public class Peasant extends BaseHero {

    public Peasant(ArrayList<BaseHero> myParty, int x, int y, String side) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", myParty, x, y, side);
    }

    @Override
    public void step(ArrayList<BaseHero> party) {
        if (status.equals("dead")) return;
        if (status.equals("used")) status = "stand";
        super.logIt(this, 0);
    }
}
