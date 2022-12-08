package game.chars;
import java.util.ArrayList;

public class Robber extends Spearman {
    public Robber(ArrayList<BaseHero> myParty, int x, int y, String fraction, int fieldSize) {
        super(8, 3, new int[]{2,4}, 10, 6, "Robber", myParty, x, y, fraction, fieldSize);
    }

}
