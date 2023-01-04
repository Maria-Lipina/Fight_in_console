package game;

import game.chars.BaseHero;
import game.chars.Party;

import java.util.ArrayList;

/**
 * Класс, в котором создаются команды персонажей (объект класса {@link game.chars.Party Party}) и взаимодействуют друг с другом - сражаются на поле битвы ({@link game.BattleField BattleField}).
 * Так же в объекте класса производится проверка статуса сражения - закончено оно победой одной из сторон или продолжается. Победителем считается та сторона, персонажи в которой убили всех своих противников и остались живы.
 */
public class Fight {

    Party members;
    BattleField field;


    /**
     * Создает объект Fight "Сражение". Данный конструктор применяется, когда при создании {@link game.chars.Party Party} пользователь устанавливает, из каких классов состоит команда, но не устанавливает количество представителей того или иного класса.
     * @param teamSize командой считается группа персонажей размера teamSize (int), принадлежащая к одной из фракций. Команды персонажей из всех фракций вместе составляют объект класса {@link game.chars.Party Party}.
     * @param request массив строк, в котором пользователь передает запрос, из каких классов состоит первая команда.
     * @param request1 массив строк, в котором пользователь передает запрос, из каких классов состоит вторая команда.
     * @param fieldSize параметр для создания игрового поля ({@link game.BattleField BattleField} размером fieldSize х fieldSize)
     */
    public Fight(int teamSize, String [] request, String [] request1, int fieldSize) {
        field = new BattleField(fieldSize);
        this.members = new Party(teamSize, request, request1, fieldSize, field);
    }


    /**
     * Создает объект Fight "Сражение". Данный конструктор применяется, когда при создании {@link game.chars.Party Party} пользователь полностью контролирует, из каких классов состоит команда, устанавливает количество представителей того или иного класса.
     * @param request массив строк, в котором пользователь передает запрос, из каких классов состоит первая команда.
     * @param request1 массив строк, в котором пользователь передает запрос, из каких классов состоит вторая команда.
     * @param requestQuantity массив целых чисел размера, равного request, в котором пользователь устанавливает количество представителей каждого класса, запрошенного в request.
     * @param requestQuantity1 массив целых чисел размера, равного request1, в котором пользователь устанавливает количество представителей каждого класса, запрошенного в request1.
     * @param fieldSize параметр для создания игрового поля ({@link game.BattleField BattleField} размером fieldSize х fieldSize)
     */
    public Fight(String [] request, String [] request1, int [] requestQuantity, int [] requestQuantity1, int fieldSize) {
        field = new BattleField(fieldSize);
        this.members = new Party(request, request1, requestQuantity, requestQuantity1, fieldSize, field);
    }

    /**
     * Для обращения к участникам сражения в объекте класса {@link game.chars.Party Party}
     * @return {@link game.chars.Party Party}
     */
    public Party getMembers() {
        return members;
    }


    /**
     * метод взаимодействия персонажей между друг другом в течение всего процесса игры, а так же контроля над статусом сражения (закончено или нет).
     * @param step номер раунда.
     * @return True, если раунд состоялся, и персонажи (представители классов-наследников {@link game.chars.BaseHero BaseHero} в составе {@link game.chars.Party Party})сделали свои ходы. False, если при начале раунда в живых остались представители только одной из сторон, а значит сражение (вместе с ним и сама игра) закончено.
     */
    public boolean round (int step) {
        if (step == 0) {
            members.sortBySpeed();
            return true;

        } else {
            ArrayList <BaseHero> active = members.getAliveAsList();
            boolean flag = isContinue(active);
            if (!flag) return false;
            else for (BaseHero h : active) h.step(members);
        }
        return true;
    }

    private boolean isContinue (ArrayList<BaseHero> active) {
        boolean res = false;
        for (int i = 0; i < active.size()-1; i++) {
            res = !active.get(i + 1).getFraction()
                    .equals(active.get(i).getFraction());
            if (res) break;
        }
        return res;
    }

}

