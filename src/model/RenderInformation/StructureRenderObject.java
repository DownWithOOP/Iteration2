package model.RenderInformation;

import model.entities.EntityType;
import utilities.id.CustomID;

/**
 * Created by Konrad on 3/5/2017.
 */
public class StructureRenderObject {
    private int locationX; // X location on the map the Structure is located
    private int locationY; // Y location on the map the Structure is located
    private EntityType entityType; // type of Structure so we know which assets to render
    private CustomID playerID; // we need to know to who this structure belongs to, might be ours, might be some enemies
    // TODO later more information such as strucutre mission

    public StructureRenderObject(EntityType entityType, int locationX, int locationY, CustomID playerID){
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
    public CustomID getCustomID(){
        return this.playerID;
    }
}
