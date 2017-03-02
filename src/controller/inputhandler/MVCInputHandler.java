package controller.inputhandler;

import controller.commands.CommandType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.security.Key;
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

        handleCommand.put(KeyCode.NUMPAD1 + "", CommandType.SOUTH_WEST);
        handleCommand.put(KeyCode.NUMPAD2 + "", CommandType.SOUTH);
        handleCommand.put(KeyCode.NUMPAD3 + "", CommandType.SOUTH_EAST);
        handleCommand.put(KeyCode.NUMPAD4 + "", CommandType.WEST);
        handleCommand.put(KeyCode.NUMPAD5 + "", CommandType.SELECT);
        handleCommand.put(KeyCode.NUMPAD6 + "", CommandType.EAST);
        handleCommand.put(KeyCode.NUMPAD7 + "", CommandType.NORTH_WEST);
        handleCommand.put(KeyCode.NUMPAD8 + "", CommandType.NORTH);
        handleCommand.put(KeyCode.NUMPAD9 + "", CommandType.NORTH_EAST);

        // adding these to be able to move around the map with W,A,S,D
        handleCommand.put(KeyCode.W+ "", CommandType.MOVE_CAMERA_UP);
        handleCommand.put(KeyCode.S + "", CommandType.MOVE_CAMERA_DOWN);
        handleCommand.put(KeyCode.A + "", CommandType.MOVE_CAMERA_LEFT);
        handleCommand.put(KeyCode.D + "", CommandType.MOVE_CAMERA_RIGHT);

    }


}



