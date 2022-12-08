package game;

import game.chars.BaseHero;
import game.chars.Party;

import java.util.ArrayList;

public class Fight {

    Party members;

    public Party getMembers() {
        return members;
    }

    public Fight(int teamSize, String [] request, String [] request1, int fieldSize) {
        this.members = new Party(teamSize, request, request1, fieldSize);
    }

    public void round (int step) {
        if (step == 0) {
            members.sortBySpeed();
        } else {
            ArrayList <BaseHero> active = members.getAlive();
            for (BaseHero h: active) h.step(this.members);
        }
            //вызов метода для получения живых
            //вызов step для каждого живого
            //по любому получается передавать весь список без разделения на dark, light
            //View - чтобы распечатать. Лог - чтобы в таблицу внести. Монаху - чтобы интеллектуально сравнить потери с каждой стороны
            //Крестьянину - чтобы отойти от врага. Например
            //Пешему, чтобы проверить isValidPos не только относительно своих, но и относительно врагов (чтобы уж точно ни на кого не наступить)
        }
}

