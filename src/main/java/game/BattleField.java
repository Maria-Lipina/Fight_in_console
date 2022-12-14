package game;

import game.chars.BaseHero;

import java.util.ArrayList;

public class BattleField {
    public int[][] field;

    public BattleField(int fieldSize) {
        this.field = new int[fieldSize][fieldSize];
    }

    public void placeHeroes(ArrayList<BaseHero> heroes) {
        for (BaseHero h: heroes) {
            field[h.getPosition().getX()][h.getPosition().getY()] = 1;
        }

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }

    }

    public void placeMe (int xOld, int yOld, int xNew, int yNew) {
        field[xOld][yOld] = 0;
        field[xNew][yNew] = 1;
    }

    public void placeMe (int xNew, int yNew) {
        field[xNew][yNew] = 1;
    }

    public boolean isValidPos (int xNew, int yNew) {
        if((xNew < 0 || xNew >= field.length || yNew < 0 || yNew >= field.length)) return false;
        else if (field[xNew][yNew] != 0) return false;
        return true;
    }
}
