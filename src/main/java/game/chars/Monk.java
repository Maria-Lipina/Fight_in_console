package game.chars;

import game.BattleField;

import java.util.ArrayList;

/**
 * см. родительский класс {@link game.chars.BaseHero BaseHero}
 */
public class Monk extends BaseHero {

    int restoration;

    /**
     * Все параметры {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, кроме указанных здесь, предустановлены внутри конструктора. Применяется для сражений, в которых каждый член {@link game.chars.Party Party} - это отдельный персонаж, а не отряд из них.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и является полем боя с переданными на него координатами x и y.
     */
    public Monk(int x, int y, String fraction, BattleField field) {
        super(12, 7, new int[]{-4,-4}, 30, 5, "Monk", x, y, fraction, field);
        restoration = 1;
    }

    /**
     * см. родительский класс {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}.
     */
    public Monk(int attack, int protection, int[] damage, int health, int speed,
                String name, int x, int y, String fraction, BattleField field) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field);
        restoration = 1;
    }


    /**
     * Все параметры {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, кроме указанных здесь, предустановлены внутри конструктора. Применяется для сражений, в которых каждый член {@link game.chars.Party Party} - это отряд из персонажей определенного класса.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и является полем боя с переданными на него координатами x и y.
     * @param quantity Количество персонажей в одном отряде.
     */
    public Monk(int x, int y, String fraction, BattleField field, int quantity) {
        super(12, 7, new int[]{-4,-4}, 30, 5, "Monk", x, y, fraction, field, quantity);
        restoration = quantity;
    }

    /**
     * см. родительский класс {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField, int) BaseHero}
     */
    public Monk(int attack, int protection, int[] damage, int health, int speed,
                String name, int x, int y, String fraction, BattleField field, int quantity) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field, quantity);
        restoration = quantity;
    }

    private float mostDamaged;
    private int mostDamagedInd;

    private void getMostDamaged(ArrayList<BaseHero> heroes) {

        mostDamagedInd = 0;
        float [] hps = new float[heroes.size()];
        for (int i = 0; i < heroes.size(); i++) {
            hps[i] = heroes.get(i).health / heroes.get(i).maxHealth;
        }

        mostDamaged = Float.MAX_VALUE;

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
            if (enemies.size() == 0) {
                target = this;
                damageValue = 0;
                return;
            }
            this.getMostDamaged(enemies);
            target = enemies.get(mostDamagedInd);
            damageValue = -damage[0];
            target.damage(damageValue);

        }
        if (mostDamaged == 0.0f) {
            target = allies.get(mostDamagedInd);
            if (target == this) {
                damageValue = 0;
                return;
            }
            else{
                damageValue = -1 * restoration;
                target.status = "stand";
            }
        }
        else {
            target = allies.get(mostDamagedInd);
            damageValue = damage[0];
        }
        target.damage(damageValue);
    }

}