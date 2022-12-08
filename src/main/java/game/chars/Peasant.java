package game.chars;
import java.util.ArrayList;

public class Peasant extends BaseHero {

    public Peasant(ArrayList<BaseHero> myParty, int x, int y, String fraction) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", myParty, x, y, fraction);
    }

    @Override
    public void step(Party party) { //TODO: добавить крестьянам возможность отхода от ближайшего врага. Если пешие дамагеры идут атаковать, то крестьяне будут только бегать по полю боя
        if (status.equals("dead")) return;
        if (status.equals("used")) status = "stand";
    }
}
