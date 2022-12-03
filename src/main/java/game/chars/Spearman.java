package game.chars;
import game.Main;

import java.util.ArrayList;

public class Spearman extends BaseHero {
    public Spearman(ArrayList<BaseHero> myParty, int x, int y) {
        super(4, 5,  new int[]{1,3}, 10, 4,  "Spearman", myParty, x, y);
    }

    public Spearman(int attack, int protection, int[] damage, int health, int speed, String name, ArrayList<BaseHero> myParty, int x, int y) {
        super(attack, protection, damage, health, speed, name, myParty, x, y);
    }

    @Override
    public void step(ArrayList<BaseHero> enemy) {
        if (status.equals("dead")) return;
        BaseHero target = position.findNearest(enemy);
        if (position.distance(target.position) == (int) Math.sqrt(2)) {target.damage(damage[1]); return;}
        if (target.position.x < position.x && position.isValid(new Coordinates(--position.x, position.y), myParty)) {
            --position.x;
            return;
        }
        if (target.position.x > position.x && position.isValid(new Coordinates(++position.x, position.y), myParty)) {
            ++position.x;
            return;
        }
        if (target.position.y < position.y && position.isValid(new Coordinates(position.x, --position.y), myParty)) {
            --position.y;
            return;
        }
        if (target.position.y > position.y && position.isValid(new Coordinates(position.x, ++position.y), myParty)) {
            ++position.y;
        }

    }

}
