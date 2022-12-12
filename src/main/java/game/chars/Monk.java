package game.chars;

import java.util.ArrayList;

public class Monk extends BaseHero {

    public Monk(int x, int y, String fraction) {
        super(12, 7, new int[]{-4,-4}, 30, 5, "Monk", x, y, fraction);
    }

    public Monk(int attack, int protection, int[] damage, int health, int speed,
                String name, int x, int y, String fraction) {
        super(attack, protection, damage, health, speed, name, x, y, fraction);
    }

    @Override
    public void step(Party party) {
        ArrayList <BaseHero> allies = party.getByFraction(fraction, true);
        float mostDamaged = (float) (allies.get(0).health / allies.get(0).maxHealth);
        int mostDamagedInd = 0;
        for (int i = 1; i < allies.size(); i++) {
            if(((float) (allies.get(i).health / allies.get(i).maxHealth)) < mostDamaged) {
                mostDamagedInd = i;
                mostDamaged = (float) (allies.get(i).health / allies.get(i).maxHealth);

            }
        }
        if (0.75 >= mostDamaged) {
            ArrayList <BaseHero> enemies = party.getAliveAsParty().getByFraction(fraction, false);
            mostDamaged = 0;
            mostDamagedInd = 0;
            for (int i = 1; i < enemies.size(); i++) {
                if(((float) (enemies.get(i).health / enemies.get(i).maxHealth)) < mostDamaged) {
                    mostDamagedInd = i;
                    mostDamaged = (float) (enemies.get(i).health / enemies.get(i).maxHealth);
                }
            }
            target = enemies.get(mostDamagedInd);
            damageValue = -damage[0];
            target.damage(damageValue);
        }
        else {
            target = allies.get(mostDamagedInd);
            if (mostDamaged == 0) { damageValue = -1; target.status = "stand"; }
            else damageValue = damage[0];
            target.damage(damageValue);
        }
    }
}