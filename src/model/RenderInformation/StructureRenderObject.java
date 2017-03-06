package model.RenderInformation;

import model.entities.EntityType;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by Konrad on 3/5/2017.
 */
public class StructureRenderObject {
    private int locationX; // X location on the map the Structure is located
    private int locationY; // Y location on the map the Structure is located
    private IdType idType; // type of Structure so we know which assets to render
    // TODO later more information such as structure missions

    public StructureRenderObject(IdType idType, int locationX, int locationY){
        this.idType = idType;
        this.locationX = locationX;
        this.locationY = locationY;
    }
    public IdType getIdType() { return this.idType; };
    public int getLocationX(){
        return this.locationX;
    }
    public int getLocationY(){
        return this.locationY;
    }
}