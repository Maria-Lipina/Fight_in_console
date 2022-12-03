package game.chars;
import java.util.ArrayList;

public class Peasant extends BaseHero {

    public Peasant(ArrayList<BaseHero> myParty, int x, int y) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", myParty, x, y);
    }

    @Override
    public void step(ArrayList<BaseHero> party, int fieldSize) {
        if (this.status.equals("dead")) return;
        if (this.status.equals("used")) this.status = "stand";
    }
}
