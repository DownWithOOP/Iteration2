package model.RenderInformation;

import controller.availablecommands.Commandable;
import controller.commands.CommandType;
import model.Mode;
import model.common.Location;

/**
 * Created by Jonathen on 3/6/2017.
 */
public class StatusRenderInformation {

    private String modeString;
    private String typeString;
    private String instanceString;
    private String commandString;
    private int locationX;
    private int locationY;

    //TODO account for other part of status viewport

    //TODO maybe have two different objects: one for cycling stuff and one for stats

    public StatusRenderInformation() {
        modeString = "";
        typeString = "";
        instanceString = "";
        commandString = "";
        locationX = -1;
        locationY = -1;
    }

    public void updateModeString(Mode newMode) {
        modeString = newMode.toString();
    }

    public void updateTypeString(String newType) {
        if (newType != null) {
            typeString = newType.toString();
        }
        else {
            typeString = "No Selected Type Available";
        }
    }

    public void updateLocationInfo(Location updatedLocation){
        if (updatedLocation != null) {
            this.locationX = (int)updatedLocation.getX();
            this.locationY = (int)updatedLocation.getY();
        }else{
            this.locationX = -1;
            this.locationY = -1;
        }
    }

    public void updateInstanceString(Commandable selectedCommandable) {
        if (selectedCommandable != null) {
            instanceString = selectedCommandable.toString();
        }
        else {
            instanceString = "No Selected Instance Available";
        }
    }

    public void updateCommandString(CommandType newCommandType) {
        if (newCommandType != null) {
            commandString = newCommandType.toString();
        }
        else {
            commandString = "No Command Available";
        }
    }

    public int getLocationX(){
        return this.locationX;
    }
    public int getLocationY(){
        return this.locationY;
    }
    public String getModeString() {
        return modeString;
    }
    public String getTypeString() {
        return typeString;
    }
    public String getInstanceString() {
        return instanceString;
    }
    public String getCommandString() {
        return commandString;
    }

}
