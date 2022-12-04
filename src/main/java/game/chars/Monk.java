package game.chars;

import java.util.ArrayList;

public class Monk extends BaseHero {

    public Monk(ArrayList<BaseHero> myParty, int x, int y) {
        super(12, 7, new int[]{-4,-4}, 30, 5, "Monk", myParty, x, y);
    }

    public Monk(int attack, int protection, int[] damage, int health, int speed, String name, ArrayList<BaseHero> myParty, int x, int y) {
        super(attack, protection, damage, health, speed, name, myParty, x, y);
    }

    @Override
    public void step(ArrayList<BaseHero> enemy) {
        if (status.equals("dead")) return;
        double mostDamaged = myParty.get(0).maxHealth -
                myParty.get(0).health;
        int mostDamagedInd = 0;
        for (int i = 1; i < myParty.size(); i++) {
            if ((myParty.get(i).maxHealth -
                    myParty.get(i).health) > mostDamaged) {
                mostDamaged = myParty.get(i).maxHealth -
                        myParty.get(i).health;
                mostDamagedInd = i;
            }
        }
        myParty.get(mostDamagedInd).damage(damage[0]);
        myParty.get(mostDamagedInd).status = "stand";
    }
}
