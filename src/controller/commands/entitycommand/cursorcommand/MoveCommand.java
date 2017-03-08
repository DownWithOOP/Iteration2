package controller.commands.entitycommand.cursorcommand;

import controller.commands.Command;
import controller.commands.Direction;
import model.Cursor;
import model.RallyPoint;

/**
 * Created by jordi on 2/28/2017.
 */
public class MoveCommand implements Command {

    private Cursor cursor;
    private Direction direction;

    public MoveCommand(Cursor cursor, Direction direction){
        this.cursor=cursor;
        this.direction=direction;
    }

    @Override
    public boolean execute() {
        cursor.move(direction);
        return true;
    }

}
