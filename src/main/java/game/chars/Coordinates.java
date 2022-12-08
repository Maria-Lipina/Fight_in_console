package game.chars;
import static java.lang.Math.pow;
import static java.lang.Math.sqrt;
import game.Main;

import java.util.ArrayList;

public class Coordinates {
    public int x, y, fieldSize;

    public Coordinates (int x, int y, int fieldSize) {
        this.x = x;
        this.y = y;
        this.fieldSize = fieldSize;
    }

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(Coordinates pos) {
        return this.x == pos.x && this.y == pos.y;
    }

    public int distance(Coordinates pos) {
        return (int) sqrt(pow((pos.x - this.x), 2) + pow((pos.y - this.y), 2));
    }

    public BaseHero findNearest(ArrayList<BaseHero> enemy) { //Здесь скорее FindNearestShoot для стрелков. Для пеших, чтобы дойти и всех обойти понадобится другой алгоритм
        double dist = this.distance(enemy.get(0).position);
        int nearestInd = 0;
        for (int i = 1; i < enemy.size(); i++) {
            if (this.distance(enemy.get(i).position) < dist && !(enemy.get(i).status.equals("dead"))) {
                nearestInd = i;
            }
        }
        return enemy.get(nearestInd);
    }

    public boolean isValid(Coordinates pos, ArrayList<BaseHero> party) {
        for (BaseHero h : party) {
            if ((h.position.isSame(pos))
                    || (h.position.x >= fieldSize) || (h.position.y >= fieldSize))
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "{" + x + ", " + y + "}";
    }
}

//здесь должен быть метод следующего шага, потому что жто смена координат. Или метод смены координат, который будет в себе проверять на границы поля, к которому будет обращаться в мэйне
