package game.chars;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import java.util.ArrayList;

/**
 * Хранит координаты персонажа (представитель наследного класса от {@link game.chars.BaseHero BaseHero}) как отдельный объект, с помощью внутренней логики которого персонажи ориентируются относительно друг друга, например во внутренней логике совершения хода.
 */
public class Coordinates {
    protected int x, y;

    /**
     * Сохраняет координаты персонажа на игровом поле в отдельном
     * @param x номер строки в {@link game.BattleField BattleField}
     * @param y номер столбца в {@link game.BattleField BattleField}
     */
    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return номер строки в {@link game.BattleField BattleField}
     */
    public int getX() {
        return x;
    }

    /**
     * @return номер столбца в {@link game.BattleField BattleField}
     */
    public int getY() {
        return y;
    }

    /**
     * сравнивает координаты, хранящиеся в вызывающем объекте, с координатами, переданными в параметрах (применяется при подготовке вида через {@link game.ConsoleView ConsoleView}).
     * @param pos Coordinates
     * @return true, если this.x == pos.x && this.y == pos.y.
     */
    public boolean isSame(Coordinates pos) {
        return (this.x == pos.x && this.y == pos.y);
    }


    protected int distance(Coordinates pos) {
        return (int) sqrt(pow((pos.x - this.x), 2) + pow((pos.y - this.y), 2));
    }

    protected BaseHero findNearest(ArrayList<BaseHero> enemy) {
        int dist = this.distance(enemy.get(0).position);
        int nearestInd = 0;
        for (int i = 1; i < enemy.size(); i++) {
            if (this.distance(enemy.get(i).position) < dist && !(enemy.get(i).status.equals("dead"))) {
                nearestInd = i;
            }
        }
        return enemy.get(nearestInd);
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }
}

