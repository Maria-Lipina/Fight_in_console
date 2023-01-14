package game.chars;
import game.BattleField;
import java.util.*;

/**
 * В представителе данного класса собраны все участники сражения (персонажи, объекты-наследники класса {@link game.chars.BaseHero BaseHero}) как единая группа ({@link java.util.ArrayList ArrayList}). Для выполнения своих целей к нему может обращаться как сам персонаж в составе Party, так и внешняя сущность ({@link game.Fight Fight}, {@link game.ConsoleView ConsoleView}).
 */
public class Party {
    public ArrayList<BaseHero> members;
    private final ArrayList<String> fractions;

    /**
     * Для создания двух равных по количеству участников команд, каждый представитель в которых - это отдельный персонаж.
     * @param teamSize Размер команд.
     * @param request Массив из названий классов-наследников {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, из которых должна быть составлена первая команда. При этом первый элемент массива - это название самой фракции, которую представляет команда.
     * @param request1 Массив из названий классов-наследников {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, из которых должна быть составлена вторая команда. При этом первый элемент массива - это название самой фракции, которую представляет команда.
     * @param fieldSize Размер поля, на котором расставляются персонажи.
     * @param field Само поле, на котором расставляются персонажи при создании.
     */
    public Party(int teamSize, String [] request, String [] request1, int fieldSize, BattleField field) {
        fractions = new ArrayList<>();
        fractions.add(request[0]);
        fractions.add(request1[0]);
        members = this.makeRandomly(teamSize, request, 0, 0, request[0], field);
        members.addAll(this.makeRandomly(teamSize, request1, 0, fieldSize-1, request1[0], field));
    }

    /**
     * Для создания двух равных по количеству участников команд, каждый представитель в которых - это отряд из персонажей.
     * @param request Массив из названий классов-наследников {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField, int) BaseHero}, из которых должна быть составлена первая команда. При этом первый элемент массива - это название самой фракции, которую представляет команда.
     * @param request1 Массив из названий классов-наследников {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, из которых должна быть составлена вторая команда. При этом первый элемент массива - это название самой фракции, которую представляет команда.
     * @param requestQuantity Количество персонажей для каждого отряда (кроме первого элемента), который входит в первую команду.
     * @param requestQuantity1 Количество персонажей для каждого отряда (кроме первого элемента), который входит во вторую команду.
     * @param fieldSize Размер поля, на котором расставляются персонажи-отряды.
     * @param field Само поле, на котором расставляются персонажи-отряды при создании.
     */
    public Party(String [] request, String [] request1, int [] requestQuantity, int [] requestQuantity1, int fieldSize, BattleField field) {
        fractions = new ArrayList<>();
        fractions.add(request[0]);
        fractions.add(request1[0]);
        members = this.makeBalanced(request, requestQuantity, 0, 0, request[0], field);
        members.addAll(this.makeBalanced(request1, requestQuantity1, 0, fieldSize-1, request1[0], field));
    }

    /**
     *
     * @return String список фракций, которые участвуют в игре.
     */
    public ArrayList<String> getFractions() {
        return fractions;
    }


    /**
     * Создает команду из персонажей внутри объекта Party. Применяется в игре, где каждый участник - это отряд из персонажей.
     * @param teamCount Размер команды.
     * @param request Массив из названий классов-наследников {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField) BaseHero}, из которых должна быть составлена команда. При этом первый элемент массива - это название самой фракции, которую представляет команда. Количество представителей того или иного класса внутри команды определяется рандомно.
     * @param x Первоначальная координата x.
     * @param y Первоначальная координата y. Вместе с параметром x составляют индекс ячейки в матрице {@link game.BattleField BattleField}, начиная с которой персонажи расставляются на поле боя.
     * @param fraction Название фракции, которой принадлежат члены команды.
     * @param field Объект {@link game.BattleField BattleField}, поле боя, на котором расставляют персонажей.
     * @return {@link java.util.ArrayList ArrayList} из персонажей.
     */
    private ArrayList<BaseHero> makeRandomly(int teamCount, String [] request, int x, int y, String fraction, BattleField field) {
        ArrayList<BaseHero> team = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < teamCount; i++) {
            switch (request[r.nextInt(1, request.length)]) {
                case "Monk" -> team.add(new Monk(x++, y, fraction, field));
                case "Peasant" -> team.add(new Peasant(x++, y, fraction, field));
                case "Robber" -> team.add(new Robber(x++, y, fraction, field));
                case "Sniper" -> team.add(new Sniper(x++, y, fraction, field));
                case "Spearman" -> team.add(new Spearman(x++, y, fraction, field));
                case "Warlock" -> team.add(new Warlock(x++, y, fraction, field));
                case "Xbowman" -> team.add(new Xbowman(x++, y, fraction, field));
            }
        }
        return team;
    }

    /**
     * Создает команду из персонажей внутри объекта Party. Применяется в игре, где каждый участник - это отдельный персонаж.
     * @param requestClass Массив из названий классов-наследников {@link game.chars.BaseHero#BaseHero(int, int, int[], int, int, String, int, int, String, BattleField, int) BaseHero}, из которых должна быть составлена команда. При этом первый элемент массива - это название самой фракции, которую представляет команда.
     * @param requestQuantity Количество персонажей для каждого отряда (кроме первого элемента), который входит в команду.
     * @param x Первоначальная координата x.
     * @param y Первоначальная координата y. Вместе с параметром x составляют индекс ячейки в матрице {@link game.BattleField BattleField}, начиная с которой персонажи расставляются на поле боя.
     * @param fraction Название фракции, которой принадлежат члены команды.
     * @param field Объект {@link game.BattleField BattleField}, поле боя, на котором расставляют персонажей.
     * @return {@link java.util.ArrayList ArrayList} из персонажей.
     */
    private ArrayList<BaseHero> makeBalanced(String [] requestClass, int [] requestQuantity, int x, int y, String fraction, BattleField field) {
        ArrayList<BaseHero> team = new ArrayList<>();
        for (int i = 1; i < requestClass.length; i++) {
            switch (requestClass[i]) {
                case "Monk" -> team.add(new Monk(x++, y, fraction, field, requestQuantity[i]));
                case "Peasant" -> team.add(new Peasant(x++, y, fraction, field, requestQuantity[i]));
                case "Robber" -> team.add(new Robber(x++, y, fraction, field, requestQuantity[i]));
                case "Sniper" -> team.add(new Sniper(x++, y, fraction, field, requestQuantity[i]));
                case "Spearman" -> team.add(new Spearman(x++, y, fraction, field, requestQuantity[i]));
                case "Warlock" -> team.add(new Warlock(x++, y, fraction, field, requestQuantity[i]));
                case "Xbowman" -> team.add(new Xbowman(x++, y, fraction, field, requestQuantity[i]));
            }
        }
        return team;
    }

    /**
     *
     * @param index индекс персонажа {@link game.chars.BaseHero BaseHero} в составе вызывающего объекта Party.
     * @return ссылку на объект по индексу в параметрах.
     */
    public BaseHero get(int index) {
        return members.get(index);
    }

    /**
     *
     * @return общее количество персонажей {@link game.chars.BaseHero BaseHero} в двух командах
     */
    public int size() {
        return members.size();
    }

    /**
     * Сортирует персонажей {@link game.chars.BaseHero BaseHero} в составе в составе вызывающего объекта Party в порядке от наибольшего значения скорости в характеристиках персонажа, до наименьшего.
     */
    public void sortBySpeed() {
        Comparator<BaseHero> comp = Comparator.comparingInt(h -> h.speed);
        members.sort(comp.reversed());
    }

    /**
     * @return {@link java.util.ArrayList ArrayList} из персонажей в составе Party, которые не имеют статуса "dead".
     */
    public ArrayList<BaseHero> getAliveAsList() {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (!h.status.equals("dead"))  res.add(h);
        }
        return res;
    }


    private Party(Party whole) {
        this.fractions = whole.fractions;
        this.members = whole.getAliveAsList();
    }

    /**
     *
     * @return новый объект Party только из тех персонажей {@link game.chars.BaseHero BaseHero} в сражении, которые не имеют статуса "dead".
     */
    public Party getAliveAsParty() {
        return new Party(this);
    }

    /**
     * Фильтрует персонажей {@link game.chars.BaseHero BaseHero} в составе Party по фракции, которой они принадлежат
     * @param fraction фракция, которой принадлежит персонаж, вызывающий метод.
     * @param ally True, если необходимо найти персонажей, относящихся к fraction (союзники). False, если если необходимо найти персонажей, НЕ относящихся к fraction (противники)
     * @return {@link java.util.ArrayList ArrayList} из персонажей по запросу в параметрах.
     */
    public ArrayList<BaseHero> getByFraction(String fraction, boolean ally) {
        ArrayList<BaseHero> res = new ArrayList<>();
        for (BaseHero h: members) {
            if (!ally) {
                if (!h.fraction.equals(fraction)) res.add(h);
            }
            if (ally && h.fraction.equals(fraction)) res.add(h);
        }
        return res;
    }

}