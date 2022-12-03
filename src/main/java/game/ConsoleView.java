package game;

import game.chars.Coordinates;

import java.util.Collections;

public class ConsoleView {
    public static void field(int teamCount, int fieldSize) { //по сути я могла бы обращаться напрямую к полям в Main вместо параметров. Хотя подсветка конечно это приятно
        //Завести здесь один-два трингбилдера вместо прямого обращения к консоли
        if (Main.step == 0) {
            System.out.println(Colors.ANSI_RED+"First step!"+Colors.ANSI_RESET);
        } else {
            System.out.println(Colors.ANSI_RED + "Step: "+Main.step+Colors.ANSI_RESET);
        }

        // Верх игровое поле
        System.out.println(
                "\u250c" + String.join("", Collections.nCopies(fieldSize-1, "\u2500\u2500\u252c")) + "\u2500\u2500\u2510");

        // Середина игровое поле
        for (int i = 1; i < fieldSize; i++) {
            System.out.println(ConsoleView.getCharFull(i,teamCount, fieldSize));
            System.out.println(
                    "\u251c" + String.join("", Collections.nCopies(fieldSize-1, "\u2500\u2500\u253c")) + "\u2500\u2500\u2524");
        }

        // Низ игровое поле
        System.out.println(ConsoleView.getCharFull(fieldSize-1, teamCount, fieldSize));
        System.out.println(
                "\u2514" + String.join("", Collections.nCopies(fieldSize-1, "\u2500\u2500\u2534")) + "\u2500\u2500\u2518");
        System.out.println("Press ENTER to continue. Press Q to exit");
    }

    private static StringBuilder charTable = new StringBuilder();
    private static String getChar(int x, int y, int teamCount) {
        String str = "  ";
        for (int i = 0; i < teamCount; i++) {
            if (Main.lightSide.get(i).position.isSame(new Coordinates(x, y))) {
                if (Main.lightSide.get(i).getStatus().equals("dead")) {
                    str = Colors.ANSI_RED + Main.lightSide.get(i).getName().substring(0, 2) + Colors.ANSI_RESET;

                    charTable.append(Colors.ANSI_RED +
                            Main.lightSide.get(i).getName() + " HP: " + Main.lightSide.get(i).getHealth() + ", Status: " + Main.lightSide.get(i).getStatus()
                            + Colors.ANSI_RESET + "    ");
                }
                else {
                    str = Colors.ANSI_BLUE + Main.lightSide.get(i).getName().substring(0, 2) + Colors.ANSI_RESET;
                    charTable.append(Colors.ANSI_BLUE +
                            Main.lightSide.get(i).getName() + " HP: " + Main.lightSide.get(i).getHealth() + ", Status: " + Main.lightSide.get(i).getStatus()
                            + Colors.ANSI_RESET + "    ");
                }
            }
            if (Main.darkSide.get(i).position.isSame(new Coordinates(x, y))) {
                if (Main.darkSide.get(i).getStatus().equals("dead")) {
                    str = Colors.ANSI_RED + Main.darkSide.get(i).getName().substring(0, 2) + Colors.ANSI_RESET;
                    charTable.append(Colors.ANSI_RED +
                            Main.darkSide.get(i).getName() + " HP: " + Main.darkSide.get(i).getHealth() + ", Status: " + Main.darkSide.get(i).getStatus()
                            + Colors.ANSI_RESET + "    ");
                }
                else {
                    str = Colors.ANSI_GREEN + Main.darkSide.get(i).getName().substring(0, 2) + Colors.ANSI_RESET;
                    charTable.append(Colors.ANSI_GREEN +
                            Main.darkSide.get(i).getName() + " HP: " + Main.darkSide.get(i).getHealth() + ", Status: " + Main.darkSide.get(i).getStatus()
                            + Colors.ANSI_RESET + "    ");
                }
            }
        }
        return str;
    }

    public static String getCharFull (int x, int teamCount, int fieldSize) {
        StringBuilder s = new StringBuilder();
        s.append(String.format("\u2502%s", ConsoleView.getChar(x, 0, teamCount)));
        for (int y = 1; y < fieldSize; y++) {
            s.append(String.format("\u2502%s", ConsoleView.getChar(x, y, teamCount)));
        }
        s.append(String.format("\u2502    %s", charTable));
        charTable.delete(0, charTable.length());
        return s.toString();
    }
}

/*Шпаргалка по значению кодовых точек
 * '\u250c' - верхний левый угол
 * '\u252c' - пересечение верхней горизонтальной границы и внутренней вертикальной
 * '\u2510' - верхний правый угол
 * '\u251c' - пересечение внешней границы слева и горизонтальной внутренней
 * '\u253c' - пересечение внутренней вертикальной и горизонтальной границ ячейки
 * '\u2524' - пересечение внешней границы справа и горизонтальной внутренней
 * '\u2514' - левый нижний угол
 * '\u2534' - пересечение нижней горизонтальной границы и внутренней вертикальной
 * '\u2518' - правый нижний угол
 * '\u2500' - нижняя граница
 * '\u2574' - верхняя граница
 * "___"
 * */

class Colors {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
}