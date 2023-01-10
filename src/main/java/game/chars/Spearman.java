package game.chars;

import game.BattleField;

import java.util.ArrayList;


/**
 * наследует классу {@link game.chars.BaseHero BaseHero}
 */
public class Spearman extends BaseHero {

    /**
     * Все параметры {@link game.chars.BaseHero BaseHero}, кроме указанных здесь, предустановлены внутри конструктора. Применяется для сражений, в которых каждый член {@link game.chars.Party Party} - это отдельный персонаж, а не отряд из них.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и является полем боя с переданными на него координатами x и y.
     */
    public Spearman(int x, int y, String fraction, BattleField field) {
        super(4, 5,  new int[]{1,3}, 10, 4,  "Spearman", x, y, fraction, field);
    }

    public Spearman(int attack, int protection, int[] damage, int health, int speed,
                    String name, int x, int y, String fraction, BattleField field) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field);
    }

    /**
     * Все параметры {@link game.chars.BaseHero BaseHero}, кроме указанных здесь, предустановлены внутри конструктора. Применяется для сражений, в которых каждый член {@link game.chars.Party Party} - это отряд из персонажей определенного класса.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и является полем боя с переданными на него координатами x и y.
     * @param quantity Количество персонажей в одном отряде.
     */
    public Spearman(int x, int y, String fraction, BattleField field, int quantity) {
        super(4, 5,  new int[]{1,3}, 10, 4,  "Spearman", x, y, fraction, field, quantity);
    }

    public Spearman(int attack, int protection, int[] damage, int health, int speed,
                    String name, int x, int y, String fraction, BattleField field, int quantity) {
        super(attack, protection, damage, health, speed, name, x, y, fraction, field, quantity);
    }

    @Override
    public void step(Party party) {

        ArrayList<BaseHero> enemies = party.getAliveAsParty().getByFraction(fraction, false);
        if (enemies.size() == 0) {
            target = this;
            damageValue = 0;
            return;
        }

        target = position.findNearest(enemies);
        int x = position.x;
        int y = position.y;

        int distance = position.distance(target.position);

        boolean checkPos;
        if (distance == (int) Math.sqrt(2)) {
            damageValue = super.damageValue(target);
            target.damage(damageValue);
            return;
        }

        checkPos = field.isValidPos(--x, y);
        if (target.position.x < position.x && checkPos) {
            field.placeMe(position.x, position.y, --position.x, position.y);
            damageValue = 0;
            return;
        }
        ++x;
        checkPos = field.isValidPos(++x, y);
        if (target.position.x > position.x && checkPos) {
            field.placeMe(position.x, position.y, ++position.x, position.y);
            damageValue = 0;
            return;
        }
        --x;
        checkPos = field.isValidPos(x, --y);
        if (target.position.y < position.y && checkPos) {
            field.placeMe(position.x, position.y, position.x, --position.y);
            damageValue = 0;
            return;
        }
        ++y;
        checkPos = field.isValidPos(x, ++y);
        if (target.position.y > position.y && checkPos) {
            field.placeMe(position.x, position.y, position.x, ++position.y);
            damageValue = 0;
        }
    }

}
