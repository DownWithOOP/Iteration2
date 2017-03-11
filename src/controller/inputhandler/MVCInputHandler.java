package controller.inputhandler;

import controller.commands.CommandType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;

/**
 * Created by jordi on 2/27/2017.
 */
public class MVCInputHandler {
    HashMap<String, CommandType> handleCommand = new HashMap<>();
    final String control = "control";

    public MVCInputHandler() {
        initializeMapValues();
    }

    public CommandType interpretInput(KeyEvent event) {
        String keyCode = event.getCode()  + "";

        if (event.isControlDown()) {
            keyCode += control;
        }


        if (handleCommand.containsKey(keyCode)) {
            return handleCommand.get(keyCode);
        }

        return null;
    }

    public void initializeMapValues() {

        handleCommand.put(KeyCode.UP + control, CommandType.CYCLE_MODE_NEXT);
        handleCommand.put(KeyCode.DOWN + control, CommandType.CYCLE_MODE_PREV);
        handleCommand.put(KeyCode.LEFT + control, CommandType.CYCLE_TYPE_PREV);
        handleCommand.put(KeyCode.RIGHT + control, CommandType.CYCLE_TYPE_NEXT);

        handleCommand.put(KeyCode.UP + "", CommandType.CYCLE_COMMAND_NEXT);
        handleCommand.put(KeyCode.DOWN + "", CommandType.CYCLE_COMMAND_PREV);
        handleCommand.put(KeyCode.LEFT + "", CommandType.CYCLE_INSTANCE_PREV);
        handleCommand.put(KeyCode.RIGHT + "", CommandType.CYCLE_INSTANCE_NEXT);

        handleCommand.put(KeyCode.NUMPAD1 + "", CommandType.MOVE_CURSOR_SOUTH_WEST);
        handleCommand.put(KeyCode.NUMPAD2 + "", CommandType.MOVE_CURSOR_SOUTH);
        handleCommand.put(KeyCode.NUMPAD3 + "", CommandType.MOVE_CURSOR_SOUTH_EAST);
        handleCommand.put(KeyCode.ENTER + "", CommandType.ACTIVATE_COMMAND);

        handleCommand.put(KeyCode.NUMPAD5 + "", CommandType.SELECT);
        handleCommand.put(KeyCode.NUMPAD7 + "", CommandType.MOVE_CURSOR_NORTH_WEST);
        handleCommand.put(KeyCode.NUMPAD8 + "", CommandType.MOVE_CURSOR_NORTH);
        handleCommand.put(KeyCode.NUMPAD9 + "", CommandType.MOVE_CURSOR_NORTH_EAST);

        // adding these to be able to move around the map with W,A,S,D
        handleCommand.put(KeyCode.W + "", CommandType.MOVE_CAMERA_UP);
        handleCommand.put(KeyCode.S + "", CommandType.MOVE_CAMERA_DOWN);
        handleCommand.put(KeyCode.A + "", CommandType.MOVE_CAMERA_LEFT);
        handleCommand.put(KeyCode.D + "", CommandType.MOVE_CAMERA_RIGHT);

    }


}



