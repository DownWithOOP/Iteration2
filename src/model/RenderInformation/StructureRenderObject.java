package model.RenderInformation;

import model.entities.EntityType;
import model.entities.Stats.StructureStats;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by Konrad on 3/5/2017.
 */
public class StructureRenderObject {
    private int locationX; // X location on the map the Structure is located
    private int locationY; // Y location on the map the Structure is located
    private IdType idType; // type of Structure so we know which assets to render
    private CustomID structureId;
    private StructureStats structureStats;

    // TODO later more information such as structure missions

    public StructureRenderObject(CustomID structureId, IdType idType, int locationX, int locationY, StructureStats structureStats){
        this.structureId = structureId;
        this.idType = idType;
        this.locationX = locationX;
        this.locationY = locationY;
        this.structureStats = structureStats;
    }

    public CustomID getStructureId() {
        return structureId;
    }
    public IdType getIdType() { return this.idType; };
    public int getLocationX(){
        return this.locationX;
    }
    public int getLocationY(){
        return this.locationY;
    }
    public StructureStats getStructureStats() { return this.structureStats; }
}
