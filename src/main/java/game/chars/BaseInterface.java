package game.chars;
import java.util.ArrayList;

public interface BaseInterface {
    String getInfo();

    void step(ArrayList<BaseHero> enemy); //хочу обращаться напрямую к field.size, а не получать как аргумент. Связь внутреннего пакета и внешнего
}
