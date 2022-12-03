package game.chars;

import game.Main;

import java.util.ArrayList;

public class Coordinates { //Protected class, потому что клиенту он не должен быть виден
    public int x, y;

    public Coordinates (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isSame(Coordinates pos) {
        return this.x == pos.x && this.y == pos.y;
    }

    public double distance(Coordinates pos) {
        return Math.sqrt(((pos.x - this.x)^2 + (pos.y - this.y^2)));
    }
    //здесь достаточно int

    public BaseHero findNearest(ArrayList<BaseHero> enemy) {
        double dist = this.distance(enemy.get(0).position);
        int nearestInd = 0;
        for (int i = 1; i < enemy.size(); i++) {
            if (this.distance(enemy.get(i).position) < dist && !(enemy.get(i).status.equals("dead"))) {
                dist = this.distance(enemy.get(i).position);
                nearestInd = i;
            }
        }
        return enemy.get(nearestInd);
    }

    public boolean isValid(Coordinates pos, ArrayList<BaseHero> party) {
        for (BaseHero h : party) {
            if ((h.position.isSame(pos))
                    || (h.position.x >= Main.FIELD_SIZE) || (h.position.y >= Main.FIELD_SIZE))
                return false;
        }
        return true;
    }
}

//здесь должен быть метод следующего шага, потому что жто смена координат. Или метод смены координат, который будет в себе проверять на границы поля, к которому будет обращаться в мэйне
