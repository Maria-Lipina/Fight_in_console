package game.chars;

import java.util.ArrayList;

public class Spearman extends BaseHero {

    private int fieldSize;
    public Spearman(ArrayList<BaseHero> myParty, int x, int y, String fraction, int fieldSize) {
        super(4, 5,  new int[]{1,3}, 10, 4,  "Spearman", myParty, x, y, fraction);
        this.fieldSize = fieldSize;
    }

    public Spearman(int attack, int protection, int[] damage, int health, int speed,
                    String name, ArrayList<BaseHero> myParty, int x, int y, String fraction, int fieldSize) {
        super(attack, protection, damage, health, speed, name, myParty, x, y, fraction);
        this.fieldSize = fieldSize;
    }

    @Override
    public void step(Party party) {
        if (status.equals("dead")) {
            return;
        }
        BaseHero target = position.findNearest(party.getByFraction(fraction, false));
        //TODO напоминаю себе, что для пеших метод findNearest нужно переписывать. Пока они вызывают метод, который подходит стрелкам
        if (position.distance(target.position) <= (int) Math.sqrt(2)) {
            int damageValue = super.damageValue(target);
            target.damage(damageValue);
            return;
        }
        if (target.position.x < position.x && position.isValid(new Coordinates(--position.x, position.y, fieldSize), myParty)) {
            --position.x;
            return;
        }
        if (target.position.x > position.x && position.isValid(new Coordinates(++position.x, position.y, fieldSize), myParty)) {
            ++position.x;
            return;
        }
        if (target.position.y < position.y && position.isValid(new Coordinates(position.x, --position.y, fieldSize), myParty)) {
            --position.y;
            return;
        }
        if (target.position.y > position.y && position.isValid(new Coordinates(position.x, ++position.y, fieldSize), myParty)) {
            ++position.y;
        }
        //TODO: подошел и ударил! То есть снова проверка близости и расстояния
    }

}
