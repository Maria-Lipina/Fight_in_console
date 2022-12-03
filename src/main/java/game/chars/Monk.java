package game.chars;

import java.util.ArrayList;

public class Monk extends BaseHero {

    public Monk(ArrayList<BaseHero> myParty, int x, int y) {
        super(12, 7, new int[]{-4,-4}, 30, 5, "Monk", myParty, x, y);
    }

    public Monk(int attack, int protection, int[] damage, double health, int speed, String name, ArrayList<BaseHero> myParty, int x, int y) {
        super(attack, protection, damage, health, speed, name, myParty, x, y);
    }

    @Override
    public void step(ArrayList<BaseHero> enemy, int fieldSize) {
        if (this.status.equals("dead")) return;
        double mostDamaged = this.getMyParty().get(0).getMaxHealth() -
                this.getMyParty().get(0).getHealth();
        int mostDamagedInd = 0;
        for (int i = 1; i < this.getMyParty().size(); i++) {
            if ((this.getMyParty().get(i).getMaxHealth() -
                    this.getMyParty().get(i).getHealth()) > mostDamaged) {
                mostDamaged = this.getMyParty().get(i).getMaxHealth() -
                        this.getMyParty().get(i).getHealth();
                mostDamagedInd = i;
            }
        }
        this.getMyParty().get(mostDamagedInd).damage(this.getDamage()[0]);
        this.getMyParty().get(mostDamagedInd).status = "stand";
        //Уже хотя бы из-за нее нужно добавить логгер
    }
}
