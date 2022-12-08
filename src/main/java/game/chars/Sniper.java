package game.chars;
import java.util.ArrayList;

public class Sniper extends Xbowman {

    public Sniper(ArrayList<BaseHero> myParty, int x, int y, String fraction) {
        super(12, 10, new int[]{8, 10}, 15, 9, "Sniper", 32, myParty, x, y, fraction);
    }

}
