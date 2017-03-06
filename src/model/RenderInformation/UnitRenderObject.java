package model.RenderInformation;

import model.entities.EntityType;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/5/2017.
 */
public class UnitRenderObject {

    private int locationX; // X location on map Unit is located
    private int locationY; // Y location on map Unit is located
    private EntityType entityType; // type of unit so we know which assets to render
    private CustomID playerID; // we need to know to who this unit belongs to, might be ours, might be some enemies

    public UnitRenderObject(EntityType entityType, int locationX, int locationY, CustomID playerID){
        this.entityType = entityType;
        this.locationX = locationX;
        this.locationY = locationY;
        this.playerID = playerID;
    }
    public EntityType getEntityType(){
        return this.entityType;
    }
    public int getLocationX(){
        return this.locationX;
    }
    public int getLocationY(){
        return this.locationY;
    }
    public CustomID getPlayerID(){
        return this.playerID;
    }

}
