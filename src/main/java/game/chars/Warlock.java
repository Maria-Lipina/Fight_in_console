package game.chars;

import java.util.ArrayList;

public class Warlock extends Monk {

    public Warlock(ArrayList<BaseHero> myParty, int x, int y, String fraction) {
        super(17, 12, new int[]{-5, -5}, 30, 9, "Warlock", myParty, x, y, fraction);
    }

}
