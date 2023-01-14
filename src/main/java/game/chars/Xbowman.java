package game.chars;

import game.BattleField;

import java.util.ArrayList;

/**
 * наследует классу {@link game.chars.BaseHero BaseHero}
 */
public class Xbowman extends BaseHero {
    private int shoot;

    /**
     * Все параметры {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, кроме указанных здесь, предустановлены внутри конструктора. Применяется для сражений, в которых каждый член {@link game.chars.Party Party} - это отдельный персонаж, а не отряд из них.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и является полем боя с переданными на него координатами x и y.
     */
    public Xbowman(int x, int y, String fraction, BattleField field) {
        super(6, 3, new int[]{2,3}, 10, 4,  "Xbowman", x, y, fraction, field);
        this.shoot = 16;
    }


    public Xbowman(int attack, int protection, int[] damage, int health, int speed,
                   String name, int shoot, int x, int y, String fraction, BattleField field) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field);
        this.shoot = shoot;
    }

    /**
     * Все параметры {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, кроме указанных здесь, предустановлены внутри конструктора. Применяется для сражений, в которых каждый член {@link game.chars.Party Party} - это отряд из персонажей определенного класса.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и является полем боя с переданными на него координатами x и y.
     * @param quantity Количество персонажей в одном отряде.
     */
    public Xbowman(int x, int y, String fraction, BattleField field, int quantity) {
        super(6, 3, new int[]{2,3}, 10, 4,  "Xbowman", x, y, fraction, field, quantity);
        this.shoot = 16;
    }


    /**
     * см. родительский класс {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField, int) BaseHero}
     */
    public Xbowman(int attack, int protection, int[] damage, int health, int speed,
                   String name, int shoot, int x, int y, String fraction, BattleField field, int quantity) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field, quantity);
        this.shoot = shoot;
    }


    @Override
    public String getInfo() {
        return super.getInfo() +
                ";shoot=" + shoot;
    }

    /**
     * Для Арбалетчика (Xbowman) или Снайпера (Sniper) сделать ход - это забрать боеприпасы у крестьянина из своей команды и выстрелить в ближайшего противника. Если боеприпас взять не у кого, то выстрел производится за счет собственного, ограниченного запаса снарядов.
     * @param party Объект класса {@link game.chars.Party Party}. Через него персонаж, совершающий ход, получает информацию о других участниках сражения: союзниках и противниках.
     */
    @Override
    public void step(Party party) {

        for (BaseHero h: party.getAliveAsParty().getByFraction(fraction, true)) {
            if (h.name.equals("Peasant") && h.status.equals("stand")) {
                shoot++;
                h.status = "used";
                break;
            }
        }
        if (shoot<1) {
            status = "used";
            target = null;
            damageValue = 0;
            return;
        }
        ArrayList<BaseHero> enemies = party.getAliveAsParty().getByFraction(fraction, false);
        if (enemies.size() == 0) {
            target = this;
            damageValue = 0;
            return;
        }
        target = position.findNearest(enemies);
        double dist = position.distance(target.position);
        damageValue = (dist < speed ?
                super.damageValue(target) :
                (super.damageValue(target)/2));
        target.damage(damageValue);
        shoot--;
    }
}

