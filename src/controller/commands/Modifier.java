package controller.commands;

/**
 * Created by jordi on 3/1/2017.
 */
public class Modifier {
    public Direction direction;
    public int number;

    public  Modifier(Direction direction){
        this.direction=direction;
    }

    public Modifier(int number){
        this.number=number;
    }

    public boolean hasNonEmptyDirection(){

        return direction!=null;
    }
}
