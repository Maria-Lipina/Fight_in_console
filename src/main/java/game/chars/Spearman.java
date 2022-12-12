package game.chars;

import java.util.ArrayList;

public class Spearman extends BaseHero {

    private int fieldSize;
    public Spearman(int x, int y, String fraction, int fieldSize) {
        super(4, 5,  new int[]{1,3}, 10, 4,  "Spearman", x, y, fraction);
        this.fieldSize = fieldSize;
    }

    public Spearman(int attack, int protection, int[] damage, int health, int speed,
                    String name, int x, int y, String fraction, int fieldSize) {
        super(attack, protection, damage, health, speed, name, x, y, fraction);
        this.fieldSize = fieldSize;
    }

    @Override
    public void step(Party party) {

        target = position.findNearest(party.getAliveAsParty().getByFraction(fraction, false));
        int x = position.x;
        int y = position.y;

        if (position.distance(target.position) == (int) Math.sqrt(2)) {
            damageValue = super.damageValue(target);
            target.damage(damageValue);
            return;
        }
        if (target.position.x < position.x && position.isValid(new Coordinates(--x, y, fieldSize), party.getAll())) {
            position.x++;
            damageValue = 0;
            return;
        }
        if (target.position.x > position.x && position.isValid(new Coordinates(++x, y, fieldSize), party.getAll())) {
            position.x++;
            damageValue = 0;
            return;
        }
        if (target.position.y < position.y && position.isValid(new Coordinates(x, --y, fieldSize), party.getAll())) {
            position.y--;
            damageValue = 0;
            return;
        }
        if (target.position.y > position.y && position.isValid(new Coordinates(x, ++y, fieldSize), party.getAll())) {
            position.y++;
            damageValue = 0;
        }
    }

}
