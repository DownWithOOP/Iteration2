package controller.inputhandler;

import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.Direction;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.ActiveState;

import java.security.Key;
import java.util.HashMap;

/**
 * Created by jordi on 2/27/2017.
 */
public class MVCInputHandler {
    HashMap<String, CommandType> handleCommand = new HashMap<>();
    HashMap<String,Direction> handleDirection= new HashMap<>();
    HashMap<String,Integer> handleNumbers= new HashMap<>();
    final String control = "control";

    public MVCInputHandler() {
        initializeMapValues();
    }

    public CommandType interpretInput(KeyEvent event) {
        String keyCode = event.getCode()  + "";

        if (event.isControlDown()) {
            keyCode += control;
        }

        /**
         * if this line is erased all actionable commands will stop working
         * sets the modifier for the active state
         */
        applyModifier(keyCode);

        if (handleCommand.containsKey(keyCode)) {
            CommandType commandType=handleCommand.get(keyCode);
//            ActiveState.relayCommand(commandType);
            return commandType;
        }

        return null;
    }

    private void applyModifier(String keyCode){
        if (handleDirection.containsKey(keyCode)) {
            Direction direction=handleDirection.get(keyCode);
            ActiveState.constructModifier(direction);
            return;
        }

        if (handleNumbers.containsKey(keyCode)){
            int number=handleNumbers.get(keyCode);
            ActiveState.constructModifier(number);
            return;
        }
        return;
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

        handleCommand.put(KeyCode.NUMPAD1 + "", CommandType.MOVE);
        handleCommand.put(KeyCode.NUMPAD2 + "", CommandType.MOVE);
        handleCommand.put(KeyCode.NUMPAD3 + "", CommandType.MOVE);
        handleCommand.put(KeyCode.NUMPAD7 + "", CommandType.MOVE);
        handleCommand.put(KeyCode.NUMPAD8 + "", CommandType.MOVE);
        handleCommand.put(KeyCode.NUMPAD9 + "", CommandType.MOVE);

        handleCommand.put(KeyCode.DIGIT0.toString(),CommandType.JOIN_ARMY);
        handleCommand.put(KeyCode.DIGIT1.toString(),CommandType.JOIN_ARMY);
        handleCommand.put(KeyCode.DIGIT2.toString(),CommandType.JOIN_ARMY);
        handleCommand.put(KeyCode.DIGIT3.toString(),CommandType.JOIN_ARMY);
        handleCommand.put(KeyCode.DIGIT4.toString(),CommandType.JOIN_ARMY);
        handleCommand.put(KeyCode.DIGIT5.toString(),CommandType.JOIN_ARMY);
        handleCommand.put(KeyCode.DIGIT6.toString(),CommandType.JOIN_ARMY);
        handleCommand.put(KeyCode.DIGIT7.toString(),CommandType.JOIN_ARMY);
        handleCommand.put(KeyCode.DIGIT8.toString(),CommandType.JOIN_ARMY);

        handleCommand.put(KeyCode.ENTER + "", CommandType.ACTIVATE_COMMAND);
        handleCommand.put(KeyCode.NUMPAD5 + "", CommandType.FOCUS);


        // adding these to be able to move around the map with W,A,S,D
        handleCommand.put(KeyCode.W + "", CommandType.MOVE_CAMERA_UP);
        handleCommand.put(KeyCode.S + "", CommandType.MOVE_CAMERA_DOWN);
        handleCommand.put(KeyCode.A + "", CommandType.MOVE_CAMERA_LEFT);
        handleCommand.put(KeyCode.D + "", CommandType.MOVE_CAMERA_RIGHT);

        handleDirection.put(KeyCode.NUMPAD1 + "", Direction.SOUTH_WEST);
        handleDirection.put(KeyCode.NUMPAD2 + "", Direction.SOUTH);
        handleDirection.put(KeyCode.NUMPAD3 + "", Direction.SOUTH_EAST);
        handleDirection.put(KeyCode.NUMPAD7 + "", Direction.NORTH_WEST);
        handleDirection.put(KeyCode.NUMPAD8 + "", Direction.NORTH);
        handleDirection.put(KeyCode.NUMPAD9 + "", Direction.EAST);

        handleNumbers.put(KeyCode.DIGIT0.toString(),0);
        handleNumbers.put(KeyCode.DIGIT1.toString(),1);
        handleNumbers.put(KeyCode.DIGIT2.toString(),2);
        handleNumbers.put(KeyCode.DIGIT3.toString(),3);
        handleNumbers.put(KeyCode.DIGIT4.toString(),4);
        handleNumbers.put(KeyCode.DIGIT5.toString(),5);
        handleNumbers.put(KeyCode.DIGIT6.toString(),6);
        handleNumbers.put(KeyCode.DIGIT7.toString(),7);
        handleNumbers.put(KeyCode.DIGIT8.toString(),8);
        handleNumbers.put(KeyCode.DIGIT9.toString(),9);
    }


}



