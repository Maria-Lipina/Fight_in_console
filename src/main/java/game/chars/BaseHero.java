package game.chars;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public abstract class BaseHero implements BaseInterface, Iterator {
    protected int attack;
    protected int defense;

    protected int[] damage;

    protected int damageValue;
    protected int health;
    protected int maxHealth;
    protected int speed;
    protected String name;
    protected ArrayList<BaseHero> myParty;

    protected BaseHero target;

    protected Coordinates position;
    protected String status;

    protected String fraction;

    private static int idCount = 0;
    protected int id;

    public BaseHero(int attack, int defense, int[] damage, int health, int speed,
                    String name, ArrayList<BaseHero> myParty, int x, int y, String fraction) {
        this.attack = attack;
        this.defense = defense;
        this.damage = damage;
        this.maxHealth = health;
        this.health = maxHealth;
        this.speed = speed;
        this.name = name;
        this.myParty = myParty;
        this.position = new Coordinates(x, y);
        this.status = "stand";
        this.id = idCount++;
        this.fraction = fraction;
        this.target = null;
        this.damageValue = 0;
    }
    /* Шпаргалка по имеющимся статусам
     * stand - для всех. Жив и готов сражаться. По сути аналогичен статусу alive
     * used - для крестьян, что они свою стрелу подали. Или для стрелков, что у них боеприпас закончился, а здоровье ещё нет
     * dead - умер. Здоровье = или меньше 0
     * */

    public int getId() {
        return id;
    }

    public String getFraction() {
        return fraction;
    }

    public String getStatus() {
        return status;
    }

    public Coordinates getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    @Override
    public void step(Party party) {}

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

    private int classFields;

    @Override
    public boolean hasNext() {
        return classFields++ < 8;
    }

    @Override
    public String next() {
        switch (classFields) {
            case 0: return "name=" + name;
            case 1: return ", attack=" + attack;
            case 2: return ", defense=" + defense;
            case 3: return ", damage=" + Arrays.toString(damage);
            case 4: return ", Max HP=" + maxHealth;
            case 5: return ", HP=" + health;
            case 6: return ", speed=" + speed;
            case 7: return ", status=" + status;


        }
        return null;
    }

    // "Step No", "Side", "MyName+ID", "MyPos", "Target", "TargetPos", "Damage val", "MyStatus"
    public void defaultLog() {

    }

}
