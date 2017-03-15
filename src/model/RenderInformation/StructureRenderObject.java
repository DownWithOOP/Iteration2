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
    private String missions;

    // TODO later more information such as structure missions

    public StructureRenderObject(CustomID structureId, IdType idType, int locationX, int locationY, StructureStats structureStats, String missions){
        this.structureId = structureId;
        this.idType = idType;
        this.locationX = locationX;
        this.locationY = locationY;
        this.structureStats = structureStats;
        this.missions = missions;
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

    public String getMissions() {
        //System.out.println("Missions: " + this.missions);
        return this.missions;
    }
}
