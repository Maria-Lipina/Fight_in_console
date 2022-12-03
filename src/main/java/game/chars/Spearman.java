package game.chars;
import java.util.ArrayList;

public class Spearman extends BaseHero {
    public Spearman(ArrayList<BaseHero> myParty, int x, int y) {
        super(4, 5,  new int[]{1,3}, 10, 4,  "Spearman", myParty, x, y);
    }

    public Spearman(int attack, int protection, int[] damage, double health, int speed, String name, ArrayList<BaseHero> myParty, int x, int y) {
        super(attack, protection, damage, health, speed, name, myParty, x, y);
    }

    @Override
    public void step(ArrayList<BaseHero> enemy, int fieldSize) {
        if (status.equals("dead")) return;
        BaseHero target = position.findNearest(enemy);
        if (position.distance(target.position) <= Math.sqrt(2)) {target.damage(this.getDamage()[1]); return;}
        if (target.position.x < position.x && this.isValidPos(new Coordinates(--position.x, position.y), fieldSize)) {
            --position.x;
            return;
        }
        if (target.position.x > position.x && this.isValidPos(new Coordinates(++position.x, position.y), fieldSize)) {
            ++position.x;
            return;
        }
        if (target.position.y < position.y && this.isValidPos(new Coordinates(position.x, --position.y), fieldSize)) {
            --position.y;
            return;
        }
        if (target.position.y > position.y && this.isValidPos(new Coordinates(position.x, ++position.y), fieldSize)) {
            ++position.y;
        }

    }
    //Это должно быть в Coordinates
    private boolean isValidPos(Coordinates pos, int fieldSize) {
        for (BaseHero h : this.getMyParty()) {
            if ((h.position.isSame(pos))
                    || (h.position.x >= fieldSize) || (h.position.y >= fieldSize))
                return false;
        }
        return true;
    }
}
