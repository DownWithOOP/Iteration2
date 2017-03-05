package controller.commands;

/**
 * Created by jordi on 2/20/2017.
 */
public enum CommandType {
    END_TURN,
    CYCLE_MODE_NEXT,
    CYCLE_MODE_PREV,
    CYCLE_TYPE_NEXT,
    CYCLE_TYPE_PREV,
    CYCLE_INSTANCE_NEXT,
    CYCLE_INSTANCE_PREV,
    CYCLE_COMMAND_NEXT,
    CYCLE_COMMAND_PREV,
    SELECT,
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NORTH_EAST,
    NORTH_WEST,
    SOUTH_EAST,
    SOUTH_WEST,
    MOVE,
    DECOMISSION,
    DEFEND, EXPLORE, JOIN_ARMY, ADVANCE_TO_RALLY_POINT, ABANDON_ARMY, CANCEL_QUEUE, POWER_DOWN, POWER_UP, DISBAND, ATTACK,
    MOVE_CAMERA_UP, MOVE_CAMERA_DOWN, MOVE_CAMERA_LEFT, MOVE_CAMERA_RIGHT
}
