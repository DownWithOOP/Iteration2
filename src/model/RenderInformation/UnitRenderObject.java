package model.RenderInformation;

import model.entities.EntityId;
import model.entities.Stats.UnitStats;
import utilities.id.CustomID;
import utilities.id.IdType;

/**
 * Created by Konrad on 3/5/2017.
 */
public class UnitRenderObject {

    private int locationX; // X location on map Unit is located
    private int locationY; // Y location on map Unit is located
    private IdType idType; // type of unit so we know which assets to render, along with unit ownership

    private EntityId id;
    private UnitStats unitStats;
    // TODO later add more info like stats

    public UnitRenderObject(EntityId id, int locationX, int locationY, UnitStats unitStats){
        this.id = id;
        this.idType = id.getIdType();
        this.locationX = locationX;
        this.locationY = locationY;
        this.unitStats = unitStats;
    }
    public IdType getIdType(){
        return this.idType;
    }
    public EntityId getUnitId(){
        return this.id;
    }
    public int getLocationX(){
        return this.locationX;
    }
    public int getLocationY(){
        return this.locationY;
    }
    public UnitStats getUnitStats(){return this.unitStats; };

    public EntityId getId() {
        return id;
    }

}
