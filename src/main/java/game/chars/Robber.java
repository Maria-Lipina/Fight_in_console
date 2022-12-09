package game.chars;
import java.util.ArrayList;

public class Robber extends Spearman {
    public Robber(int x, int y, String fraction, int fieldSize) {
        super(8, 3, new int[]{2,4}, 10, 6, "Robber", x, y, fraction, fieldSize);
    }

}
