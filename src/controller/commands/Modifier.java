package controller.commands;

/**
 * Created by jordi on 3/1/2017.
 */
public class Modifier {
    public Direction direction;
    public int number;

    public void setModifier(Direction direction){
        this.direction=direction;
    }

    public void setModifier(int number){
        this.number=number;
    }
}
