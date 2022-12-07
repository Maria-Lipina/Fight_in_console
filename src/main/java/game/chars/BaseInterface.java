package game.chars;
import java.io.IOException;
import java.util.ArrayList;

public interface BaseInterface {
    String getInfo();

    void step(ArrayList<BaseHero> enemy);

    //"Step No", "Side", "Hero+ID", "Target", "Damage val"
//    void logIt(BaseHero target, int damageValue);
}
