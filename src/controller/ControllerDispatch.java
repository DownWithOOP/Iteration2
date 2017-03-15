package controller;

import controller.Observers.MapDisplayObserver;
import controller.Observers.PlayerObservator;
import controller.availablecommands.AvailableCommands;
import controller.availablecommands.Commandable;
import controller.commands.Command;
import controller.commands.CommandType;
import controller.commands.Direction;
import controller.commands.controllercommands.*;
import controller.commands.entitycommand.armycommand.*;
import controller.commands.entitycommand.entitycommand.*;
import controller.commands.entitycommand.unitcommand.*;
import controller.commands.entitycommand.unitcommand.explorercommand.*;
import controller.commands.playercommands.*;
import controller.ingamecontrollertypes.MainViewController;
import model.ActiveState;
import model.Cursor;
import model.GameModel;
import model.RallyPoint;
import model.common.Location;
import model.entities.EntityType;
import model.entities.structure.Structure;
import model.entities.structure.StructureType;
import model.entities.unit.*;
import model.entities.EntityId;
import model.entities.unit.Army;
import model.entities.unit.Explorer;
import model.entities.unit.Unit;
import model.player.Player;
import utilities.ObserverInterfaces.StatusObserver;
import utilities.ObserverInterfaces.StructureObserver;
import utilities.ObserverInterfaces.UnitObserver;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Konrad on 2/17/2017.
 */
public class ControllerDispatch {
    private AvailableCommands availableCommands;
    private GameModel gameModel;
    private HashMap<CommandType, Command> commandHashMap = new HashMap<>();

    public ControllerDispatch(int playerNumber, MapDisplayObserver mapDisplayObserver, UnitObserver unitObserver, StructureObserver structureObserver, StatusObserver statusObserver, PlayerObservator playerObservator) {
        availableCommands = new AvailableCommands();
        gameModel = new GameModel(playerNumber, mapDisplayObserver, unitObserver, structureObserver, statusObserver, playerObservator);
        ActiveState.getInstance().init(new Cursor(new Location(4,4)));
        setGameModelMap();
    }

    public void handleCommand(CommandType commandType) {
        //TODO: could add all these commands to command factory and perform them using the following method
        if (commandHashMap.containsKey(commandType)) {
            System.out.println("issuing command: " + commandHashMap.get(commandType).toString());
            commandHashMap.get(commandType).execute();

            return;
        }
    }

    public void handleCommandActivation() {
        CommandType selectedCommandType = gameModel.getActivePlayer().getCurrentCommandType();
        Commandable selectedInstance = gameModel.getActivePlayer().getCurrentInstanceToCommand();
        ActiveState.getInstance().update(selectedInstance);
        ActiveState.getInstance().update(selectedCommandType);
        System.out.println("Added to Queue: " + selectedCommandType.toString());

        if (selectedCommandType == CommandType.MOVE) {
            selectedInstance.addToQueue(new MoveUnitCommand((Unit)selectedInstance, ActiveState.getInstance().getCursor().getX() ,ActiveState.getInstance().getCursor().getY()));
        }
        else if (selectedCommandType == CommandType.MOVE_RALLY_POINT) {
            selectedInstance.addToQueue(new MoveRallyPointCommand((RallyPoint)selectedInstance, ActiveState.getInstance().getCursor().getLocation()));
        }
//        //ActiveState.relayCommand(CommandType.ACTIVATE_COMMAND);


        //ActiveState.relayCommand(CommandType.ACTIVATE_COMMAND);
        ActiveState.getInstance().relayCommand(CommandType.ACTIVATE_COMMAND);

        //System.out.println("controller dispatch says that command is " + selectedCommandType);
//        switch(selectedCommandType) {
//            case DISBAND:
//                selectedInstance.addToQueue(new DisbandCommand((Army)selectedInstance));
//                break;
//            case POWER_UP:
//                selectedInstance.addToQueue(new PowerUpCommand(selectedInstance));
//                break;
//            case POWER_DOWN:
//                selectedInstance.addToQueue(new PowerDownCommand(selectedInstance));
//                break;
//            case CANCEL_QUEUE:
//                selectedInstance.addToQueue(new CancelQueueCommand(selectedInstance));
//                break;
//            case DECOMMISSION:
//                selectedInstance.addToQueue(new DecommissionCommand(selectedInstance));
//                break;
//            case ABANDON_ARMY:
//                selectedInstance.addToQueue(new AbandonArmyCommand((Unit)selectedInstance));
//                break;
//            case PROSPECT:
//                selectedInstance.addToQueue(new ProspectCommand((Explorer) selectedInstance));
//                break;
//            default:
//                System.out.print("Invalid command");
//                break;
//        }

    }

    public void handleBuildStructureCommand(String structureTypeStr) {
        Commandable selectedInstance = gameModel.getActivePlayer().getCurrentInstance();
        CommandType selectedCommandType = gameModel.getActivePlayer().getCurrentCommandType();
        ActiveState.getInstance().update(selectedInstance);
        ActiveState.getInstance().update(selectedCommandType);

        EntityType entityType = EntityType.valueOf(structureTypeStr);
        ActiveState.getInstance().constructModifier(entityType);
        ActiveState.getInstance().relayCommand(CommandType.BUILD_STRUCTURE);
    }

    public void handleCreateUnitCommand(String unitTypeStr) {
        Commandable selectedInstance = gameModel.getActivePlayer().getCurrentInstance();
        CommandType selectedCommandType = gameModel.getActivePlayer().getCurrentCommandType();
        ActiveState.getInstance().update(selectedInstance);
        ActiveState.getInstance().update(selectedCommandType);

        EntityType entityType = EntityType.valueOf(unitTypeStr);
        ActiveState.getInstance().constructModifier(entityType);
        ActiveState.getInstance().relayCommand(CommandType.CREATE_UNIT);
    }

    public void handleHealUnitCommand(String unitStr) {
        //FighterUnit unit = unitStr;
    }
    public void handleCommandActivationFromView() {
        //View needs to have set the necessary information in active state before calling this
        ActiveState.getInstance().relayCommand(CommandType.ACTIVATE_COMMAND);

        //System.out.println("controller dispatch says that command is " + selectedCommandType);
//        switch(selectedCommandType) {
//            case DISBAND:
//                selectedInstance.addToQueue(new DisbandCommand((Army)selectedInstance));
//                break;
//            case POWER_UP:
//                selectedInstance.addToQueue(new PowerUpCommand(selectedInstance));
//                break;
//            case POWER_DOWN:
//                selectedInstance.addToQueue(new PowerDownCommand(selectedInstance));
//                break;
//            case CANCEL_QUEUE:
//                selectedInstance.addToQueue(new CancelQueueCommand(selectedInstance));
//                break;
//            case DECOMMISSION:
//                selectedInstance.addToQueue(new DecommissionCommand(selectedInstance));
//                break;
//            case ABANDON_ARMY:
//                selectedInstance.addToQueue(new AbandonArmyCommand((Unit)selectedInstance));
//                break;
//            case PROSPECT:
//                selectedInstance.addToQueue(new ProspectCommand((Explorer) selectedInstance));
//                break;
//            default:
//                System.out.print("Invalid command");
//                break;
//        }
    }

    //TODO: ASK  IF THIS WILL WORK WHEN THE PLAYERS ARE CHANGED
    //^^^ it (should) work now :D - JS
    private void setGameModelMap() {
        commandHashMap.put(CommandType.END_TURN, new EndTurn(gameModel, this));
        commandHashMap.put(CommandType.CYCLE_MODE_NEXT, new CycleModeNext(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_MODE_PREV, new CycleModePrev(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_TYPE_NEXT, new CycleTypeNext(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_TYPE_PREV, new CycleTypePrev(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_INSTANCE_NEXT, new CycleInstanceNext(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_INSTANCE_PREV, new CycleInstancePrev(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_COMMAND_NEXT, new CycleCommandNext(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CYCLE_COMMAND_PREV, new CycleCommandPrev(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.CREATE_ARMY, new CreateArmy(gameModel.getActivePlayer()));
        commandHashMap.put(CommandType.SELECT_INSTANCE_1, new SelectInstance(gameModel.getActivePlayer(), 1));
        commandHashMap.put(CommandType.SELECT_INSTANCE_2, new SelectInstance(gameModel.getActivePlayer(), 2));
        commandHashMap.put(CommandType.SELECT_INSTANCE_3, new SelectInstance(gameModel.getActivePlayer(), 3));
        commandHashMap.put(CommandType.SELECT_INSTANCE_4, new SelectInstance(gameModel.getActivePlayer(), 4));
        commandHashMap.put(CommandType.SELECT_INSTANCE_5, new SelectInstance(gameModel.getActivePlayer(), 5));
        commandHashMap.put(CommandType.SELECT_INSTANCE_6, new SelectInstance(gameModel.getActivePlayer(), 6));
        commandHashMap.put(CommandType.SELECT_INSTANCE_7, new SelectInstance(gameModel.getActivePlayer(), 7));
        commandHashMap.put(CommandType.SELECT_INSTANCE_8, new SelectInstance(gameModel.getActivePlayer(), 8));
        commandHashMap.put(CommandType.SELECT_INSTANCE_0, new SelectInstance(gameModel.getActivePlayer(), 0));
    }

    public void updateActiveController(Controller newActiveController) {
        commandHashMap.put(CommandType.MOVE_CAMERA_UP, new MoveCameraUp(newActiveController));
        commandHashMap.put(CommandType.MOVE_CAMERA_RIGHT, new MoveCameraRight(newActiveController));
        commandHashMap.put(CommandType.MOVE_CAMERA_DOWN, new MoveCameraDown(newActiveController));
        commandHashMap.put(CommandType.MOVE_CAMERA_LEFT, new MoveCameraLeft(newActiveController));

        //Put in cursor commands to our map if we have a main view controller
        //Otherwise, remove cursor commands from map (if they exist) so cursor doesn't move when not on main menu
        try {
            MainViewController newActiveMainViewController = (MainViewController) newActiveController;

            commandHashMap.put(CommandType.MOVE_CURSOR_NORTH_EAST, new MoveCursorNE(newActiveMainViewController));
            commandHashMap.put(CommandType.MOVE_CURSOR_NORTH, new MoveCursorNorth(newActiveMainViewController));
            commandHashMap.put(CommandType.MOVE_CURSOR_NORTH_WEST, new MoveCursorNW(newActiveMainViewController));
            commandHashMap.put(CommandType.MOVE_CURSOR_SOUTH_EAST, new MoveCursorSE(newActiveMainViewController));
            commandHashMap.put(CommandType.MOVE_CURSOR_SOUTH, new MoveCursorSouth(newActiveMainViewController));
            commandHashMap.put(CommandType.MOVE_CURSOR_SOUTH_WEST, new MoveCursorSW(newActiveMainViewController));
        } catch (ClassCastException e) {
            commandHashMap.remove(CommandType.MOVE_CURSOR_NORTH_EAST);
            commandHashMap.remove(CommandType.MOVE_CURSOR_NORTH);
            commandHashMap.remove(CommandType.MOVE_CURSOR_NORTH_WEST);
            commandHashMap.remove(CommandType.MOVE_CURSOR_SOUTH_EAST);
            commandHashMap.remove(CommandType.MOVE_CURSOR_SOUTH);
            commandHashMap.remove(CommandType.MOVE_CURSOR_SOUTH_WEST);
        }
    }

    public int getActivePlayerNumber() {
        return gameModel.getActivePlayerIndex() + 1;
    }

    public Player getPlayer() {
        return gameModel.getActivePlayer();
    }

    public void updateActiveStateCommandable(EntityId commandableId) {
        ActiveState.getInstance().update(gameModel.getEntity(commandableId));
    }

    public void updateActiveStateModifier(int armyNumber) {
        ActiveState.getInstance().constructModifier(armyNumber);
    }

    public void updateActiveStateCommand(CommandType commandType) {
        ActiveState.getInstance().update(commandType);
    }

    public void updateActiveStateModifier(Direction direction) {
        ActiveState.getInstance().constructModifier(direction);
    }

    public void resetMap() {
        setGameModelMap();
    }
}
