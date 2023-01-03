package game;

import game.chars.Party;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Логирование игрового процесса. Для каждой сессии в предустановленной директории на компьютере создается файл csv-формата, имя которого состоит из даты и времени создания. В файл добавляются заголовки столбцов, которые заполняются в течение каждого раунда для каждого участвующего персонажа:
 * "StepNo" - номер раунда.
 * "Fraction" - название фракции, одной из воюющих сторон.
 * "Me" - имя текущего персонажа, совершившего ход.
 * "MyPos" - координаты персонажа на игровом поле.
 * "Target" - имя цели.
 * "TargetPos" - координаты цели на игровом поле.
 * "Damage val" - величина нанесенного урона (если положительное число) или количество восстановленных очков здоровья (если число отрицательное).
 * "TgStatus" - статус цели после хода текущего персонажа.
 * TODO: путь к директории так же должен быть в файле-конфиге.csv, как и названия полей
*/
 public class Logger {
    private FileWriter fw;

    private Party members;

    private String [] header;


    /**
     * Создает представителя класса Logger.
     * @param members ссылка на объект класса {@link game.chars.Party Party}, в котором информация для всех столбцов файла-лога, кроме "Step".
     * @throws {@link IOException} при невозможности создать файл, записать в него (TODO: а дальше добавить как поступит программа)
     */
    public Logger(Party members) throws IOException {

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM.dd_HH.mm.ss");
        StringBuilder sb = new StringBuilder("D:\\IT\\Java\\Fight in console\\log\\");
        sb.append(dtf.format(LocalDateTime.now()));
        sb.append(".csv");

        fw = new FileWriter(sb.toString(), false);
        sb.delete(0, sb.length());

        this.header = new String[]{"StepNo", "Fraction", "Me", "MyPos", "Target", "TargetPos", "Damage val", "TgStatus"};
        this.members = members;

    }

    /**
     * Обращается к каждому персонажу (представитель одного из наследников абстрактного класса {@link game.chars.BaseHero BaseHero}) в составе объекта класса Party и добавляет в файл лога полученную от персонажа информацию:
     *  * "Fraction" - название фракции, одной из воюющих сторон.
     *  * "Me" - имя текущего персонажа, совершившего ход.
     *  * "MyPos" - координаты персонажа на игровом поле.
     *  * "Target" - имя цели.
     *  * "TargetPos" - координаты цели на игровом поле.
     *  * "Damage val" - величина нанесенного урона (если положительное число) или количество восстановленных очков здоровья (если число отрицательное).
     *  * "TgStatus" - статус цели после хода текущего персонажа.
     * @param step - для поля Step персонаж не может предоставить данные, поэтому они передаются как параметр
     */
        public void printDefault(int step) {
            try {
                if (step == 0) {
                    for (int i = 0; i < members.size(); i++) {
                        fw.append(members.get(i).getInfo());
                        fw.append("\r\n");
                    }
                    fw.append(String.join(";", header));
                }
                else {
                    for (int i = 0; i < members.getAliveAsList().size(); i++) {
                        fw.append("\r\n");
                        fw.append(Integer.toString(step));
                        fw.append(";");
                        fw.append(members.getAliveAsList().get(i).defaultLog());
                    }
                }
                fw.flush();
            }
            catch(IOException e){ //TODO: здесь правильнее добавить имплементацию autocloseable и обрабатывать выше по стеку, как с конструктором
                e.printStackTrace();
            }

        }

}

