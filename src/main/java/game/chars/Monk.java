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

    private float mostDamaged;
    private int mostDamagedInd;

    private void getMostDamaged(ArrayList<BaseHero> heroes) {
        mostDamaged = Float.MAX_VALUE;
        mostDamagedInd = 0;
        float [] hps = new float[heroes.size()];
        for (int i = 0; i < heroes.size(); i++) {
            hps[i] = heroes.get(i).health / heroes.get(i).maxHealth;
        }

        for (int i = 0; i < hps.length; i++) {
            if (hps[i] < mostDamaged) {
                mostDamaged = hps[i];
                mostDamagedInd = i;
            }
        }
    }

    @Override
    public void step(Party party) {
        ArrayList <BaseHero> allies = party.getByFraction(fraction, true);
        this.getMostDamaged(allies);

        if (mostDamaged >= 0.75f) {
            ArrayList<BaseHero> enemies = party.getAliveAsParty().getByFraction(fraction, false);
            this.getMostDamaged(enemies);
            target = enemies.get(mostDamagedInd);
            damageValue = -damage[0];
            target.damage(damageValue);
            System.out.println(target.getInfo());
            return;
        }
        if (mostDamaged == 0.0f) {
            target = allies.get(mostDamagedInd);
            damageValue = -1;
            target.status = "stand";
        }
        else {
            target = allies.get(mostDamagedInd);
            damageValue = damage[0];
        }
        target.damage(damageValue);
        System.out.println(target.getInfo());
    }

}