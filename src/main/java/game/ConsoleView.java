package game;

import game.chars.Coordinates;

import java.util.Collections;

public class ConsoleView {
    private static StringBuilder field = new StringBuilder();
    public static StringBuilder field() {

        if (Main.step == 1) {
            field.append(Colors.ANSI_RED);
            field.append("First step!");
            field.append(Colors.ANSI_RESET);
            field.append("\n");
//            System.out.println(Colors.ANSI_RED+"First step!"+Colors.ANSI_RESET);
        }
        else {
            field.append(Colors.ANSI_RED);
            field.append("Step: ");
            field.append(Main.step);
            field.append(Colors.ANSI_RESET);
            field.append("\n");
//            System.out.println(Colors.ANSI_RED + "Step: "+Main.step+Colors.ANSI_RESET);
        }


        // Верх игровое поле
        field.append("\u250c");
        field.append(String.join("", Collections.nCopies(Main.FIELD_SIZE-1, "\u2500\u2500\u252c")));
        field.append("\u2500\u2500\u2510\n");

//        System.out.println(
//                "\u250c" + String.join("", Collections.nCopies(Main.FIELD_SIZE-1, "\u2500\u2500\u252c")) + "\u2500\u2500\u2510");

        // Середина игровое поле
        for (int i = 1; i < Main.FIELD_SIZE; i++) {
            ConsoleView.getCharFull(i);
            field.append("\u251c");
            field.append(String.join("", Collections.nCopies(Main.FIELD_SIZE-1, "\u2500\u2500\u253c")));
            field.append("\u2500\u2500\u2524\n");
//            System.out.println(ConsoleView.getCharFull(i));
//            System.out.println(
//                    "\u251c" + String.join("", Collections.nCopies(Main.FIELD_SIZE-1, "\u2500\u2500\u253c")) + "\u2500\u2500\u2524");
        }

        // Низ игровое поле
        ConsoleView.getCharFull(Main.FIELD_SIZE-1);
        field.append("\u2514");
        field.append(String.join("", Collections.nCopies(Main.FIELD_SIZE-1, "\u2500\u2500\u2534")));
        field.append("\u2500\u2500\u2518\n");
        field.append("Press ENTER to continue. Press Q to exit\n");

        return field;
//        System.out.println(ConsoleView.getCharFull(Main.FIELD_SIZE-1));
//        System.out.println(
//                "\u2514" + String.join("", Collections.nCopies(Main.FIELD_SIZE-1, "\u2500\u2500\u2534")) + "\u2500\u2500\u2518");
//        System.out.println("Press ENTER to continue. Press Q to exit");return
    }

    private static StringBuilder charTable = new StringBuilder();

    private static void getChar(int x, int y) {
//        String str = "  ";
        for (int i = 0; i < Main.TEAM_SIZE; i++) {
            if (Main.lightSide.get(i).position.isSame(new Coordinates(x, y))) {
                if (Main.lightSide.get(i).getStatus().equals("dead")) {

                    field.append(Colors.ANSI_RED);
                    field.append(Main.lightSide.get(i).getName(), 0, 2);
                    field.append(Colors.ANSI_RESET);
//                    str = Colors.ANSI_RED + Main.lightSide.get(i).getName().substring(0, 2) + Colors.ANSI_RESET;

                    charTable.append(Colors.ANSI_RED);
                    charTable.append(Main.lightSide.get(i).getName());
                    charTable.append(" HP: ");
                    charTable.append(Main.lightSide.get(i).getHealth());
                    charTable.append(", Status: ");
                    charTable.append(Main.lightSide.get(i).getStatus());
                    charTable.append(Colors.ANSI_RESET);
                    charTable.append("    ");

                    return;
//                    charTable.append(Colors.ANSI_RED +
//                            Main.lightSide.get(i).getName() + " HP: " + Main.lightSide.get(i).getHealth() + ", Status: " + Main.lightSide.get(i).getStatus()
//                            + Colors.ANSI_RESET + "    ");
                }
                else {

                    field.append(Colors.ANSI_BLUE);
                    field.append(Main.lightSide.get(i).getName(), 0, 2);
                    field.append(Colors.ANSI_RESET);
//                    str = Colors.ANSI_BLUE + Main.lightSide.get(i).getName().substring(0, 2) + Colors.ANSI_RESET;

                    charTable.append(Colors.ANSI_BLUE);
                    charTable.append(Main.lightSide.get(i).getName());
                    charTable.append(" HP: ");
                    charTable.append(Main.lightSide.get(i).getHealth());
                    charTable.append(", Status: ");
                    charTable.append(Main.lightSide.get(i).getStatus());
                    charTable.append(Colors.ANSI_RESET);
                    charTable.append("    ");
                    return;
                }
            }
            if (Main.darkSide.get(i).position.isSame(new Coordinates(x, y))) {
                if (Main.darkSide.get(i).getStatus().equals("dead")) {

                    field.append(Colors.ANSI_RED);
                    field.append(Main.darkSide.get(i).getName(), 0, 2);
                    field.append(Colors.ANSI_RESET);
                    return;
//                    str = Colors.ANSI_RED + Main.darkSide.get(i).getName().substring(0, 2) + Colors.ANSI_RESET;
//                    charTable.append(Colors.ANSI_RED +
//                            Main.darkSide.get(i).getName() + " HP: " + Main.darkSide.get(i).getHealth() + ", Status: " + Main.darkSide.get(i).getStatus()
//                            + Colors.ANSI_RESET + "    ");
                }
                else {

                    field.append(Colors.ANSI_GREEN);
                    field.append(Main.darkSide.get(i).getName(), 0, 2);
                    field.append(Colors.ANSI_RESET);

                    charTable.append(Colors.ANSI_GREEN);
                    charTable.append(Main.darkSide.get(i).getName());
                    charTable.append(" HP: ");
                    charTable.append(Main.darkSide.get(i).getHealth());
                    charTable.append(", Status: ");
                    charTable.append(Main.darkSide.get(i).getStatus());
                    charTable.append(Colors.ANSI_RESET);
                    charTable.append("    ");
                    return;

//                    str = Colors.ANSI_GREEN + Main.darkSide.get(i).getName().substring(0, 2) + Colors.ANSI_RESET;
//                    charTable.append(Colors.ANSI_GREEN +
//                            Main.darkSide.get(i).getName() + " HP: " + Main.darkSide.get(i).getHealth() + ", Status: " + Main.darkSide.get(i).getStatus()
//                            + Colors.ANSI_RESET + "    ");
                }
            }
        }
        field.append("  ");
//        return str;
    }

    public static void getCharFull (int x) {
        field.append("\u2502");
        ConsoleView.getChar(x, 0);
        for (int y = 1; y < Main.FIELD_SIZE; y++) {
            field.append("\u2502");
            ConsoleView.getChar(x, y);
        }
        field.append(String.format("\u2502    %s\n", charTable));
        charTable.delete(0, charTable.length());
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