package game.chars;

import game.BattleField;

import java.util.Arrays;

/**
 * Класс описывает персонажа, участвующего в сражении.
 */
public abstract class BaseHero implements BaseInterface {
    protected int attack;
    protected int defense;

    protected int[] damage;

    protected BattleField field;

    protected int damageValue;
    protected float health;
    protected float maxHealth;
    protected int speed;
    protected String name;

    protected BaseHero target;

    protected Coordinates position;
    protected String status;

    protected String fraction;

    private static int idCount = 0;
    protected int id;


    /**
     * Создает персонажа, участвующего в сражении.
     * @param attack Бонус к атаке может определить точность её попадания по цели и величину нанесенного урона.
     * @param defense Бонус к защите может определить точность попадания со стороны противника и величину полученного урона.
     * @param damage Минимальное и максимальное значение урона, который может нанести персонаж.
     * @param health Очки здоровья.
     * @param speed Скорость реакции на поле боя.
     * @param name Имя персонажа.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который и \вляется полем боя с переданными на него координатами x и y.
     */
    public BaseHero(int attack, int defense, int[] damage, int health, int speed,
                    String name, int x, int y, String fraction, BattleField field) {
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        this.maxHealth = health;
        this.health = maxHealth;
        this.speed = speed;
        this.name = name;
        this.position = new Coordinates(x, y);
        this.field = field;
        field.placeMe(x, y);
        this.status = "stand";
        this.id = idCount++;
        this.fraction = fraction;
        this.target = this;
        this.damageValue = 0;
    }


    /**
     * Создает персонажа как отряд, участвующий в сражении. Все персонажи в отряде действуют как один.
     * @param attack Бонус к атаке может определить точность её попадания по цели и величину нанесенного урона.
     * @param defense Бонус к защите может определить точность попадания со стороны противника и величину полученного урона.
     * @param damage Минимальное и максимальное значение урона, который может нанести персонаж.
     * @param health Очки здоровья.
     * @param speed Скорость реакции на поле боя.
     * @param name Имя персонажа.
     * @param x Номер строки в матрице {@link game.BattleField BattleField}.
     * @param y Номер столбца в матрице {@link game.BattleField BattleField}. Вместе с параметром x определяет положение персонажа на поле боя.
     * @param fraction Название фракции, команды, относительно которой персонаж различает союзников и противников.
     * @param field Ссылка на объект класса {@link game.BattleField BattleField}, который является полем боя с переданными на него координатами x и y.
     * @param quantity Количество персонажей в одном отряде.
     */
    public BaseHero(int attack, int defense, int[] damage, int health, int speed,
                    String name, int x, int y, String fraction, BattleField field, int quantity) {
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        this.damage[0] = this.damage[0] * quantity;
        this.damage[1] = this.damage[0] * quantity;
        this.maxHealth = health;
        this.health = maxHealth * quantity;
        this.speed = speed;
        this.name = name;
        this.position = new Coordinates(x, y);
        this.field = field;
        field.placeMe(x, y);
        this.status = "stand";
        this.id = idCount++;
        this.fraction = fraction;
        this.target = this;
        this.damageValue = 0;
    }


    /**
     * @return String название команды, к которой относится вызывающий объект {@link game.chars.BaseHero BaseHero}
     */
    public String getFraction() {
        return fraction;
    }


    /**
     * @return String cтатус персонажа, который определяет его возможности в течение хода.
     * <p>stand - жив и может совершить ход, быть целью других персонажей.</p>
     * <p>used - для {@link game.chars.Peasant Peasant}, что нет боеприпасов для передачи персонажам-стрелкам {@link game.chars.Xbowman Xbowman}, {@link game.chars.Sniper Sniper}. Такой Peasant может совершать ход и являться целью других персонажей.</p>
     * <p>used - для {@link game.chars.Xbowman Xbowman}, {@link game.chars.Sniper Sniper}, что у них боеприпас закончился. Такой персонаж может быть целью других, но не может совершить ход.</p>
     * <p>dead - умер. Не совершает ход и не является целью других персонажей.</p>
     */
    public String getStatus() {
        return status;
    }


    /**
     * @return координаты персонажа в форме объекта класса {@link game.chars.Coordinates Coordinates}.
     */
    public Coordinates getPosition() {
        return position;
    }


    /**
     * @return имя персонажа, установленное при создании, в формате String.
     */
    public String getName() {
        return name;
    }


    /**
     * @return очки здоровья в формате float.
     */
    public float getHealth() {
        return health;
    }


    /**
     * Сделать ход в процессе игры.
     * @param party объект класса {@link game.chars.Party Party}. Через него персонаж, совершающий ход, получает информацию о других участниках сражения: союзниках и противниках
     */
    @Override
    public void step(Party party) {}


    /**
     * Количество очков здоровья изменяется на значение, переданное в параметре damage.
     * @param damage целое число, обозначающее количество урона, который получает персонаж, вызывающие метод. Число может быть отрицательным, тогда персонаж лечится. очки здоровья Во втором случае невозможно, полечив, установить количество очков здоровья больше максимального, определенного при создании персонажа.
     */
    protected void damage(int damage) {
        health = health - damage;
        if (health <= 0) {
            status = "dead";
            health = 0;
        }
        if (health > maxHealth) health = maxHealth;
    }


    protected int damageValue(BaseHero h) {
        int flag = attack - h.defense;
        int value = 0;
        if (flag == 0) value = ((damage[0] + damage[1]) / 2);
        if (flag > 0) value = damage[1];
        if (flag < 0) value = damage[0];
        return value;
    }

    /**
     * Предоставляет базовую информацию о параметрах персонажа, которые могут непосредственно повлиять на совершение хода.
     * Применяется в начале игры для первой записи в лог.
     * @return строка String, в которой перечислены: название команды, имя, бонус к атаке, бонус к защите, минимальное и максимальное значение урона, текущие очки здоровья, скорость, статус, координаты на поле боя.
     */
    @Override
    public String getInfo() {
        return "fraction=" + fraction +
                ";name=" + name + id +
                ";attack=" + attack +
                ";defense=" + defense +
                ";damage=" + Arrays.toString(damage) +
                ";health=" + health +
                ";speed=" + speed +
                ";status=" + status +
                ";position=" + position.toString();

    }

    /**
     * Вызывается каждый раунд (см. {@link game.Fight Fight}) для каждого члена в {@link game.chars.Party Party} Предоставляет данные о персонаже для записи в лог игрового процесса (см. {@link game.Logger Logger}).
     * @return String содержит: название команды, имя вместе с id, координаты на поле боя, имя цели вместе с её id, координаты цели на поле боя, фактическая величина урона в данном ходу, статус цели.
     */
    public String defaultLog() {
        try {
            return String.join(";", fraction, name + id,
                    position.toString(), target.name + target.id, target.position.toString(), String.valueOf(damageValue), target.status);
        }
        catch(NullPointerException n){
            return String.join(";", fraction, name + id, "invalid data for target");
        }
    }

}
