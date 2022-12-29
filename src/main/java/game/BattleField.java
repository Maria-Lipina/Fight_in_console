package game;

/**
 * Battlefield - служебный класс для расстановки персонажей на поле боя и контроля их перемещения по нему.
 */
public class BattleField {
    private int[][] field;

    /**
     * Создание представителя класса Battlefield.
     * @param fieldSize - количество строк и столбцов для создания поля, которое представляет собой двумерный массив field фиксированного неизменяемого размера. Количество строк здесь - количество вложенных массивов в общем. Количество столбцов - длина каждого вложенного массива.
     */
    public BattleField(int fieldSize) {
        this.field = new int[fieldSize][fieldSize];
    }

    /**
     *Метод для перемещения по полю персонажа, вызывающего метод. Элемент в массиве по старым координатам обнуляется, по новым - принимает значение 1
     * @param xOld старый индекс строки в массиве field
     * @param yOld старый индекс столбца в массиве field
     * @param xNew новый индекс строки в массиве field
     * @param yNew новый индекс столбца в массиве field
     */
    public void placeMe (int xOld, int yOld, int xNew, int yNew) {
        field[xOld][yOld] = 0;
        field[xNew][yNew] = 1;
    }

    /**
     * Метод для расстановки персонажей в начале игры. Элементам массива по указанному индексу присваивается значение 1
     * @param xNew индекс строки в массиве field
     * @param yNew индекс столбца в массиве field
     */
    public void placeMe (int xNew, int yNew) {
        field[xNew][yNew] = 1;
    }

    /**
     * Метод для проверки, возможно ли переместить персонажа на точку по указанным в параметрах координатам
     * @param xNew индекс строки в массиве field
     * @param yNew индекс столбца в массиве field
     * @return True, если возможно обратиться к элементу массива по указанному индексу. False, если индекс указывает на элемент за пределами массива.
     */
    public boolean isValidPos (int xNew, int yNew) {
        if((xNew < 0 || xNew >= field.length || yNew < 0 || yNew >= field.length)) return false;
        else return field[xNew][yNew] == 0;
    }
}
