package game.chars;

import game.BattleField;

import java.util.ArrayList;

public class Monk extends BaseHero {

    public Monk(int x, int y, String fraction, BattleField field) {
        super(12, 7, new int[]{-4,-4}, 30, 5, "Monk", x, y, fraction, field);
    }

    public Monk(int attack, int protection, int[] damage, int health, int speed,
                String name, int x, int y, String fraction, BattleField field) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field);
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
    }

}