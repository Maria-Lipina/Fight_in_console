package game.chars;
import game.BattleField;

/**
 * наследует классу {@link game.chars.BaseHero BaseHero}
 */
public class Peasant extends BaseHero {

    /**
     * Все параметры {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, кроме указанных здесь, предустановлены внутри конструктора. Применяется для сражений, в которых каждый член {@link game.chars.Party Party} - это отдельный персонаж, а не отряд из них.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и является полем боя с переданными на него координатами x и y.
     */
    public Peasant(int x, int y, String fraction, BattleField field) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", x, y, fraction, field);
    }


    /**
     * Все параметры {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, кроме указанных здесь, предустановлены внутри конструктора. Применяется для сражений, в которых каждый член {@link game.chars.Party Party} - это отряд из персонажей определенного класса.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и является полем боя с переданными на него координатами x и y.
     * @param quantity Количество персонажей в одном отряде.
     */
    public Peasant(int x, int y, String fraction, BattleField field, int quantity) {
        super(1, 1, new int[]{1, 1}, 1, 3, "Peasant", x, y, fraction, field, quantity);
    }

    /**
     * Для крестьянина (Peasant) сделать ход - это набрать боеприпасов для стрелков {@link game.chars.Xbowman Xbowman}, {@link game.chars.Sniper Sniper}.
     * @param party Объект класса {@link game.chars.Party Party}. Через него персонаж, совершающий ход, получает информацию о других участниках сражения: союзниках и противниках.
     */
    @Override
    public void step(Party party) {
        if (status.equals("used")) status = "stand";
    }
}
